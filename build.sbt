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
  "org.scalatest" %% "scalatest" % "3.0.0",
  "junit" % "junit" % "4.12"
)
libraryDependencies += "com.typesafe" % "config" % "1.3.1"

libraryDependencies ++= Seq("org.slf4j" % "slf4j-api" % "1.7.5",
  "org.slf4j" % "slf4j-simple" % "1.7.5")

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.0.0",
  "org.slf4j" % "slf4j-nop" % "1.6.4"
)

libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "2.16.0"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "3.0.1" % "test"
