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

## Library mappings `pekko` to `akka` 

| pekko                         | akka                                                                         | migrated from version |
|-------------------------------|------------------------------------------------------------------------------|-----------------------|
| pekko-extension-serialization | [akka-serialization](https://github.com/evolution-gaming/akka-serialization) | 1.1.0                 | 
