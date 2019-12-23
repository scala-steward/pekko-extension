# akka-test 
[![travis-ci](https://travis-ci.org/evolution-gaming/akka-test.svg)](https://travis-ci.org/evolution-gaming/akka-test)
[![github-actions](https://github.com/evolution-gaming/akka-test/workflows/ci/badge.svg)](https://github.com/evolution-gaming/akka-test/actions?query=workflow%3Aci)
[![scoverage](https://coveralls.io/repos/evolution-gaming/akka-test/badge.svg)](https://coveralls.io/r/evolution-gaming/akka-test)
[![codacy](https://api.codacy.com/project/badge/Grade/799b059200e14801ac572ca5b86cc48e)](https://www.codacy.com/manual/evolution-gaming/akka-test?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=evolution-gaming/akka-test&amp;utm_campaign=Badge_Grade)
[![bintray](https://api.bintray.com/packages/evolutiongaming/maven/akka-test/images/download.svg) ](https://bintray.com/evolutiongaming/maven/akka-test/_latestVersion)
[![license: MIT](https://img.shields.io/badge/License-MIT-yellowgreen.svg)](https://opensource.org/licenses/MIT)

This library is created in order to provide reusable set of tests to be used in projects dependent on [Akka](https://akka.io) libraries.
For instance to prevent following "surprise" at runtime:

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

## For [akka-actor](https://doc.akka.io/docs/akka/current/)

Tests:
* akka modules are of same version

### Usage

```scala
resolvers += Resolver.bintrayRepo("evolutiongaming", "maven")

libraryDependencies += "com.evolutiongaming" %% "akka-test-actor" % "0.0.2" % Test
```

And just drop this test into your project 

```scala
import com.evolutiongaming.akkatest.AkkaActorSuite

class AkkaActorTest extends AkkaActorSuite
```


## For [akka-http](https://doc.akka.io/docs/akka-http/current)

Tests:
* akka-http modules are of same version

### Usage

Add dependency

```scala
resolvers += Resolver.bintrayRepo("evolutiongaming", "maven")

libraryDependencies += "com.evolutiongaming" %% "akka-test-http" % "0.0.2" % Test
```

And just drop this test into your project

```scala
import com.evolutiongaming.akkatest.AkkaHttpSuite

class AkkaHttpTest extends AkkaHttpSuite
```

## Setup

```scala
resolvers += Resolver.bintrayRepo("evolutiongaming", "maven")

libraryDependencies += "com.evolutiongaming" %% "akka-test-actor" % "0.0.2"

libraryDependencies += "com.evolutiongaming" %% "akka-test-http" % "0.0.2"
```
