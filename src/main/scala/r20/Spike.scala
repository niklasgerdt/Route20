package r20

object Spike extends Application {
  MySub + "topic"

  new MyEvent("id") -> "topic"

  val e = KurssiMuutos("nok", 15.0)
  println(e)

}

class MyEvent(e: String) extends Event with Sourced with Logged {
  def m1() = println("myevent")
}

object MySub extends Subscriber {
  override def @>(e: RouteEvent): Unit = println("@my sub")
}

case class KurssiMuutos(id: String, value: Double) extends Event with Logged