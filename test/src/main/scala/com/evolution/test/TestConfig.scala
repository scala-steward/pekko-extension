package com.evolution.test

import com.typesafe.config.{Config, ConfigFactory}

object TestConfig {

  private lazy val value = ConfigFactory.load("test-pekko.conf")

  def apply(): Config = value
}
