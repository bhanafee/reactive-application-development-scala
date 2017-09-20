object Tourist {

  case class Guidance(code: String, description: String)

  case class Start(codes: Seq[String])

}

import akka.actor.{Actor, ActorRef, ActorLogging}

import Guidebook.Inquiry
import Tourist.{Guidance, Start}

class Tourist(guidebook: ActorRef) extends Actor with ActorLogging {

  override def receive = {
    case Start(codes) =>
      codes.foreach(guidebook ! Inquiry(_))
    case Guidance(code, description) =>
      log.info(s"$code: $description")
  }
}
