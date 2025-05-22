import Dependencies.*
import sbt.Keys.{homepage, organizationName, startYear}

lazy val commonSettings = Seq(
  Compile / doc / scalacOptions ++= Seq("-no-link-warnings"),
  scalaVersion := crossScalaVersions.value.head,
  crossScalaVersions := Seq("2.13.16", "3.3.6"),
  publishTo := Some(Resolver.evolutionReleases),
  versionScheme := Some("semver-spec"),
)

lazy val publishSettings = Seq(
  homepage := Some(url("https://github.com/evolution-gaming/pekko-tools")),
  startYear := Some(2016),
  organizationName := "Evolution",
  organizationHomepage := Some(url("https://evolution.com")),
  organization := "com.evolution",
  licenses := Seq("MIT" -> url("https://www.opensource.org/licenses/mit-license.html")),
)

lazy val allSettings = commonSettings ++ publishSettings

lazy val pekkoTools = project
  .in(file("."))
  .settings(name := "pekko-tools")
  .settings(allSettings)
  .aggregate(instrumentation, cluster, persistence, serialization, util, test)

lazy val instrumentation = project
  .in(file("instrumentation"))
  .dependsOn(util)
  .settings(
    name := "pekko-tools-instrumentation",
    libraryDependencies ++= Seq(
      Pekko.Actor,
      ConfigTools,
      Prometheus.simpleclient,
    ),
  )
  .settings(allSettings)

lazy val cluster = project
  .in(file("cluster"))
  .dependsOn(test % "test->compile")
  .settings(
    name := "pekko-tools-cluster",
    libraryDependencies ++= Seq(
      Pekko.Actor,
      Pekko.Cluster,
      Pekko.ClusterSharding,
      Pekko.TestKit % Test,
      Logging,
      ConfigTools,
      Nel,
      ScalaTest % Test,
    ),
  )
  .settings(allSettings)

lazy val persistence = project
  .in(file("persistence"))
  .dependsOn(serialization, test % "test->compile")
  .settings(
    name := "pekko-tools-persistence",
    libraryDependencies ++= Seq(
      Pekko.Actor,
      ScalaTools,
      ConfigTools,
      Pekko.TestKit % Test,
      ScalaTest % Test,
    ),
  )
  .settings(allSettings)

lazy val serialization = project
  .in(file("serialization"))
  .dependsOn(test % "test->compile")
  .settings(
    name := "pekko-tools-serialization",
    libraryDependencies ++= Seq(
      Pekko.Actor,
      Logging,
      Pekko.Persistence,
      ScalaTest % Test,
    ),
  )
  .settings(allSettings)

lazy val util = project
  .in(file("util"))
  .dependsOn(test % "test->compile")
  .settings(
    name := "pekko-tools-util",
    libraryDependencies ++= Seq(
      Pekko.Actor,
      Pekko.TestKit % Test,
      ScalaTest % Test,
      Logging,
    ),
  )
  .settings(allSettings)

lazy val test = project
  .in(file("test"))
  .settings(
    name := "pekko-tools-test",
    libraryDependencies ++= Seq(
      Pekko.Actor,
      Pekko.TestKit,
      ScalaTest,
    ),
  )
  .settings(allSettings)

addCommandAlias("check", "all scalafmtCheckAll scalafmtSbtCheck")
//addCommandAlias("check", "all versionPolicyCheck scalafmtCheckAll scalafmtSbtCheck")
addCommandAlias("fmt", "all scalafmtAll scalafmtSbt") // optional: for development
addCommandAlias("build", "+all compile test")
