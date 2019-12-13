package com.evolutiongaming.akkatest

import com.evolutiongaming.akkatest.AkkaActorSuite._
import org.scalatest.{Assertion, AsyncFunSuite, Matchers}

import scala.util.{Failure, Success, Try}


class AkkaActorTest extends AsyncFunSuite with Matchers {

  test("akka modules are not of same version") {
    for {
      error <- `akka modules are of same version`.transform((a: Try[Assertion]) => Success(a))
    } yield {
      error match {
        case Failure(a: IllegalStateException) if a.getMessage contains "akka-slf4j" => succeed
        case _                                                                       => fail()
      }
    }
  }
}