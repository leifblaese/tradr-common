
lazy val libdeps = Seq(
  "com.typesafe" % "config" % "1.3.1",
  "com.typesafe.play" %% "play-json" % "2.6.3"
)


lazy val root = (project in file("."))
  .enablePlugins(GitVersioning)
  .settings(Seq(
    name := "tradr-common",
    organization := "tradr",
    scalaVersion := "2.12.3",
//    crossScalaVersions := Seq("2.11.11", "2.12.3"),
    libraryDependencies ++= libdeps,
    git.useGitDescribe := true
  ))

