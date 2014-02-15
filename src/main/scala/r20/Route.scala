package r20

object Route {
  var routes: Map[String, Sub] = Map.empty

  def route(topic: String, e: RouteEvent):Unit = {
    println("routing")
    routes.get(topic).get.@>(e) 
  }
  
  def *(sub: Subscriber): Unit = {
    
  }
  
  def addSub(topic: String, sub: Sub) = {
    println("registering")
    routes = routes.+((topic, sub))
  }
}