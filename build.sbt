import Dependencies.*

val commonSettings = inThisBuild(Seq(
  organization := "com.evolution",
  organizationName := "Evolution",
  organizationHomepage := Some(url("https://evolution.com")),
  homepage := Some(url("https://github.com/evolution-gaming/pekko-extension")),
  startYear := Some(2016),
  crossScalaVersions := Seq("2.13.16", "3.3.6"),
  scalaVersion := crossScalaVersions.value.head,
  scalacOptions ++= Seq(
    "-release:17",
    "-deprecation",
  ),
  scalacOptions ++= crossSettings(
    scalaVersion = scalaVersion.value,
    if2 = Seq(
      "-Xsource:3",
    ),
    // Good compiler options for Scala 2.13 are coming from com.evolution:sbt-scalac-opts-plugin:0.0.9,
    // but its support for Scala 3 is limited, especially what concerns linting options.
    //
    // If Scala 3 is made the primary target, good linting scalac options for it should be added first.
    if3 = Seq(
      "-Ykind-projector:underscores",

      // disable new brace-less syntax:
      // https://alexn.org/blog/2022/10/24/scala-3-optional-braces/
      "-no-indent",

      // improve error messages:
      "-explain",
      "-explain-types",
    ),
  ),
  Compile / doc / scalacOptions ++= Seq("-groups", "-implicits", "-no-link-warnings"),
  Compile / doc / scalacOptions -= "-Xfatal-warnings",
  publishTo := Some(Resolver.evolutionReleases),
  licenses := Seq(("MIT", url("https://opensource.org/licenses/MIT"))),
  libraryDependencySchemes ++= Seq(
    "org.scala-lang.modules" %% "scala-java8-compat" % "always",
    "org.scala-lang.modules" %% "scala-xml" % "always",
  ),
  autoAPIMappings := true,
  versionScheme := Some("early-semver"),
  versionPolicyIntention := Compatibility.BinaryCompatible,
))

val alias =
  addCommandAlias("build", "+all compile test") ++
    addCommandAlias("fmt", "+all scalafmtAll scalafmtSbt") ++
    // `check` is called with `+` in release workflow
    // TODO add `versionPolicyCheck`
    addCommandAlias("check", "all Compile/doc scalafmtCheckAll scalafmtSbtCheck")

val root = project
  .in(file("."))
  .settings(name := "pekko-extension")
  .settings(commonSettings)
  .settings(publish / skip := true)
  .settings(alias)
  .aggregate(
    `pekko-extension-serialization`,
    `pekko-extension-pubsub`,
    `pekko-extension-test-actor`,
    `pekko-extension-test-http`,
    `pekko-extension-distributed-data-tools`,
    `pekko-extension-sharding-strategy`,
    `pekko-extension-tools-test`,
    `pekko-extension-tools-util`,
    `pekko-extension-tools-persistence`,
    `pekko-extension-tools-cluster`,
    `pekko-extension-tools-instrumentation`,
    `pekko-extension-conhub`,
    `pekko-extension-effect-actor`,
    `pekko-extension-effect-testkit`,
    `pekko-extension-effect-actor-tests`,
    `pekko-extension-effect-persistence-api`,
    `pekko-extension-effect-persistence`,
    `pekko-extension-effect-cluster`,
    `pekko-extension-effect-cluster-sharding`,
  )

lazy val `pekko-extension-serialization` = project
  .settings(
    libraryDependencies ++= Seq(
      Pekko.Actor,
      TestLib.ScalaTest % Test,
    ) ++ crossSettings(
      scalaVersion = scalaVersion.value,
      if2 = Seq(
        Scodec.Scala2.Core % Optional,
      ),
      if3 = Seq(
        Scodec.Scala3.Core % Optional,
      ),
    ),
  )

lazy val `pekko-extension-pubsub` = project
  .dependsOn(
    `pekko-extension-serialization`,
  )
  .settings(
    libraryDependencies ++= Seq(
      Pekko.Actor,
      Pekko.Stream,
      Pekko.ClusterTools,
      Pekko.Testkit % Test,
      Cats.Core,
      Cats.Effect,
      Evo.MetricTools,
      Evo.CatsHelper,
      Evo.SCache,
      Scodec.Bits,
      TestLib.ScalaTest % Test,
    ),
  )
  .settings(
    libraryDependencies ++= crossSettings(
      scalaVersion = scalaVersion.value,
      if2 = Seq(
        Scodec.Scala2.Core,
      ),
      if3 = Seq(
        Scodec.Scala3.Core,
      ),
    ),
  )

lazy val `pekko-extension-test-actor` = project
  .settings(
    libraryDependencies ++= Seq(
      Pekko.Actor,
      TestLib.ScalaTest,
      Pekko.OlderSlf4j % Test,
    ),
  )

lazy val `pekko-extension-test-http` = project
  .settings(
    libraryDependencies ++= Seq(
      Pekko.Actor,
      Pekko.Stream,
      PekkoHttp.Core,
      TestLib.ScalaTest,
      PekkoHttp.OlderTestkit % Test,
    ),
  )

lazy val `pekko-extension-distributed-data-tools` = project
  .settings(
    libraryDependencies ++= Seq(
      Pekko.Actor,
      Pekko.Cluster,
      Pekko.DistributedData,
      Pekko.Testkit % Test,
      Cats.Core,
      Cats.Effect,
      Evo.ExecutorTools,
      Evo.CatsHelper,
      Evo.SMetrics,
      TestLib.ScalaTest % Test,
    ),
  )

lazy val `pekko-extension-sharding-strategy` = project
  .dependsOn(
    `pekko-extension-distributed-data-tools`,
  )
  .settings(
    libraryDependencies ++= Seq(
      Pekko.Actor,
      Pekko.DistributedData,
      Pekko.Cluster,
      Pekko.ClusterSharding,
      Pekko.Testkit % Test,
      Cats.Core,
      Cats.Effect,
      Evo.CatsHelper,
      TestLib.ScalaTest % Test,
    ),
  )

lazy val `pekko-extension-tools-test` = project
  .settings(
    libraryDependencies ++= Seq(
      Pekko.Actor,
      Pekko.Testkit,
      TestLib.ScalaTest,
    ),
  )

lazy val `pekko-extension-tools-util` = project
  .dependsOn(`pekko-extension-tools-test` % "test->compile")
  .settings(
    libraryDependencies ++= Seq(
      Pekko.Actor,
      Misc.Logging,
      Pekko.Testkit % Test,
      TestLib.ScalaTest % Test,
    ),
  )

lazy val `pekko-extension-tools-serialization` = project
  .dependsOn(
    `pekko-extension-tools-test` % "test->compile",
  )
  .settings(
    libraryDependencies ++= Seq(
      Pekko.Actor,
      Pekko.Persistence,
      Misc.Logging,
      TestLib.ScalaTest % Test,
    ),
  )

lazy val `pekko-extension-tools-persistence` = project
  .dependsOn(
    `pekko-extension-tools-serialization`,
    `pekko-extension-tools-test` % "test->compile",
  )
  .settings(
    libraryDependencies ++= Seq(
      Pekko.Actor,
      Evo.ScalaTools,
      Evo.ConfigTools,
      Pekko.Testkit % Test,
      TestLib.ScalaTest % Test,
    ),
  )

lazy val `pekko-extension-tools-cluster` = project
  .dependsOn(
    `pekko-extension-tools-test` % "test->compile",
  )
  .settings(
    libraryDependencies ++= Seq(
      Pekko.Actor,
      Pekko.Cluster,
      Pekko.ClusterSharding,
      Pekko.Testkit % Test,
      Misc.Logging,
      Evo.ConfigTools,
      Cats.Core,
      TestLib.ScalaTest % Test,
    ),
  )

lazy val `pekko-extension-tools-instrumentation` = project
  .dependsOn(
    `pekko-extension-tools-util`,
  )
  .settings(
    libraryDependencies ++= Seq(
      Pekko.Actor,
      Evo.ConfigTools,
      Prometheus.simpleclient,
    ),
  )

lazy val `pekko-extension-conhub` = project
  .dependsOn(
    `pekko-extension-serialization`,
    `pekko-extension-tools-test` % "test->compile",
  )
  .settings(
    libraryDependencies ++= Seq(
      Scodec.Bits,
      Pekko.Actor,
      Pekko.Remote,
      Pekko.Cluster,
      Pekko.Stream,
      Pekko.Protobuf,
      Evo.ConfigTools,
      Evo.FutureHelper,
      Evo.Sequentially,
      Misc.Logging,
      Cats.Core,
      Evo.SCache % Test,
      Pekko.Testkit % Test,
      TestLib.ScalaTest % Test,
    ),
  )
  .settings(
    libraryDependencies ++= crossSettings(
      scalaVersion = scalaVersion.value,
      if2 = Seq(
        Scodec.Scala2.Core,
      ),
      if3 = Seq(
        Scodec.Scala3.Core,
      ),
    ),
  )

lazy val `pekko-extension-effect-actor` = project
  .settings(
    libraryDependencies ++= Seq(
      Pekko.Actor,
      Cats.Core,
      Cats.Effect,
      Evo.CatsHelper,
      Pekko.Slf4j % Test,
      Pekko.Testkit % Test,
      Logback.Classic % Test,
      Logback.Core % Test,
      Slf4j.Api % Test,
      Slf4j.Log4jOverSlf4j % Test,
      TestLib.ScalaTest % Test,
    ),
  )
  .settings(
    libraryDependencies ++= crossSettings(
      scalaVersion.value,
      if2 = Seq(compilerPlugin(Misc.KindProjector cross CrossVersion.full)),
      if3 = Nil,
    ),
  )

lazy val `pekko-extension-effect-testkit` = project
  .dependsOn(
    `pekko-extension-effect-actor`,
  )
  .settings(
    libraryDependencies ++= Seq(
      Pekko.Actor,
      Cats.Effect,
      Evo.CatsHelper,
      Pekko.Testkit % Test,
      TestLib.ScalaTest % Test,
    ),
  )
  .settings(
    libraryDependencies ++= crossSettings(
      scalaVersion.value,
      if2 = Seq(compilerPlugin(Misc.KindProjector cross CrossVersion.full)),
      if3 = Nil,
    ),
  )

lazy val `pekko-extension-effect-actor-tests` = project
  .dependsOn(
    `pekko-extension-effect-actor` % "test->test;compile->compile",
    `pekko-extension-effect-testkit` % "test->test;test->compile",
  )
  .settings(
    libraryDependencies ++= Seq(
      Pekko.Testkit % Test,
    ),
  )
  .settings(
    libraryDependencies ++= crossSettings(
      scalaVersion.value,
      if2 = Seq(compilerPlugin(Misc.KindProjector cross CrossVersion.full)),
      if3 = Nil,
    ),
  )

lazy val `pekko-extension-effect-persistence-api` = project
  .dependsOn(
    `pekko-extension-effect-actor` % "test->test;compile->compile",
    `pekko-extension-effect-testkit` % "test->test;test->compile",
    `pekko-extension-effect-actor-tests` % "test->test",
  )
  .settings(
    Compile / doc / scalacOptions -= "-Xfatal-warnings", // TODO get rid of this
  )
  .settings(
    libraryDependencies ++= Seq(
      Cats.Core,
      Cats.Effect,
      Evo.CatsHelper,
      Evo.SStream,
      Pekko.Slf4j % Test,
      Pekko.Testkit % Test,
      TestLib.ScalaTest % Test,
    ),
  )

lazy val `pekko-extension-effect-persistence` = project
  .dependsOn(
    `pekko-extension-effect-persistence-api` % "test->test;compile->compile",
    `pekko-extension-effect-actor` % "test->test;compile->compile",
    `pekko-extension-effect-testkit` % "test->test;test->compile",
    `pekko-extension-effect-actor-tests` % "test->test",
  )
  .settings(
    Compile / doc / scalacOptions -= "-Xfatal-warnings", // TODO get rid of this
  )
  .settings(
    libraryDependencies ++= Seq(
      Pekko.Actor,
      Pekko.Stream,
      Pekko.Persistence,
      Pekko.PersistenceQuery,
      Cats.Core,
      Cats.Effect,
      Evo.CatsHelper,
      Evo.SMetrics,
      Pekko.PersistenceTestkit % Test,
      Pekko.Slf4j % Test,
      Pekko.Testkit % Test,
      TestLib.ScalaTest % Test,
    ),
  )
  .settings(
    libraryDependencies ++= crossSettings(
      scalaVersion = scalaVersion.value,
      if2 = List(Pureconfig.Pureconfig),
      if3 = List(Pureconfig.Scala3.Cores, Pureconfig.Scala3.Generic),
    ),
  )
  .settings(
    libraryDependencies ++= crossSettings(
      scalaVersion.value,
      if2 = Seq(compilerPlugin(Misc.KindProjector cross CrossVersion.full)),
      if3 = Nil,
    ),
  )

lazy val `pekko-extension-effect-cluster` = project
  .dependsOn(
    `pekko-extension-effect-actor` % "test->test;compile->compile",
    `pekko-extension-effect-testkit` % "test->test;test->compile",
    `pekko-extension-effect-actor-tests` % "test->test",
  )
  .settings(
    libraryDependencies ++= Seq(
      Pekko.Cluster,
      Pekko.ClusterTyped % Test,
    ),
  )
  .settings(
    libraryDependencies ++= crossSettings(
      scalaVersion = scalaVersion.value,
      if2 = List(Pureconfig.Pureconfig),
      if3 = List(Pureconfig.Scala3.Cores, Pureconfig.Scala3.Generic),
    ),
  )

lazy val `pekko-extension-effect-cluster-sharding` = project
  .dependsOn(
    `pekko-extension-effect-cluster` % "test->test;compile->compile",
    `pekko-extension-effect-persistence` % "test->test;compile->compile",
  )
  .settings(
    libraryDependencies ++= Seq(
      Pekko.ClusterSharding,
    ),
  )
  .settings(
    libraryDependencies ++= crossSettings(
      scalaVersion.value,
      if2 = Seq(compilerPlugin(Misc.KindProjector cross CrossVersion.full)),
      if3 = Nil,
    ),
  )

lazy val `pekko-extension-effect-eventsourcing` = project
  .dependsOn(
    `pekko-extension-effect-persistence` % "test->test;compile->compile",
  )
  .settings(
    libraryDependencies ++= Seq(
      Pekko.Stream,
      Evo.Retry,
    ),
  )
  .settings(
    libraryDependencies ++= crossSettings(
      scalaVersion.value,
      if2 = Seq(compilerPlugin(Misc.KindProjector cross CrossVersion.full)),
      if3 = Nil,
    ),
  )
  .settings(
    libraryDependencies ++= crossSettings(
      scalaVersion = scalaVersion.value,
      if2 = Nil,
      if3 = List(Pureconfig.Scala3.Generic),
    ),
  )

def crossSettings[T](scalaVersion: String, if3: T, if2: T): T = {
  scalaVersion match {
    case version if version.startsWith("3") => if3
    case _ => if2
  }
}
