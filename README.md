[![Build Status](https://github.com/evolution-gaming/pekko-extension/workflows/CI/badge.svg)](https://github.com/evolution-gaming/pekko-extension/actions?query=workflow%3ACI)
[![Coverage Status](https://coveralls.io/repos/github/evolution-gaming/pekko-extension/badge.svg?branch=main)](https://coveralls.io/github/evolution-gaming/pekko-extension?branch=main)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/4fa9958e884a458fbfd465372e4e3e65)](https://app.codacy.com/gh/evolution-gaming/pekko-extension/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)
[![Version](https://img.shields.io/badge/version-click-blue)](https://evolution.jfrog.io/artifactory/api/search/latestVersion?g=com.evolution&a=pekko-extension_2.13&repos=public)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellowgreen.svg)](https://opensource.org/licenses/MIT)

# Pekko Extension libraries

Set of extension libraries for [`pekko`](https://pekko.apache.org/).

## Getting Started

All libraries require the same initial setup, like:
```scala
addSbtPlugin("com.evolution" % "sbt-artifactory-plugin" % "0.0.2")
```
Setting dependency:
```scala
libraryDependencies += "com.evolution" %% "pekko-extension-<name>" % "<version>"
```

## Extensions

### pekko-extension-serialization

TODO add description!

### pekko-extension-pubsub

Typesafe layer for `DistributedPubSubMediator`.

```scala
trait PubSub[F[_]] {

  def publish[A: Topic: ToBytes](
    msg: A,
    sender: Option[ActorRef] = None,
    sendToEachGroup: Boolean = false
  ): F[Unit]

  def subscribe[A: Topic: FromBytes: ClassTag](
    group: Option[String] = None)(
    onMsg: OnMsg[F, A]
  ): Resource[F, Unit]

  def topics(timeout: FiniteDuration = 3.seconds): F[Set[String]]
}
```

For an ability to serialize/deserialize messages to offload `pekko` remoting and improve throughput, 
check [`DistributedPubSubMediatorSerializing.scala`](src/main/scala/org/apache/pekko/cluster/pubsub/DistributedPubSubMediatorSerializing.scala).

### pekko-extension-test

These two libraries were created to provide a set of tests to be used in projects dependent on [Pekko](https://pekko.apache.org)
libraries.
For instance, to prevent the following "surprise" at runtime:

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

#### pekko-extension-test-actor

For [pekko-actor](https://pekko.apache.org/docs/pekko/current/) tests that all `pekko` modules are of the same version.

Set up the dependency in `Test` scope:
```scala
libraryDependencies += "com.evolution" %% "pekko-extension-test-actor" % "<version>" % Test
```

And add the following test into your project:
```scala
import com.evolution.pekkotest.PekkoActorSuite

class PekkoActorTest extends PekkoActorSuite
```

#### pekko-extension-test-http

For [pekko-http](https://pekko.apache.org/docs/pekko-http/current/) tests that all `pekko-http` modules are of the same version.

Set up the dependency in `Test` scope:
```scala
libraryDependencies += "com.evolution" %% "pekko-extension-test-http" % "<version>" % Test
```

And add the following test into your project.
```scala
import com.evolution.pekkotest.PekkoHttpSuite

class PekkoHttpTest extends PekkoHttpSuite
```

### pekko-extension-distributed-data-tools

`SafeReplicator` is a typesafe api for [Distributed Data replicator](https://pekko.apache.org/docs/pekko/current/typed/distributed-data.html)

```scala
trait SafeReplicator[F[_], A <: ReplicatedData] {

  def get(implicit consistency: ReadConsistency): F[Option[A]]

  def update(modify: Option[A] => A)(implicit consistency: WriteConsistency): F[Unit]

  def delete(implicit consistency: WriteConsistency): F[Boolean]

  def subscribe(
    onStop: F[Unit],
    onChanged: A => F[Unit])(implicit
    factory: ActorRefFactory,
    executor: ExecutionContext
  ): Resource[F, Unit]

  def flushChanges: F[Unit]
}
```

### pekko-extension-sharding-strategy

Alternative to [org.apache.pekko.cluster.sharding.ShardCoordinator.ShardAllocationStrategy](https://github.com/apache/pekko/blob/main/cluster-sharding/src/main/scala/org/apache/pekko/cluster/sharding/ShardCoordinator.scala#L78).

#### Api

```scala
trait ShardingStrategy[F[_]] {

  def allocate(requester: Region, shard: Shard, current: Allocation): F[Option[Region]]

  def rebalance(current: Allocation, inProgress: Set[Shard]): F[List[Shard]]
}
```

#### Syntax

```scala
val strategy = LeastShardsStrategy()
  .filterShards(...)
  .filterRegions(...)
  .rebalanceThreshold(10)
  .takeShards(10) 
  .shardRebalanceCooldown(1.minute)
  .logging(...)
  .toAllocationStrategy()
```

### pekko-extension-tools-test

TODO

### pekko-extension-tools-util

TODO

### pekko-extension-tools-serialization

TODO

### pekko-extension-tools-persistence

TODO

### pekko-extension-tools-cluster

TODO

### pekko-extension-tools-instrumentation

TODO

TODO do we need umbrella lib `pekko-extension-tools`?

### pekko-extension-conhub

ConHub is a distributed registry used to manage websocket connections on the different nodes of an application.
It enables you to send a serializable message to one or many connections hiding away the complexity of distributed system.
In short: user provides `lookup` criteria and a `message` and `conHub` does the job routing message to physical 
instances of a matched connections

Usage example:
```scala
type Connection = ??? // type representing physical connection
final case class Msg(bytes: Array[Byte]) // serializable
final case class Envelope(lookup: LookupById, msg: Msg)
final case class LookupById(id: String)
val conHub: ConHub[String, LookupById, Connection, Envelope] = ???
conHub ! Envelope(LookupById("testId"), Msg(Array(…)))
```

# -----------------------------------
# TODO add descriptions for libraries
# -----------------------------------

## Library mappings `pekko` to `akka` 

| pekko                                  | akka                                                                         | migrated from version |
|----------------------------------------|------------------------------------------------------------------------------|-----------------------|
| pekko-extension-serialization          | [akka-serialization](https://github.com/evolution-gaming/akka-serialization) | 1.1.0                 |
| pekko-extension-pubsub                 | [pubsub](https://github.com/evolution-gaming/akka-pubsub)                    | 10.0.0                |
| pekko-extension-test-actor             | [akka-test](https://github.com/evolution-gaming/akka-test)                   | 0.3.0                 |
| pekko-extension-test-http              | [akka-test](https://github.com/evolution-gaming/akka-test)                   | 0.3.0                 |
| pekko-extension-distributed-data-tools | [ddata-tools](https://github.com/evolution-gaming/ddata-tools/)              | 3.1.0                 |
| pekko-extension-sharding-strategy      | [sharding-strategy](https://github.com/evolution-gaming/sharding-strategy)   | 3.0.2                 |
| pekko-extension-tools-test             | [akka-tools](https://github.com/evolution-gaming/akka-tools/)                | 3.3.13                |
| pekko-extension-tools-util             | [akka-tools](https://github.com/evolution-gaming/akka-tools/)                | 3.3.13                |
| pekko-extension-tools-serialization    | [akka-tools](https://github.com/evolution-gaming/akka-tools/)                | 3.3.13                |
| pekko-extension-tools-persistence      | [akka-tools](https://github.com/evolution-gaming/akka-tools/)                | 3.3.13                |
| pekko-extension-tools-cluster          | [akka-tools](https://github.com/evolution-gaming/akka-tools/)                | 3.3.13                |
| pekko-extension-tools-instrumentation  | [akka-tools](https://github.com/evolution-gaming/akka-tools/)                | 3.3.13                |
| pekko-extension-conhub                 | [conhub](https://github.com/evolution-gaming/conhub)                         | 3.0.0                 |

