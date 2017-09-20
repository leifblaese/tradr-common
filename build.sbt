
lazy val libdeps = Seq(
//  "org.deeplearning4j" % "deeplearning4j-core" % "0.8.0",
//  "org.nd4j" % "nd4j-native-platform" % "0.8.0",
  "com.typesafe" % "config" % "1.3.1",
  "com.typesafe.play" %% "play-json" % "2.6.1"
)





lazy val root = (project in file("."))
  .settings(Seq(
    name := "tradr-common",
    organization := "tradr",
    scalaVersion := "2.12.2",
    version := "1.0.0",
    libraryDependencies ++= libdeps
//    assemblyJarName in assembly := "tradr-common.jar"
  ))

