# pekko-tools 
[![Build Status](https://github.com/evolution-gaming/pekko-tools/workflows/CI/badge.svg)](https://github.com/evolution-gaming/pekko-tools/actions?query=workflow%3ACI) 
[![Coverage Status](https://coveralls.io/repos/evolution-gaming/pekko-tools/badge.svg)](https://coveralls.io/r/evolution-gaming/pekko-tools)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/06420117427240fd9e18f4f2f58f6849)](https://app.codacy.com/gh/evolution-gaming/pekko-tools/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)
[![Version](https://img.shields.io/badge/version-click-blue)](https://evolution.jfrog.io/artifactory/api/search/latestVersion?g=com.evolutiongaming&a=pekko-tools_2.13&repos=public)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellowgreen.svg)](https://opensource.org/licenses/MIT)

This project is forked from [akka-tools](https://github.com/evolution-gaming/akka-tools/) at v3.3.13 by replacing `akka` with `pekko`.

### ExtractShardId
ExtractShardId is an interface of the function used by Pekko sharding to extract ShardId from an incoming message.
Our implementation of ExtractShardId supports mapping of specific EntityId-s to specific ShardId-s.
All other not pre-configured EntityId-s will be mapped to equal individual ShardId-s.

## Setup

```scala
addSbtPlugin("com.evolution" % "sbt-artifactory-plugin" % "0.0.2")

libraryDependencies += "com.evolution" %% "pekko-tools" % "1.0.0"
```
