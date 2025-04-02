# akka-test 
[![Build Status](https://github.com/evolution-gaming/akka-test/workflows/CI/badge.svg)](https://github.com/evolution-gaming/akka-test/actions?query=workflow%3ACI)
[![Test Coverage Status](https://coveralls.io/repos/github/evolution-gaming/akka-test/badge.svg?branch=master)](https://coveralls.io/github/evolution-gaming/akka-test?branch=master)
[![Codacy](https://api.codacy.com/project/badge/Grade/799b059200e14801ac572ca5b86cc48e)](https://app.codacy.com/gh/evolution-gaming/akka-test/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)
[![Version](https://img.shields.io/badge/version-click-blue)](https://evolution.jfrog.io/artifactory/api/search/latestVersion?g=com.evolutiongaming&a=akka-test-actor_2.13&repos=public)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellowgreen.svg)](https://opensource.org/licenses/MIT)

This library is created to provide a reusable set of tests to be used in projects dependent on [Akka](https://akka.io) libraries.
For instance, to prevent the following "surprise" at runtime:

```
java.lang.IllegalStateException: Detected possible incompatible versions on the classpath. Please note that a given Akka HTTP version MUST be the same across all modules of Akka HTTP that you are using, e.g. if you use [10.1.11] all other modules that are released together MUST be of the same version. Make sure you're using a compatible set of libraries. Possibly conflicting versions [10.1.9, 10.1.11] in libraries [akka-http-spray-json:10.1.9, akka-parsing:10.1.11, akka-http-xml:10.1.11, akka-http:10.1.11, akka-http-core:10.1.11]
	at akka.util.ManifestInfo.checkSameVersion(ManifestInfo.scala:206)
	at akka.util.ManifestInfo.checkSameVersion(ManifestInfo.scala:173)
	at akka.http.scaladsl.HttpExt.<init>(Http.scala:75)
	at akka.http.scaladsl.Http$.createExtension(Http.scala:1123)
	at akka.http.scaladsl.Http$.createExtension(Http.scala:892)
	at akka.actor.ActorSystemImpl.registerExtension(ActorSystem.scala:1151)
	at akka.actor.ExtensionId.apply(Extension.scala:78)
	at akka.actor.ExtensionId.apply$(Extension.scala:77)
	at akka.http.scaladsl.Http$.apply(Http.scala:1118)
```

## Version compatibility

Only the latest OSS Akka and Akka HTTP versions are supported:
- Akka: 2.6.x
- Akka-HTTP: 10.2.x

Since there is no OSS Akka-HTTP version published for Scala 3, submodules have different sets of supported
major Scala versions:
- `akka-test-actor`: 2.12, 2.13, 3
- `akka-test-http`: 2.12, 2.13

## For [akka-actor 2.6.x](https://doc.akka.io/docs/akka/2.6.21/)

Tests:
* akka modules are of the same version

### Usage

```scala
addSbtPlugin("com.evolution" % "sbt-artifactory-plugin" % "0.0.2")

libraryDependencies += "com.evolutiongaming" %% "akka-test-actor" % "0.2.0" % Test
```

And just drop this test into your project 

```scala
import com.evolutiongaming.akkatest.AkkaActorSuite

class AkkaActorTest extends AkkaActorSuite
```


## For [akka-http 10.2.x](https://doc.akka.io/docs/akka-http/10.2.10)

Tests:
* akka-http modules are of same version

### Usage

Add dependency

```scala
addSbtPlugin("com.evolution" % "sbt-artifactory-plugin" % "0.0.2")

libraryDependencies += "com.evolutiongaming" %% "akka-test-http" % "0.2.0" % Test
```

And just drop this test into your project

```scala
import com.evolutiongaming.akkatest.AkkaHttpSuite

class AkkaHttpTest extends AkkaHttpSuite
```

## Setup

```scala
addSbtPlugin("com.evolution" % "sbt-artifactory-plugin" % "0.0.2")

libraryDependencies += "com.evolutiongaming" %% "akka-test-actor" % "0.2.0"

libraryDependencies += "com.evolutiongaming" %% "akka-test-http" % "0.2.0"
```
