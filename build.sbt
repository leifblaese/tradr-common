
lazy val libdeps = Seq(
  "com.typesafe" % "config" % "1.3.1",
  "com.typesafe.play" %% "play-json" % "2.6.3"
)


lazy val root = (project in file("."))
  .settings(Seq(
    name := "tradr-common",
    organization := "tradr",
    scalaVersion := "2.12.3",
    crossScalaVersions := Seq("2.11.11", "2.12.3"),
    version := "0.0.5",
    libraryDependencies ++= libdeps
  ))

