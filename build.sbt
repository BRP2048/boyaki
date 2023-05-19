name := """boyaki"""
organization := "systems.brp.saki"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.10"

libraryDependencies += guice
libraryDependencies ++= Seq(
    "com.google.inject"            % "guice"                % "5.1.0",
    "com.google.inject.extensions" % "guice-assistedinject" % "5.1.0"
)
libraryDependencies += "org.springframework.security" % "spring-security-crypto" % "6.1.0"
libraryDependencies ++= Seq(ws)
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "systems.brp.saki.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "systems.brp.saki.binders._"
