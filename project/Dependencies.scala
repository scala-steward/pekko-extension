import sbt._

object Dependencies {

  val scalatest     = "org.scalatest"       %% "scalatest"   % "3.2.19"
  val `cats-helper` = "com.evolutiongaming" %% "cats-helper" % "3.12.0"
  val `ddata-tools` = "com.evolution"       %% "pekko-ddata-tools" % "0.0.1"

  object Cats {
    private val version = "2.13.0"
    private val effectVersion = "3.5.7"
    val core   = "org.typelevel" %% "cats-core"   % version
    val effect = "org.typelevel" %% "cats-effect" % effectVersion
  }

  object Pekko {
    private val version = "1.1.3"
    val actor              = "org.apache.pekko" %% "pekko-actor"           % version
    val cluster            = "org.apache.pekko" %% "pekko-cluster"         % version
    val sharding           = "org.apache.pekko" %% "pekko-cluster-sharding" % version
    val `distributed-data` = "org.apache.pekko" %% "pekko-distributed-data" % version
    val testkit            = "org.apache.pekko" %% "pekko-testkit"         % version
  }
}
