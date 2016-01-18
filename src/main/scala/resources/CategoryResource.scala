package scatalogue.resources

import scatalogue.entities.{CategoryUpdate, Category}
import scatalogue.routing.ScatalogueHttpService
import scatalogue.services.CategoryService
import spray.routing._

trait CategoryResource extends ScatalogueHttpService {

  val categoryService: CategoryService

  def categoryRoutes: Route = pathPrefix("categories") {
    pathEnd {
      post {
        entity(as[Category]) { category =>
          completeWithLocationHeader(
            resourceId = categoryService.createCategory(category),
            ifDefinedStatus = 201, ifEmptyStatus = 409)
          }
        }
    } ~
    path(Segment) { id =>
      get {
        complete(categoryService.getCategory(id))
      } ~
      put {
        entity(as[CategoryUpdate]) { update =>
          complete(categoryService.updateCategory(id, update))
        }
      } ~
      delete {
        complete(204, categoryService.deleteCategory(id))
      }
    }
  }
}