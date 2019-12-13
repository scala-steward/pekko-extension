package com.evolutiongaming.akkatest

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import org.scalatest.Succeeded
import org.scalatest.funsuite.AsyncFunSuite

import scala.concurrent.Future
import scala.util.Try

trait AkkaActorSuite extends AsyncFunSuite {

  test("modules are of same version") {

    def future[A](a: => A): Future[A] = Future.fromTry { Try { a } }

    for {
      config <- future { ConfigFactory.load("reference.conf") }
      system <- future { ActorSystem("actor-manifest-suite", config) }
      _      <- system.terminate()
    } yield Succeeded
  }
}
