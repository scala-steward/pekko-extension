import sbt._

object Dependencies {

  val `executor-tools` = "com.evolutiongaming" %% "executor-tools" % "1.0.5"
  val scalatest        = "org.scalatest"       %% "scalatest"      % "3.2.19"
  val `cats-helper`    = "com.evolutiongaming" %% "cats-helper"    % "3.12.0"
  val smetrics         = "com.evolutiongaming" %% "smetrics"       % "2.3.1"

  object Cats {
    val core   = "org.typelevel" %% "cats-core"   % "2.13.0"
    val effect = "org.typelevel" %% "cats-effect" % "3.5.7"
  }

  object Pekko {
    private val version = "1.1.3"
    val actor              = "org.apache.pekko" %% "pekko-actor"            % version
    val cluster            = "org.apache.pekko" %% "pekko-cluster"          % version
    val `distributed-data` = "org.apache.pekko" %% "pekko-distributed-data" % version
    val testkit            = "org.apache.pekko" %% "pekko-testkit"          % version
  }
}
