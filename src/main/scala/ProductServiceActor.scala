package scatalogue

import akka.actor.Actor
import spray.routing._
import spray.http._
import MediaTypes._

class ProductServiceActor extends ProductService with Actor {
  
  def actorRefFactory = context

  def receive = runRoute(rootRoute)
}