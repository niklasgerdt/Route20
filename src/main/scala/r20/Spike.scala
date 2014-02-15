package r20

object Spike extends Application {
  MySub + "topic" 
  
  new MyEvent("id") -> "topic"
  
}

class MyEvent(e: String) extends Event with Sourced with Logged {
  def m1() = println("myevent")
}

object MySub extends Subscriber {
  override def @>(e: RouteEvent):Unit = println("@my sub")
}