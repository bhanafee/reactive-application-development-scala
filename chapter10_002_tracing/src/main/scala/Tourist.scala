object Tourist {

  case class Guidance(code: String, description: String)(val trace: Map[String, String]) extends Traceable

  case class Start(codes: Seq[String])

}

import akka.actor.{Actor, ActorRef}

import io.opentracing.Tracer

import Guidebook.Inquiry
import Tourist.{Guidance, Start}

class Tourist(guidebook: ActorRef, val tracer: Tracer) extends Actor with Spanned {

  override def receive = TracingReceive(this) {
    case Start(codes) =>
      codes.foreach(guidebook ! Inquiry(_)(trace()))
    case Guidance(code, description) =>
      println(s"$code: $description")
  }
}
