import Dependencies.*

lazy val commonSettings = Seq(
  organization := "com.evolution",
  homepage := Some(url("https://github.com/evolution-gaming/pekko-test")),
  startYear := Some(2019),
  organizationName := "Evolution",
  organizationHomepage := Some(url("https://evolution.com")),
  crossScalaVersions := Seq("2.13.16", "3.3.6"),
  scalaVersion := crossScalaVersions.value.head,
  scalacOptions ++= Seq("-release:17", "-deprecation"),
  scalacOptions ++= {
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, n)) => Seq("-Xsource:3")
      case _ => Nil
    }
  },
  Compile / doc / scalacOptions ++= Seq("-groups", "-implicits", "-no-link-warnings"),
  licenses := Seq(("MIT", url("https://opensource.org/licenses/MIT"))),
  publishTo := Some(Resolver.evolutionReleases),
  versionPolicyIntention := Compatibility.BinaryCompatible,
)

val aliases: Seq[sbt.Def.Setting[?]] =
  addCommandAlias("fmt", "all scalafmtAll scalafmtSbt") ++
    addCommandAlias("check", "all scalafmtCheckAll scalafmtSbtCheck Compile/doc") ++ // TODO add `versionPolicyCheck`
    addCommandAlias("build", "+all test package")

lazy val root = project.in(file("."))
  .settings(name := "pekko-test")
  .settings(commonSettings)
  .settings(aliases)
  .settings(
    publish / skip := true,
  )
  .aggregate(actor, http)

lazy val actor = project
  .settings(name := "pekko-test-actor")
  .settings(commonSettings)
  .settings(libraryDependencies ++= Seq(
    Pekko.default.actor,
    scalatest,
    Pekko.older.slf4j % Test,
  ))

lazy val http = project
  .settings(name := "pekko-test-http")
  .settings(commonSettings)
  .settings(libraryDependencies ++= Seq(
    Pekko.default.actor,
    Pekko.default.stream,
    PekkoHttp.default.core,
    scalatest,
    PekkoHttp.older.testkit % Test,
  ))
