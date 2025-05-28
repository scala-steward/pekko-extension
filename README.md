# PubSub
[![Build Status](https://github.com/evolution-gaming/pekko-pubsub/workflows/CI/badge.svg)](https://github.com/evolution-gaming/pekko-pubsub/actions?query=workflow%3ACI)
[![Coverage Status](https://coveralls.io/repos/github/evolution-gaming/pekko-pubsub/badge.svg?branch=master)](https://coveralls.io/github/evolution-gaming/pekko-pubsub?branch=master)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/260032c4cece4be08b1c3e79792b39ab)](https://app.codacy.com/gh/evolution-gaming/pekko-pubsub/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)
[![Version](https://img.shields.io/badge/version-click-blue)](https://evolution.jfrog.io/artifactory/api/search/latestVersion?g=com.evolution&a=pekko-pubsub_2.13&repos=public)
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
