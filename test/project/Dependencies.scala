import sbt.*

object Dependencies {

  val scalatest = "org.scalatest" %% "scalatest" % "3.2.19"

  sealed abstract class Pekko(version: String) {
    val actor = "org.apache.pekko" %% "pekko-actor" % version
    val stream = "org.apache.pekko" %% "pekko-stream" % version
    val slf4j = "org.apache.pekko" %% "pekko-slf4j" % version
  }

  object Pekko {
    val older: Pekko = new Pekko("1.0.3") {}
    val default: Pekko = new Pekko("1.1.3") {}
  }

  sealed abstract class PekkoHttp(version: String) {
    val core = "org.apache.pekko" %% "pekko-http-core" % version
    val testkit = "org.apache.pekko" %% "pekko-http-testkit" % version
  }

  object PekkoHttp {
    val older: PekkoHttp = new PekkoHttp("1.1.0") {}
    val default: PekkoHttp = new PekkoHttp("1.2.0") {}
  }
}
