# ConHub
[![Build Status](https://github.com/evolution-gaming/pekko-conhub/workflows/CI/badge.svg)](https://github.com/evolution-gaming/pekko-conhub/actions?query=workflow%3ACI)
[![Coverage Status](https://coveralls.io/repos/evolution-gaming/pekko-conhub/badge.svg)](https://coveralls.io/r/evolution-gaming/pekko-conhub)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/b0d07be1d0424c2e949f1a27ba5032db)](https://app.codacy.com/gh/evolution-gaming/pekko-conhub/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)
[![Version](https://img.shields.io/badge/version-click-blue)](https://evolution.jfrog.io/artifactory/api/search/latestVersion?g=com.evolution&a=pekko-conhub_2.13&repos=public)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellowgreen.svg)](https://opensource.org/licenses/MIT)

ConHub is a distributed registry used to manage websocket connections on the different nodes of an application.
It enables you to send serializable message to one or many connections hiding away complexity of distributed system. 
In short: user provides `lookup` criteria and a `message`, there after `conHub` does the job routing message to physical instances of a matched connections

## Usage example
```scala
type Connection = ??? // type representing physical connection
final case class Msg(bytes: Array[Byte]) // serializable
final case class Envelope(lookup: LookupById, msg: Msg)
final case class LookupById(id: String)
val conHub: ConHub[String, LookupById, Connection, Envelope] = ???
conHub ! Envelope(LookupById("testId"), Msg(Array(…)))
```

## Setup

```scala
addSbtPlugin("com.evolution" % "sbt-artifactory-plugin" % "0.0.2")

libraryDependencies += "com.evolution" %% "pekko-conhub" % "1.0.0"
```
