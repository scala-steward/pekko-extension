import Dependencies.*

name := "pekko-pubsub"

organization := "com.evolution"

homepage := Some(url("https://github.com/evolution-gaming/pekko-pubsub"))

startYear := Some(2017)

organizationName := "Evolution"

organizationHomepage := Some(url("https://evolution.com"))

scalaVersion := crossScalaVersions.value.head

crossScalaVersions := Seq("2.13.16")

publishTo := Some(Resolver.evolutionReleases)

libraryDependencies ++= Seq(
  Pekko.Actor,
  Pekko.Stream,
  Pekko.ClusterTools,
  Pekko.Testkit % Test,
  Scodec.core,
  Scodec.bits,
  Cats.core,
  Cats.effect,
  scalax,
  `metric-tools`,
  `pekko-serialization`,
  `cats-helper`,
  scache,
  scalatest % Test,
)

licenses := Seq(("MIT", url("https://opensource.org/licenses/MIT")))

scalacOptions ++= Seq(
  "-release:17",
  "-deprecation",
)

Compile / doc / scalacOptions ++= Seq("-groups", "-implicits", "-no-link-warnings")

//addCommandAlias("check", "all versionPolicyCheck scalafmtCheckAll scalafmtSbtCheck")
addCommandAlias("check", "all scalafmtCheckAll scalafmtSbtCheck")
addCommandAlias("fmt", "all scalafmtAll scalafmtSbt")
addCommandAlias("build", "+all compile test")
