import Dependencies._

name := "pekko-ddata-tools"

organization := "com.evolutiongaming"

homepage := Some(url("https://github.com/evolution-gaming/pekko-ddata-tools"))

startYear := Some(2018)

organizationName := "Evolution"

organizationHomepage := Some(url("https://evolution.com"))

scalaVersion := crossScalaVersions.value.head

crossScalaVersions := Seq("2.13.11")

Compile / doc / scalacOptions ++= Seq("-no-link-warnings")

publishTo := Some(Resolver.evolutionReleases)

libraryDependencies ++= Seq(
  Pekko.actor,
  Pekko.cluster,
  Pekko.`distributed-data`,
  Pekko.testkit % Test,
  Cats.core,
  Cats.effect,
  `executor-tools`,
  `cats-helper`,
  smetrics,
  scalatest % Test,
)

licenses := Seq(("MIT", url("https://opensource.org/licenses/MIT")))

releaseCrossBuild := true

scalacOptsFailOnWarn := Some(false)

//addCommandAlias("check", "all versionPolicyCheck Compile/doc")
addCommandAlias("check", "show version")
addCommandAlias("build", "+all compile test")
