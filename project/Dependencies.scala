import sbt.*

object Dependencies {

  object Evo {
    val MetricTools = "com.evolutiongaming" %% "metric-tools" % "3.0.0"
    val CatsHelper = "com.evolutiongaming" %% "cats-helper" % "3.12.0"
    val SCache = "com.evolution" %% "scache" % "5.1.2"
    val ExecutorTools = "com.evolutiongaming" %% "executor-tools" % "1.0.5"
    val SMetrics = "com.evolutiongaming" %% "smetrics" % "2.3.1"
    val ScalaTools = "com.evolutiongaming" %% "scala-tools" % "3.0.6"
    val ConfigTools = "com.evolutiongaming" %% "config-tools" % "1.0.5"
    val Nel = "com.evolutiongaming" %% "nel" % "1.3.5" // TODO drop
    val FutureHelper = "com.evolutiongaming" %% "future-helper" % "1.0.7"
    val Sequentially = "com.evolutiongaming" %% "sequentially" % "1.2.0"
  }

  object Pekko {
    private val version = "1.1.3"
    val Actor = "org.apache.pekko" %% "pekko-actor" % version
    val Cluster = "org.apache.pekko" %% "pekko-cluster" % version
    val ClusterTools = "org.apache.pekko" %% "pekko-cluster-tools" % version
    val Testkit = "org.apache.pekko" %% "pekko-testkit" % version
    val Stream = "org.apache.pekko" %% "pekko-stream" % version
    val Slf4j = "org.apache.pekko" %% "pekko-slf4j" % version
    val DistributedData = "org.apache.pekko" %% "pekko-distributed-data" % version
    val ClusterSharding = "org.apache.pekko" %% "pekko-cluster-sharding" % version
    val Persistence = "org.apache.pekko" %% "pekko-persistence" % version
    val Remote = "org.apache.pekko" %% "pekko-remote" % version
    val Protobuf = "org.apache.pekko" %% "pekko-protobuf-v3" % version

    val OlderSlf4j = "org.apache.pekko" %% "pekko-slf4j" % "1.0.3"
  }

  object PekkoHttp {
    private val version = "1.2.0"
    val Core = "org.apache.pekko" %% "pekko-http-core" % version
    val Testkit = "org.apache.pekko" %% "pekko-http-testkit" % version

    val OlderTestkit = "org.apache.pekko" %% "pekko-http-testkit" % "1.1.0"
  }

  object Cats {
    val Core = "org.typelevel" %% "cats-core" % "2.13.0"
    val Effect = "org.typelevel" %% "cats-effect" % "3.5.7"
  }

  object Scodec {
    val Bits = "org.scodec" %% "scodec-bits" % "1.2.1"
    // see https://github.com/scodec/scodec/issues/365
    object Scala2 {
      val Core = "org.scodec" %% "scodec-core" % "1.11.11" // the last scodec-core version built for 2.13
    }
    object Scala3 {
      val Core = "org.scodec" %% "scodec-core" % "2.3.2"
    }
  }

  object Misc {
    val Logging = "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5"
  }

  object Prometheus {
    private val version = "0.9.0"
    val simpleclient = "io.prometheus" % "simpleclient" % version
  }

  object TestLib {
    val ScalaTest = "org.scalatest" %% "scalatest" % "3.2.19"
  }
}
