import sbt.*

object Dependencies {

  object Pekko {
    private val version = "1.1.3"
    val Stream = "org.apache.pekko" %% "pekko-stream" % version
    val Actor = "org.apache.pekko" %% "pekko-actor" % version
    val Persistence = "org.apache.pekko" %% "pekko-persistence" % version
    val Cluster = "org.apache.pekko" %% "pekko-cluster" % version
    val ClusterSharding = "org.apache.pekko" %% "pekko-cluster-sharding" % version
    val TestKit = "org.apache.pekko" %% "pekko-testkit" % version
  }

  val ScalaTest = "org.scalatest" %% "scalatest" % "3.2.19"
  val Logback = "ch.qos.logback" % "logback-classic" % "1.2.3"
  val Logging = "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5"
  val Nel = "com.evolutiongaming" %% "nel" % "1.3.5"
  val ScalaTools = "com.evolutiongaming" %% "scala-tools" % "3.0.6"
  val ConfigTools = "com.evolutiongaming" %% "config-tools" % "1.0.5"

  object Prometheus {
    private val version = "0.9.0"
    val simpleclient = "io.prometheus" % "simpleclient" % version
  }
}
