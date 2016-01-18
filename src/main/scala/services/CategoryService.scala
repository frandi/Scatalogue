package scatalogue.services

import scatalogue.entities.{Category, CategoryUpdate}

import scala.concurrent.{ExecutionContext, Future}

class CategoryService(implicit val executionContext: ExecutionContext) {

  var categories = Vector.empty[Category]
  
  def createCategory(category: Category): Future[Option[String]] = Future {
    categories.find(_.id == category.id) match {
      case Some(q) => None // Conflict! id is already taken
      case None =>
        categories = categories :+ category
        Some(category.id)
    }
  }
  
  def getCategory(id: String): Future[Option[Category]] = Future {
    categories.find(_.id == id)
  }
  
  def updateCategory(id: String, update: CategoryUpdate): Future[Option[Category]] = {

    def updateEntity(category: Category): Category = {
      val name = update.name.getOrElse(category.name)
      val parentid = update.parentid.getOrElse(category.parentid)
      Category(id, name, parentid)
    }

    getCategory(id).flatMap { maybeCategory =>
      maybeCategory match {
        case None => Future { None } // No category found, nothing to update
        case Some(category) =>
          val updatedCategory = updateEntity(category)
          deleteCategory(id).flatMap { _ =>
            createCategory(updatedCategory).map(_ => Some(updatedCategory))
          }
      }
    }
  }
  
  def deleteCategory(id: String): Future[Unit] = Future {
    categories = categories.filterNot(_.id == id)
  }
}