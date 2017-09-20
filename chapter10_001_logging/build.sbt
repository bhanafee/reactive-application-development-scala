name := "Guidebook_Demo"

version := "1.0"

val akkaVersion = "2.5.4"

val logbackVersion = "1.2.3"

scalaVersion := "2.12.3"

resolvers += "Lightbend Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "ch.qos.logback"    %  "logback-classic" % logbackVersion
)
