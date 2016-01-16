package scatalogue

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._

object Boot extends App {
    // we need an ActorSystem to host our service
    implicit val system = ActorSystem("scatalogue-app")

    //create our service actor
    val service = system.actorOf(Props[ProductServiceActor], "product-service")

    //bind our actor to an HTTP port
    IO(Http) ! Http.Bind(service, interface = "localhost", port = 8000)
}