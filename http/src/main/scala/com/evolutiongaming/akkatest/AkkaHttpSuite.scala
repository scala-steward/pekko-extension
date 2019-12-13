package com.evolutiongaming.akkatest

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import com.typesafe.config.ConfigFactory
import org.scalatest.Succeeded
import org.scalatest.funsuite.AsyncFunSuite
import org.scalatest.matchers.should.Matchers

import scala.concurrent.Future
import scala.util.Try

trait AkkaHttpSuite extends AsyncFunSuite with Matchers {

  test("modules are of same version") {

    def future[A](a: => A): Future[A] = Future.fromTry { Try { a } }
    
    for {
      config <- future { ConfigFactory.load("reference.conf") }
      system <- future { ActorSystem("actor-manifest-suite", config) }
      http <- future { Http(system) }
      _    <- http.shutdownAllConnectionPools()
      _    <- system.terminate()
    } yield Succeeded
  }
}
