package com.evolutiongaming.akkatest

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import org.scalatest.funsuite.AsyncFunSuite
import org.scalatest.{Assertion, Succeeded}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

trait AkkaActorSuite extends AsyncFunSuite {
  import AkkaActorSuite._

  test("akka modules are of same version") {
    `akka modules are of same version`
  }
}

object AkkaActorSuite {

  def `akka modules are of same version`(
    implicit
    executor: ExecutionContext,
  ): Future[Assertion] = {
    def future[A](a: => A): Future[A] = Future.fromTry { Try { a } }

    for {
      config <- future { ConfigFactory.load("reference.conf") }
      system <- future { ActorSystem("actor-manifest-suite", config) }
      _ <- system.terminate()
    } yield Succeeded
  }
}
