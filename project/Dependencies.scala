import sbt._

object Dependencies {

  val `pekko-serialization` = "com.evolution"       %% "pekko-serialization" % "0.0.1"
  val nel                   = "com.evolutiongaming" %% "nel"                 % "1.3.4"
  val `metric-tools`        = "com.evolutiongaming" %% "metric-tools"        % "2.0.0"
  val `cats-helper`         = "com.evolutiongaming" %% "cats-helper"         % "3.12.0"
  val scache                = "com.evolution"       %% "scache"              % "5.1.2"
  val scalatest             = "org.scalatest"       %% "scalatest"           % "3.2.19"
  val scalax                = "com.github.t3hnar"   %% "scalax"              % "3.8.1"

  object Pekko {
    private val version = "1.1.3"
    val Actor           = "org.apache.pekko" %% "pekko-actor"         % version
    val ClusterTools    = "org.apache.pekko" %% "pekko-cluster-tools" % version
    val Testkit         = "org.apache.pekko" %% "pekko-testkit"       % version
    val Stream          = "org.apache.pekko" %% "pekko-stream"        % version
  }

  object Scodec {
    val core = "org.scodec" %% "scodec-core" % "1.11.6"
    val bits = "org.scodec" %% "scodec-bits" % "1.1.14"
  }

  object Cats {
    val core   = "org.typelevel" %% "cats-core"   % "2.13.0"
    val effect = "org.typelevel" %% "cats-effect" % "3.5.7"
  }
}
