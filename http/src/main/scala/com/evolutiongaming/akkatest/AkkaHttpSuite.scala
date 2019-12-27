package com.evolutiongaming.akkatest

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import com.typesafe.config.ConfigFactory
import org.scalatest.funsuite.AsyncFunSuite
import org.scalatest.{Assertion, Succeeded}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

trait AkkaHttpSuite extends AsyncFunSuite {
  import AkkaHttpSuite._

  test("akka-http modules are of same version") {
    `akka-http modules are of same version`
  }
}

object AkkaHttpSuite {

  def `akka-http modules are of same version`(implicit executor: ExecutionContext): Future[Assertion] = {

    def future[A](a: => A): Future[A] = Future.fromTry { Try { a } }

    for {
      config <- future { ConfigFactory.load("reference.conf") }
      system <- future { ActorSystem("http-manifest-suite", config) }
      http   <- future { Http(system) }
      _      <- http.shutdownAllConnectionPools()
      _      <- system.terminate()
    } yield Succeeded
  }
}