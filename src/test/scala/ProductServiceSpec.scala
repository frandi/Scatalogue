package scatalogue

import scatalogue.resources.ProductResource
import scatalogue.services.ProductService
import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import spray.http._
import StatusCodes._

class ProductServiceSpec extends Specification with Specs2RouteTest with ProductResource {
  def actorRefFactory = system
  implicit val executionContext = system.dispatcher
  val productService = new ProductService
  
  "Product resource" should {
    "leave GET requests to other paths unhandled" in {
        Get("/nothing") ~> productRoutes ~> check {
            handled must beFalse
        }
    }
  }
}