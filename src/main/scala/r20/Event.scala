package r20

abstract class RouteEvent {
  def ->(topic: String): Unit
}

class Event extends RouteEvent {
  def ->(topic: String): Unit = {
    println("event")
    Route.route(topic, this)
  }
}

trait Logged extends RouteEvent{
  abstract override def ->(topic: String): Unit={
    println("logged")
    super.->(topic)
  }
}
 
trait Sourced extends RouteEvent {
  abstract override def ->(topic: String): Unit = {
    println("sourced")
    super.->(topic)
  }
}