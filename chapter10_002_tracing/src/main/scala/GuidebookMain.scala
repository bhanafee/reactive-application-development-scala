import akka.actor.{ActorRef, ActorSystem, Props}

import io.opentracing.Tracer
import io.opentracing.mock.MockTracer

object GuidebookMain extends App {
  val tracer: Tracer =
    new MockTracer(MockTracer.Propagator.TEXT_MAP)

  val system: ActorSystem = ActorSystem("BookSystem")

  val guideProps: Props = Props(classOf[Guidebook], tracer)
  val guidebook: ActorRef =
    system.actorOf(guideProps, "guidebook")
}
