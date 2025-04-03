package com.evolutiongaming.akkatest

import com.evolutiongaming.akkatest.AkkaActorSuite._
import org.scalatest.Assertion
import org.scalatest.funsuite.AsyncFunSuite
import org.scalatest.matchers.should.Matchers

import scala.util.{Failure, Success, Try}

class AkkaActorTest extends AsyncFunSuite with Matchers {

  test("akka modules are not of same version") {
    `akka modules are of same version`
      .transform((a: Try[Assertion]) => Success(a))
      .map {
        case Failure(a: IllegalStateException) if a.getMessage contains "akka-slf4j" =>
          succeed
        case Failure(unexpectedException) =>
          fail("unexpected exception", unexpectedException)
        case Success(_) =>
          fail("unexpected success")
      }
  }
}
