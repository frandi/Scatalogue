package scatalogue

import scatalogue.resources.{ProductResource, CategoryResource}
import scatalogue.services.{ProductService, CategoryService}
import spray.routing._

import scala.concurrent.ExecutionContext
import scala.language.postfixOps

class RestInterface(implicit val executionContext: ExecutionContext) extends HttpServiceActor with Resources {

  def receive = runRoute(routes)

  val productService = new ProductService
  val categoryService = new CategoryService

  val routes: Route = (productRoutes ~ categoryRoutes)

}

trait Resources extends ProductResource with CategoryResource {
    
}
