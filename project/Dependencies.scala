import sbt._

object Dependencies {

  val scalatest = "org.scalatest" %% "scalatest" % "3.0.8"

  object Akka {
    private val version = "2.5.26"
    val actor  = "com.typesafe.akka" %% "akka-actor" % version
    val stream = "com.typesafe.akka" %% "akka-stream" % version
  }

  object AkkaHttp {
    private val version = "10.1.9"
    val core = "com.typesafe.akka" %% "akka-http-core" % version
  }
}