package scatalogue

import scatalogue.resources.ProductResource
import scatalogue.services.ProductService
import spray.routing._

import scala.concurrent.ExecutionContext
import scala.language.postfixOps

class RestInterface(implicit val executionContext: ExecutionContext) extends HttpServiceActor with Resources {

  def receive = runRoute(routes)

  val productService = new ProductService

  val routes: Route = productRoutes

}

trait Resources extends ProductResource {
    
}
