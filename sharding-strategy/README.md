# Sharding Strategies
[![Build Status](https://github.com/evolution-gaming/pekko-sharding-strategy/workflows/CI/badge.svg)](https://github.com/evolution-gaming/pekko-sharding-strategy/actions?query=workflow%3ACI)
[![Coverage Status](https://coveralls.io/repos/github/evolution-gaming/pekko-sharding-strategy/badge.svg?branch=master)](https://coveralls.io/github/evolution-gaming/pekko-sharding-strategy?branch=master)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/0083aeb74eb3479e87ac0d536f05f1cf)](https://app.codacy.com/gh/evolution-gaming/pekko-sharding-strategy/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)
[![Version](https://img.shields.io/badge/version-click-blue)](https://evolution.jfrog.io/artifactory/api/search/latestVersion?g=com.evolution&a=pekko-sharding-strategy_2.13&repos=public)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellowgreen.svg)](https://opensource.org/licenses/MIT)

This project is forked from [sharding-strategy](https://github.com/evolution-gaming/sharding-strategy) at v3.0.2 by replacing 
`akka` with `pekko`.

Alternative to [org.apache.pekko.cluster.sharding.ShardCoordinator.ShardAllocationStrategy](https://github.com/apache/pekko/blob/main/cluster-sharding/src/main/scala/org/apache/pekko/cluster/sharding/ShardCoordinator.scala#L78)

## Api

```scala
trait ShardingStrategy[F[_]] {

  def allocate(requester: Region, shard: Shard, current: Allocation): F[Option[Region]]

  def rebalance(current: Allocation, inProgress: Set[Shard]): F[List[Shard]]
}
```

## Syntax

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

## Setup

```scala
addSbtPlugin("com.evolution" % "sbt-artifactory-plugin" % "0.0.2")

libraryDependencies += "com.evolution" %% "pekko-sharding-strategy" % "1.0.0"
```