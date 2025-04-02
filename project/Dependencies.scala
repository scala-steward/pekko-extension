import sbt.*

object Dependencies {

  val scalatest = "org.scalatest" %% "scalatest" % "3.2.19"

  sealed abstract class Akka(version: String) {
    val actor = "com.typesafe.akka" %% "akka-actor" % version
    val stream = "com.typesafe.akka" %% "akka-stream" % version
    val slf4j = "com.typesafe.akka" %% "akka-slf4j" % version
  }

  object Akka {
    val older: Akka = new Akka("2.6.20") {}
    val default: Akka = new Akka("2.6.21") {}
  }

  sealed abstract class AkkaHttp(version: String) {
    val core = "com.typesafe.akka" %% "akka-http-core" % version
    val testkit = "com.typesafe.akka" %% "akka-http-testkit" % version
  }

  object AkkaHttp {
    val older: AkkaHttp = new AkkaHttp("10.2.9") {}
    val default: AkkaHttp = new AkkaHttp("10.2.10") {}
  }
}
