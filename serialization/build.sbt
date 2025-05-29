import Dependencies.*

name := "pekko-serialization"

organization := "com.evolution"

homepage := Some(url("https://github.com/evolution-gaming/pekko-serialization"))

startYear := Some(2018)

organizationName := "Evolution"

organizationHomepage := Some(url("https://evolution.com"))

crossScalaVersions := Seq("3.3.6", "2.13.16")
scalaVersion       := crossScalaVersions.value.head

scalacOptions ++= {
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, 13)) => Seq("-Xsource:3", "-Ytasty-reader")
    case _ =>
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
}
//see https://github.com/scodec/scodec/issues/365
libraryDependencies ++= (CrossVersion.partialVersion(scalaVersion.value) match {
  case Some((3, _)) =>
    Seq(
      Scodec.core % Optional,
    )
  case _ =>
    Seq(
      Scodec.core2 % Optional,
    )
})
libraryDependencies ++= Seq(
  Pekko.actor,
  scalatest % Test,
)

publishTo := Some(Resolver.evolutionReleases)

licenses := Seq(("MIT", url("https://opensource.org/licenses/MIT")))

Compile / doc / scalacOptions ++= Seq("-groups", "-implicits", "-no-link-warnings")

addCommandAlias("check", "all versionPolicyCheck scalafmtCheckAll scalafmtSbtCheck")
addCommandAlias("fmt", "all scalafmtAll scalafmtSbt")
addCommandAlias("build", "+all compile test")

// Your next release will be binary compatible with the previous one,
// but it may not be source compatible (i.e. it will be a minor release).
ThisBuild / versionPolicyIntention := Compatibility.BinaryCompatible
ThisBuild / versionScheme          := Some("early-semver")
