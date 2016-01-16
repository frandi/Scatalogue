package scatalogue.resources

import scatalogue.entities.{ProductUpdate, Product}
import scatalogue.routing.ScatalogueHttpService
import scatalogue.services.ProductService
import spray.routing._

trait ProductResource extends ScatalogueHttpService {

  val productService: ProductService

  def productRoutes: Route = pathPrefix("products") {
    pathEnd {
      post {
        entity(as[Product]) { product =>
          completeWithLocationHeader(
            resourceId = productService.createProduct(product),
            ifDefinedStatus = 201, ifEmptyStatus = 409)
          }
        }
    } ~
    path(Segment) { id =>
      get {
        complete(productService.getProduct(id))
      } ~
      put {
        entity(as[ProductUpdate]) { update =>
          complete(productService.updateProduct(id, update))
        }
      } ~
      delete {
        complete(204, productService.deleteProduct(id))
      }
    }
  }
}