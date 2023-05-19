package controllers

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import javax.inject._
import play.api._
import play.api.data.Form
import play.api.libs.ws.{WSClient, WSResponse}
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent.Future

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(config: Configuration, ws: WSClient, cc: MessagesControllerComponents) extends MessagesAbstractController(cc) {
  import contentForm._

  val bcrypt = new BCryptPasswordEncoder();

    private val postUrl = routes.HomeController.action

  def index() = Action { implicit request : MessagesRequest[AnyContent] =>
    Ok(views.html.index(form, postUrl));
  }

  def action() = Action {implicit request : MessagesRequest[AnyContent] =>
    val errorFunction = { formWithErrors: Form[contentForm.Data] =>
      Redirect("/");
    }

    val successFunction = { data: contentForm.Data =>
      val content = contentForm.Data(content = data.content, password = data.password);
      val passwordDigest = config.get[String]("passwordDigest");
      if (bcrypt.matches(content.password, passwordDigest)) {
        val body = Json.obj(
          "content" -> content.content
        )
        val request: Future[WSResponse] = ws.url(config.get[String]("webHookUrl")).addHttpHeaders("Accept" -> "application/json").post(body);
      }
      Redirect("/");
    }

    val formValidationResult = form.bindFromRequest();
    formValidationResult.fold(errorFunction, successFunction);
  }
}
