import Dependencies.*

name := "pekko-sharding-strategy"

organization := "com.evolution"
organizationName := "Evolution"
organizationHomepage := Some(url("https://evolution.com"))
homepage := Some(url("https://github.com/evolution-gaming/pekko-sharding-strategy"))
startYear := Some(2018)

crossScalaVersions := Seq("2.13.16", "3.3.6")
scalaVersion := crossScalaVersions.value.head
scalacOptions ++= Seq(
  "-release:17",
  "-deprecation",
)

scalacOptions ++= crossSettings(
  scalaVersion = scalaVersion.value,
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
  if2 = Seq(
    "-Xsource:3",
  ),
)

Compile / doc / scalacOptions ++= Seq("-groups", "-implicits", "-no-link-warnings")
Compile / doc / scalacOptions -= "-Xfatal-warnings"

publishTo := Some(Resolver.evolutionReleases)
versionPolicyIntention := Compatibility.BinaryCompatible // sbt-version-policy

libraryDependencies ++= Seq(
  `ddata-tools`,
  `cats-helper`,
  Pekko.actor,
  Pekko.`distributed-data`,
  Pekko.cluster,
  Pekko.sharding,
  Pekko.testkit % Test,
  Cats.core,
  Cats.effect,
  scalatest % Test,
)

licenses := Seq(("MIT", url("https://opensource.org/licenses/MIT")))

def crossSettings[T](scalaVersion: String, if3: T, if2: T): T = {
  scalaVersion match {
    case version if version.startsWith("3") => if3
    case _ => if2
  }
}

addCommandAlias("check", "all scalafmtCheckAll scalafmtSbtCheck Compile/doc")
addCommandAlias("fmt", "all scalafmtAll scalafmtSbt") // optional: for development
addCommandAlias("build", "+all compile test")
