import sbt.*

object Dependencies {

  val scalatest = "org.scalatest" %% "scalatest" % "3.2.19"

  object Pekko {
    private val version = "1.1.3"
    val actor           = "org.apache.pekko" %% "pekko-actor" % version
  }

  object Scodec {
    val core  = "org.scodec" %% "scodec-core" % "2.3.2"
    val core2 = "org.scodec" %% "scodec-core" % "1.11.11"
  }
}
