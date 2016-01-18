package scatalogue

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import scala.language.postfixOps

object Boot extends App {
    val host = "localhost"
    val port = 8000

    //create our service actor
    implicit val system = ActorSystem("scatalogue-service")
    implicit val executionContext = system.dispatcher
    implicit val timeout = Timeout(10 seconds)
  
    val api = system.actorOf(Props(new RestInterface))

    //bind our actor to an HTTP port
    IO(Http).ask(Http.Bind(listener = api, interface = host, port = port))
    .mapTo[Http.Event]
    .map {
      case Http.Bound(address) =>
        println(s"REST interface bound to $address")
      case Http.CommandFailed(cmd) =>
        println("REST interface could not bind to " +
          s"$host:$port, ${cmd.failureMessage}")
        system.shutdown()
    }
}