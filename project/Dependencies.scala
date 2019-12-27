import sbt._

object Dependencies {

  val scalatest = "org.scalatest" %% "scalatest" % "3.1.0"


  sealed abstract class Akka(version: String) {
    val actor  = "com.typesafe.akka" %% "akka-actor"  % version
    val stream = "com.typesafe.akka" %% "akka-stream" % version
    val slf4j  = "com.typesafe.akka" %% "akka-slf4j"  % version
  }

  object Akka {
    val default: Akka = new Akka("2.5.27") {}
    val newer  : Akka = new Akka("2.6.1") {}
  }

  
  sealed abstract class AkkaHttp(version: String) {
    val core    = "com.typesafe.akka" %% "akka-http-core"    % version
    val testkit = "com.typesafe.akka" %% "akka-http-testkit" % version
  }

  object AkkaHttp {
    val default: AkkaHttp = new AkkaHttp("10.1.9") {}
    val newer  : AkkaHttp = new AkkaHttp("10.1.11") {}
  }
}