import Dependencies.*

lazy val crossScalaVersionsWithout3Val = Seq("2.13.16", "2.12.20")
lazy val crossScalaVersionsVal = crossScalaVersionsWithout3Val :+ "3.3.5"

lazy val commonSettings = Seq(
  organization := "com.evolutiongaming",
  homepage := Some(url("https://github.com/evolution-gaming/akka-test")),
  startYear := Some(2019),
  organizationName := "Evolution",
  organizationHomepage := Some(url("https://evolution.com")),
  scalaVersion := crossScalaVersionsVal.head,
  Compile / doc / scalacOptions ++= Seq("-groups", "-implicits", "-no-link-warnings"),
  licenses := Seq(("MIT", url("https://opensource.org/licenses/MIT"))),
  publishTo := Some(Resolver.evolutionReleases),
  versionPolicyIntention := Compatibility.BinaryCompatible,
)

val aliases: Seq[sbt.Def.Setting[?]] =
  addCommandAlias("fmt", "all scalafmtAll scalafmtSbt") ++
    addCommandAlias(
      "check",
      "all scalafmtCheckAll scalafmtSbtCheck versionPolicyCheck Compile/doc",
    ) ++
    addCommandAlias("build", "all test package")

lazy val root = project.in(file("."))
  .settings(name := "akka-test")
  .settings(inThisBuild(commonSettings))
  .settings(aliases)
  .settings(
    publish / skip := true,
  )
  .aggregate((
    actor.projectRefs ++
      http.projectRefs
  ) *)

lazy val actor = projectMatrix.in(file("actor"))
  .settings(name := "akka-test-actor")
  .jvmPlatform(
    scalaVersions = crossScalaVersionsVal,
  )
  .settings(libraryDependencies ++= Seq(
    Akka.default.actor,
    scalatest,
    Akka.older.slf4j % Test,
  ))

lazy val http = projectMatrix.in(file("http"))
  .settings(name := "akka-test-http")
  .jvmPlatform(
    scalaVersions = crossScalaVersionsWithout3Val,
  )
  .settings(libraryDependencies ++= Seq(
    Akka.default.actor,
    Akka.default.stream,
    AkkaHttp.default.core,
    scalatest,
    AkkaHttp.older.testkit % Test,
  ))
