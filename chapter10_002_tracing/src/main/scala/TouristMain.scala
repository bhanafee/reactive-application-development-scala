import java.util.Locale

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.util.Timeout

import Tourist.Start

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.SECONDS
import scala.util.{Failure, Success}

import io.opentracing.Tracer
import io.opentracing.mock.MockTracer

object TouristMain extends App {
  val tracer: Tracer =
    new MockTracer(MockTracer.Propagator.TEXT_MAP)

  val system: ActorSystem = ActorSystem("TouristSystem")

  val path =
    "akka.tcp://BookSystem@127.0.0.1:2553/user/guidebook"

  implicit val timeout: Timeout = Timeout(5, SECONDS)

  system.actorSelection(path).resolveOne().onComplete {
    case Success(guidebook) =>

      val tourProps: Props =
        Props(classOf[Tourist], guidebook, tracer)
      val tourist: ActorRef = system.actorOf(tourProps)

      tourist ! Start(Locale.getISOCountries)

    case Failure(e) => println(e)
  }
}
