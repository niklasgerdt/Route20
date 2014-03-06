package r20

abstract class Sub {
  def @>(e: RouteEvent): Unit

  def +(topic: String): Unit 
}

abstract class Subscriber extends Sub {
//  def @>(e: RouteEvent): Unit = {
//    println("sub")
//  }

  def +(topic: String): Unit = {
    Route.addSub(topic, this)
  }
}
