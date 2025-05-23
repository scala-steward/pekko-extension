import sbt.*

object Dependencies {

  val `config-tools`        = "com.evolutiongaming"        %% "config-tools"        % "1.0.5"
  val `future-helper`       = "com.evolutiongaming"        %% "future-helper"       % "1.0.7"
  val sequentially          = "com.evolutiongaming"        %% "sequentially"        % "1.2.0"
  val `pekko-serialization` = "com.evolution"              %% "pekko-serialization" % "0.0.1"
  val nel                   = "com.evolutiongaming"        %% "nel"                 % "1.3.5"
  val `scala-tools`         = "com.evolutiongaming"        %% "scala-tools"         % "3.0.6"
  val scalatest             = "org.scalatest"              %% "scalatest"           % "3.2.19"
  val `scala-logging`       = "com.typesafe.scala-logging" %% "scala-logging"       % "3.9.5"
  val `scodec-bits`         = "org.scodec"                 %% "scodec-bits"         % "1.2.1"
  val `scodec-core1`        = "org.scodec"                 %% "scodec-core"         % "1.11.10"
  val `scodec-core2`        = "org.scodec"                 %% "scodec-core"         % "2.3.1"

  object Pekko {
    private val version = "1.1.3"
    val actor           = "org.apache.pekko" %% "pekko-actor"       % version
    val remote          = "org.apache.pekko" %% "pekko-remote"      % version
    val cluster         = "org.apache.pekko" %% "pekko-cluster"     % version
    val testkit         = "org.apache.pekko" %% "pekko-testkit"     % version
    val stream          = "org.apache.pekko" %% "pekko-stream"      % version
    val protobuf        = "org.apache.pekko" %% "pekko-protobuf-v3" % version
  }

  object PekkoTools {
    private val version = "0.0.1"
    val test            = "com.evolution" %% "pekko-tools-test" % version
  }
}
