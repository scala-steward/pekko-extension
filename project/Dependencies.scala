import sbt.*

object Dependencies {

  object Evo {
    val MetricTools = "com.evolutiongaming" %% "metric-tools" % "3.0.0"
    val CatsHelper = "com.evolutiongaming" %% "cats-helper" % "3.12.0"
    val SCache = "com.evolution" %% "scache" % "5.1.2"
  }

  object Pekko {
    private val version = "1.1.3"
    val Actor = "org.apache.pekko" %% "pekko-actor" % version
    val ClusterTools = "org.apache.pekko" %% "pekko-cluster-tools" % version
    val Testkit = "org.apache.pekko" %% "pekko-testkit" % version
    val Stream = "org.apache.pekko" %% "pekko-stream" % version
  }

  object Cats {
    val Core = "org.typelevel" %% "cats-core" % "2.13.0"
    val Effect = "org.typelevel" %% "cats-effect" % "3.5.7"
  }

  object Scodec {
    // see https://github.com/scodec/scodec/issues/365
    object Scala2 {
      val Core = "org.scodec" %% "scodec-core" % "1.11.11" // the last scodec-core version built for 2.13
    }
    object Scala3 {
      val Core = "org.scodec" %% "scodec-core" % "2.3.2"
    }
    val Bits = "org.scodec" %% "scodec-bits" % "1.2.1"
  }

  object TestLib {
    val ScalaTest = "org.scalatest" %% "scalatest" % "3.2.19"
  }
}
