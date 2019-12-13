import sbt._

object Dependencies {

  val scalatest = "org.scalatest" %% "scalatest" % "3.1.0"

  object Akka {
    private val version = "2.6.1"
    val actor  = "com.typesafe.akka" %% "akka-actor" % version
    val stream = "com.typesafe.akka" %% "akka-stream" % version
  }

  object AkkaHttp {
    private val version = "10.1.11"
    val http = "com.typesafe.akka" %% "akka-http" % version
  }
}