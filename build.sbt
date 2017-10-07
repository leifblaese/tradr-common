
lazy val libdeps = Seq(
  "com.typesafe" % "config" % "1.3.1",
  "com.typesafe.play" %% "play-json" % "2.6.1"
)





lazy val root = (project in file("."))
  .settings(Seq(
    name := "tradr-common",
    organization := "tradr",
    scalaVersion := "2.12.2",
    version := "0.0.1",
    libraryDependencies ++= libdeps,
    assemblyJarName in assembly :=  s"${name.value}-${version.value}.jar"
  ))

