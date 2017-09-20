import io.opentracing.{Span, Tracer}
import io.opentracing.propagation.TextMap
import io.opentracing.propagation.Format.Builtin.TEXT_MAP
trait Spanned {

  val tracer: Tracer

  var span: Span = _

  def trace(): Map[String, String] = {
    var kvs: List[(String, String)] = List.empty
    tracer.inject(span.context(), TEXT_MAP, new TextMap() {
      override def put(key: String, value: String): Unit =
        kvs = (key, value) :: kvs

      override def iterator() =
        throw new UnsupportedOperationException()
    })
    Map(kvs: _*)
  }
}
