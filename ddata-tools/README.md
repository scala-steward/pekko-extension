# DData Tools
[![Build Status](https://github.com/evolution-gaming/pekko-ddata-tools/workflows/CI/badge.svg)](https://github.com/evolution-gaming/pekko-ddata-tools/actions?query=workflow%3ACI)
[![Coverage Status](https://coveralls.io/repos/github/evolution-gaming/pekko-ddata-tools/badge.svg?branch=master)](https://coveralls.io/github/evolution-gaming/pekko-ddata-tools?branch=master)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/9460691128bb4ba5b8a4b0006aca195a)](https://app.codacy.com/gh/evolution-gaming/pekko-ddata-tools/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)
[![Version](https://img.shields.io/badge/version-click-blue)](https://evolution.jfrog.io/artifactory/api/search/latestVersion?g=com.evolution&a=pekko-ddata-tools_2.13&repos=public)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellowgreen.svg)](https://opensource.org/licenses/MIT)

This project is forked from [ddata-tools](https://github.com/evolution-gaming/ddata-tools/) at v3.1.0 by replacing `akka` with `pekko`. 

### SafeReplicator - is a typesafe api for [DData replicator](https://pekko.apache.org/docs/pekko/current/typed/distributed-data.html)

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

## Setup

```scala
addSbtPlugin("com.evolution" % "sbt-artifactory-plugin" % "0.0.2")

libraryDependencies += "com.evolution" %% "pekko-ddata-tools" % "1.0.0"
```
