import sbt.*

object Dependencies {

  object Pekko {
    private val version = "1.1.3"
    val Actor = "org.apache.pekko" %% "pekko-actor" % version
  }

  object Scodec {
    object Scala2 {
      val core = "org.scodec" %% "scodec-core" % "1.11.11" // the last scodec-core version built for 2.13
    }
    object Scala3 {
      val core = "org.scodec" %% "scodec-core" % "2.3.2"
    }
    val bits = "org.scodec" %% "scodec-bits" % "1.2.1"
  }

  object TestLib {
    val ScalaTest = "org.scalatest" %% "scalatest" % "3.2.19"
  }
}
