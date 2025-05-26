# PubSub
[![Build Status](https://github.com/evolution-gaming/pekko-pubsub/workflows/CI/badge.svg)](https://github.com/evolution-gaming/pekko-pubsub/actions?query=workflow%3ACI)
[![Coverage Status](https://coveralls.io/repos/github/evolution-gaming/pekko-pubsub/badge.svg?branch=master)](https://coveralls.io/github/evolution-gaming/pekko-pubsub?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/5c1e3dc82255463f82583a3fa69fd56f)](https://www.codacy.com/app/evolution-gaming/pekko-pubsub?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=evolution-gaming/pekko-pubsub&amp;utm_campaign=Badge_Grade)
[![Version](https://img.shields.io/badge/version-click-blue)](https://evolution.jfrog.io/artifactory/api/search/latestVersion?g=com.evolutiongaming&a=pubsub_2.13&repos=public)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellowgreen.svg)](https://opensource.org/licenses/MIT)

It is forked from [pubsub](https://github.com/evolution-gaming/pekko-pubsub) at v10.0.0 by replacing `akka` with `pekko`.

### Typesafe layer for DistributedPubSubMediator

```scala
trait PubSub[F[_]] {

  def publish[A: Topic : ToBytes](
    msg: A,
    sender: Option[ActorRef] = None,
    sendToEachGroup: Boolean = false
  ): F[Unit]

  def subscribe[A: Topic : FromBytes : ClassTag](
    group: Option[String] = None)(
    onMsg: OnMsg[F, A]
  ): Resource[F, Unit]

  def topics(timeout: FiniteDuration = 3.seconds): F[Set[String]]
}
```

### Ability to serialize/deserialize messages to offload pekko remoting and improve throughput

Check [`DistributedPubSubMediatorSerializing.scala`](src/main/scala/org/apache/pekko/cluster/pubsub/DistributedPubSubMediatorSerializing.scala).

## Setup

```scala
addSbtPlugin("com.evolution" % "sbt-artifactory-plugin" % "0.0.2")

libraryDependencies += "com.evolution" %% "pekko-pubsub" % "1.0.0"
```
