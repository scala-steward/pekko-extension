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
  .dependsOn(`pekko-extension-serialization`)
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

// TODO add all modules

def crossSettings[T](scalaVersion: String, if3: T, if2: T): T = {
  scalaVersion match {
    case version if version.startsWith("3") => if3
    case _ => if2
  }
}
