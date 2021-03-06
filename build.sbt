name := "Scatalogue"

version := "0.1-ALPHA"

scalaVersion := "2.11.6"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-feature")

libraryDependencies ++= {
    val akkaVersion = "2.3.9"
    val sprayVersion = "1.3.3" 
    val Json4sVersion = "3.2.11"
    Seq(
        "io.spray"            %%  "spray-can"     % sprayVersion,
        "io.spray"            %%  "spray-routing" % sprayVersion,
        "io.spray"            %%  "spray-testkit" % sprayVersion  % "test",
        "com.typesafe.akka"   %%  "akka-actor"    % akkaVersion,
        "com.typesafe.akka"   %%  "akka-testkit"  % akkaVersion   % "test",
        "org.specs2"          %%  "specs2-core"   % "2.3.11" % "test",
        "org.json4s"          %%  "json4s-native" % Json4sVersion,
        "org.json4s"          %%  "json4s-ext"    % Json4sVersion
    )
}

Revolver.settings