import Dependencies.*

name := "pekko-conhub"

organization := "com.evolution"

homepage := Some(url("https://github.com/evolution-gaming/pekko-conhub"))

startYear := Some(2018)

organizationName := "Evolution"

organizationHomepage := Some(url("https://evolution.com"))

scalaVersion := crossScalaVersions.value.head

crossScalaVersions := Seq("2.13.16", "3.3.6")

Compile / scalacOptions ++= {
  if (scalaBinaryVersion.value == "2.13")
    Seq(
      "-Xsource:3",
    )
  else
    Seq(
      "-Ykind-projector:underscores",

      // disable new brace-less syntax:
      // https://alexn.org/blog/2022/10/24/scala-3-optional-braces/
      "-no-indent",

      // improve error messages:
      "-explain",
      "-explain-types",
    )
}

Test / fork := true

publishTo := Some(Resolver.evolutionReleases)

libraryDependencies ++= Seq(
  `scodec-bits`,
  Pekko.actor,
  Pekko.remote,
  Pekko.cluster,
  Pekko.testkit % Test,
  Pekko.stream,
  Pekko.protobuf,
  PekkoTools.test % Test,
  `config-tools`,
  `future-helper`,
  sequentially,
  `scala-logging`,
  `pekko-serialization`,
  cats,
  `scala-tools` % Test,
  scalatest     % Test,
)

libraryDependencies ++= {
  if (scalaBinaryVersion.value == "2.13")
    Seq(
      `scodec-core1`,
    )
  else
    Seq(
      `scodec-core2`,
    )
}

licenses := Seq(("MIT", url("https://opensource.org/licenses/MIT")))

Compile / doc / scalacOptions ++= Seq("-groups", "-implicits", "-no-link-warnings")

// Your next release will be binary compatible with the previous one,
// but it may not be source compatible (i.e. it will be a minor release).
ThisBuild / versionPolicyIntention := Compatibility.BinaryCompatible
ThisBuild / versionScheme          := Some("early-semver")

addCommandAlias("check", "all versionPolicyCheck scalafmtCheckAll scalafmtSbtCheck")
addCommandAlias("fmt", "all scalafmtAll scalafmtSbt")
addCommandAlias("build", "+all compile test")
