name := "workAutomation"

version := "1.0"

scalaVersion := "2.11.8"

scalacOptions ++=
  Seq("-encoding", "UTF8", "-unchecked", "-deprecation", "-language:postfixOps", "-language:implicitConversions", "-language:higherKinds", "-language:reflectiveCalls")

val akkaVersion = "2.4.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-experimental" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,
  "com.typesafe.akka" %% "akka-http-testkit-experimental" % "2.4.2-RC3" % Test,
  "mysql" % "mysql-connector-java" % "5.1.24",
  "org.scalatest" %% "scalatest" % "3.0.0"
)
libraryDependencies += "com.typesafe" % "config" % "1.3.1"

libraryDependencies ++= Seq("org.slf4j" % "slf4j-api" % "1.7.5",
  "org.slf4j" % "slf4j-simple" % "1.7.5")