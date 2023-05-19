package controllers

import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText}

object contentForm {
  case class Data (
                      content: String,
                      password: String
                    )

  val form = Form(
    mapping(
      "content" -> nonEmptyText,
      "password" -> nonEmptyText
    )(Data.apply)(Data.unapply)
  )

}

