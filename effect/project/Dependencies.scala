import sbt._

object Dependencies {

  val scalatest                   = "org.scalatest"         %% "scalatest"                 % "3.2.19"
  val `cats-helper`               = "com.evolutiongaming"   %% "cats-helper"               % "3.12.0"
  val retry                       = "com.evolutiongaming"   %% "retry"                     % "3.1.0"
  val `kind-projector`            = "org.typelevel"          % "kind-projector"            % "0.13.3"
  val pureconfig                  = "com.github.pureconfig" %% "pureconfig"                % "0.17.8"
  val `pureconfig-scala3`         = "com.github.pureconfig" %% "pureconfig-core"           % "0.17.8"
  val `pureconfig-generic-scala3` = "com.github.pureconfig" %% "pureconfig-generic-scala3" % "0.17.8"
  val smetrics                    = "com.evolutiongaming"   %% "smetrics"                  % "2.3.1"
  val sstream                     = "com.evolutiongaming"   %% "sstream"                   % "1.1.0"

  object Cats {
    private val version = "2.13.0"
    val core            = "org.typelevel" %% "cats-core" % version
  }

  object CatsEffect {
    private val version = "3.5.7"
    val effect          = "org.typelevel" %% "cats-effect" % version
  }

  object Pekko {
    private val version       = "1.1.3"
    val actor                 = "org.apache.pekko" %% "pekko-actor"               % version
    val testkit               = "org.apache.pekko" %% "pekko-testkit"             % version
    val stream                = "org.apache.pekko" %% "pekko-stream"              % version
    val persistence           = "org.apache.pekko" %% "pekko-persistence"         % version
    val `persistence-testkit` = "org.apache.pekko" %% "pekko-persistence-testkit" % version
    val `persistence-query`   = "org.apache.pekko" %% "pekko-persistence-query"   % version
    val cluster               = "org.apache.pekko" %% "pekko-cluster"             % version
    val `cluster-typed`       = "org.apache.pekko" %% "pekko-cluster-typed"       % version
    val `cluster-sharding`    = "org.apache.pekko" %% "pekko-cluster-sharding"    % version
    val slf4j                 = "org.apache.pekko" %% "pekko-slf4j"               % version
  }

  object Logback {
    private val version = "1.5.18"
    val core            = "ch.qos.logback" % "logback-core"    % version
    val classic         = "ch.qos.logback" % "logback-classic" % version
  }

  object Slf4j {
    private val version    = "2.0.17"
    val api                = "org.slf4j" % "slf4j-api"        % version
    val `log4j-over-slf4j` = "org.slf4j" % "log4j-over-slf4j" % version
  }
}
