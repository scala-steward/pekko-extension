import Dependencies.*

lazy val crossScalaVersionsWithout3Val = Seq("2.13.16", "2.12.20")
lazy val crossScalaVersionsVal = crossScalaVersionsWithout3Val :+ "3.3.5"

lazy val commonSettings = Seq(
  organization := "com.evolutiongaming",
  homepage := Some(url("https://github.com/evolution-gaming/akka-test")),
  startYear := Some(2019),
  organizationName := "Evolution",
  organizationHomepage := Some(url("https://evolution.com")),
  scalaVersion := crossScalaVersions.value.head,
  crossScalaVersions := crossScalaVersionsVal,
  Compile / doc / scalacOptions ++= Seq("-groups", "-implicits", "-no-link-warnings"),
  licenses := Seq(("MIT", url("https://opensource.org/licenses/MIT"))),
  publishTo := Some(Resolver.evolutionReleases),
  versionPolicyIntention := Compatibility.BinaryCompatible,
)

val aliases: Seq[sbt.Def.Setting[?]] =
  addCommandAlias("fmt", plus4HeteroScalaVersions("all scalafmtAll scalafmtSbt")) ++
    addCommandAlias(
      "check", // check is used by the release.yml@v3
      plus4HeteroScalaVersions("all scalafmtCheckAll scalafmtSbtCheck versionPolicyCheck Compile/doc"),
    ) ++
    addCommandAlias(
      "checkOne",
      "all scalafmtCheckAll scalafmtSbtCheck versionPolicyCheck Compile/doc",
    ) ++
    addCommandAlias("build", plus4HeteroScalaVersions("all compile test"))

// workaround for +all not working if you have modules with different cross-compilation Scala version sets
def plus4HeteroScalaVersions(command: String): String = {
  crossScalaVersionsVal.map { scalaVer =>
    s"++$scalaVer $command"
  }.mkString(
    start = "; ",
    sep = "; ",
    end = "",
  )
}

lazy val root = project.in(file("."))
  .settings(name := "akka-test")
  .settings(inThisBuild(commonSettings))
  .settings(aliases)
  .settings(publish / skip := true)
  .aggregate(actor, http)

lazy val actor = project.in(file("actor"))
  .settings(name := "akka-test-actor")
  .settings(libraryDependencies ++= Seq(
    Akka.default.actor,
    scalatest,
    Akka.older.slf4j % Test,
  ))

lazy val http = project.in(file("http"))
  .settings(name := "akka-test-http")
  .settings(
    crossScalaVersions := crossScalaVersionsWithout3Val,
  )
  .settings(libraryDependencies ++= Seq(
    Akka.default.actor,
    Akka.default.stream,
    AkkaHttp.default.core,
    scalatest,
    AkkaHttp.older.testkit % Test,
  ))
