package com.evolutiongaming.akkatest

import org.scalatest.{Assertion, AsyncFunSuite, Matchers}

import scala.util.{Failure, Success, Try}

class AkkaHttpTest extends AsyncFunSuite with Matchers {
  import AkkaHttpSuite._

  test("akka-http modules are not of same version") {
    for {
      error <- `akka-http modules are of same version`.transform((a: Try[Assertion]) => Success(a))
    } yield {
      //      error should matchPattern { case Failure(_: IllegalStateException) => }
      error match {
        case Failure(a: IllegalStateException) if a.getMessage contains "akka-http-testkit" => succeed
        case _                                                                              => fail()
      }
    }
  }
}