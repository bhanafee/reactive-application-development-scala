object Guidebook {

  case class Inquiry(code: String)(val trace: Map[String, String]) extends Traceable

}

import java.util.{Currency, Locale}

import akka.actor.Actor

import io.opentracing.Tracer

import Guidebook.Inquiry
import Tourist.Guidance

class Guidebook (val tracer: Tracer) extends Actor with Spanned {
  def describe(locale: Locale) =
    s"""In ${locale.getDisplayCountry}, ${locale.getDisplayLanguage} is spoken and the currency is the ${Currency.getInstance(locale).getDisplayName}"""

  override def receive = TracingReceive(this) {
    case Inquiry(code) =>
      println(s"Actor ${self.path.name} responding to inquiry about $code")
      Locale.getAvailableLocales.filter(_.getCountry == code).
        foreach { locale =>
          sender ! Guidance(code, describe(locale))(trace())
        }
  }
}
