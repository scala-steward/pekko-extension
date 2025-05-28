# pekko-test 
[![Build Status](https://github.com/evolution-gaming/pekko-test/workflows/CI/badge.svg)](https://github.com/evolution-gaming/pekko-test/actions?query=workflow%3ACI)
[![Test Coverage Status](https://coveralls.io/repos/github/evolution-gaming/pekko-test/badge.svg?branch=master)](https://coveralls.io/github/evolution-gaming/pekko-test?branch=master)
[![Codacy](https://api.codacy.com/project/badge/Grade/799b059200e14801ac572ca5b86cc48e)](https://app.codacy.com/gh/evolution-gaming/pekko-test/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)
[![Version](https://img.shields.io/badge/version-click-blue)](https://evolution.jfrog.io/artifactory/api/search/latestVersion?g=com.evolution&a=pekko-test-actor_2.13&repos=public)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellowgreen.svg)](https://opensource.org/licenses/MIT)

It is forked from [akka-test](https://github.com/evolution-gaming/akka-test) at v0.3.0 by replacing `akka` with `pekko`.

This library is created to provide a reusable set of tests to be used in projects dependent on [Pekko](https://pekko.apache.org)
libraries. For instance, to prevent the following "surprise" at runtime:

```
java.lang.IllegalStateException: You are using version 1.2.0 of Pekko HTTP, but it appears you (perhaps indirectly) also depend on older versions of related artifacts. You can solve this by adding an explicit dependency on version 1.2.0 of the [pekko-http, pekko-http-testkit] artifacts to your project. Here's a complete collection of detected artifacts: (1.1.0, [pekko-http, pekko-http-testkit]), (1.2.0, [pekko-http-core, pekko-parsing]). See also: https://pekko.apache.org/docs/pekko/current/common/binary-compatibility-rules.html#mixed-versioning-is-not-allowed
	at org.apache.pekko.util.ManifestInfo.checkSameVersion(ManifestInfo.scala:188)
	at org.apache.pekko.util.ManifestInfo.checkSameVersion(ManifestInfo.scala:166)
	at org.apache.pekko.http.scaladsl.HttpExt.<init>(Http.scala:89)
	at org.apache.pekko.http.scaladsl.Http$.createExtension(Http.scala:1140)
	at org.apache.pekko.http.scaladsl.Http$.createExtension(Http.scala:871)
	at org.apache.pekko.actor.ActorSystemImpl.registerExtension(ActorSystem.scala:1175)
	at org.apache.pekko.actor.ExtensionId.apply(Extension.scala:87)
	at org.apache.pekko.actor.ExtensionId.apply$(Extension.scala:86)
```

## For [pekko-actor](https://pekko.apache.org/docs/pekko/current/)

Tests:
* pekko modules are of the same version

### Usage

```scala
addSbtPlugin("com.evolution" % "sbt-artifactory-plugin" % "0.0.2")

libraryDependencies += "com.evolution" %% "pekko-test-actor" % "1.0.0" % Test
```

And just drop this test into your project 

```scala
import com.evolution.pekkotest.PekkoActorSuite

class PekkoActorTest extends PekkoActorSuite
```


## For [pekko-http](https://pekko.apache.org/docs/pekko-http/current/)

Tests:
* pekko-http modules are of same version

### Usage

Add dependency

```scala
addSbtPlugin("com.evolution" % "sbt-artifactory-plugin" % "0.0.2")

libraryDependencies += "com.evolution" %% "pekko-test-http" % "1.0.0" % Test
```

And just drop this test into your project

```scala
import com.evolution.pekkotest.PekkoHttpSuite

class PekkoHttpTest extends PekkoHttpSuite
```

## Setup

```scala
addSbtPlugin("com.evolution" % "sbt-artifactory-plugin" % "0.0.2")

libraryDependencies += "com.evolution" %% "pekko-test-actor" % "1.0.0"

libraryDependencies += "com.evolution" %% "pekko-test-http" % "1.0.0"
```
