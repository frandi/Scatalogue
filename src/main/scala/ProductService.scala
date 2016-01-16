package scatalogue

import spray.routing.HttpService

trait ProductService extends HttpService {

  def pingRoute = path("ping") {
    get { complete("Ping!") }
  }

  def pongRoute = path("pong") {
    get { complete("Pong!?") }
  }

  def rootRoute = pingRoute ~ pongRoute
}