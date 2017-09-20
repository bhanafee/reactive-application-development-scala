name := "Guidebook_Demo"

version := "1.0"

val akkaVersion = "2.5.4"

val openTracingVersion = "0.30.0"

scalaVersion := "2.12.3"

resolvers += "Lightbend Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-remote" % akkaVersion,
  "io.opentracing" % "opentracing-api" % openTracingVersion,
  "io.opentracing" % "opentracing-mock" % openTracingVersion
)
