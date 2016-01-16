package scatalogue.services

import scatalogue.entities.{Product, ProductUpdate}

import scala.concurrent.{ExecutionContext, Future}

class ProductService(implicit val executionContext: ExecutionContext) {

  var products = Vector.empty[Product]

  def createProduct(product: Product): Future[Option[String]] = Future {
    products.find(_.id == product.id) match {
      case Some(q) => None // Conflict! id is already taken
      case None =>
        products = products :+ product
        Some(product.id)
    }
  }
  
  def getProduct(id: String): Future[Option[Product]] = Future {
    products.find(_.id == id)
  }

  def updateProduct(id: String, update: ProductUpdate): Future[Option[Product]] = {

    def updateEntity(product: Product): Product = {
      val name = update.name.getOrElse(product.name)
      Product(id, name)
    }

    getProduct(id).flatMap { maybeProduct =>
      maybeProduct match {
        case None => Future { None } // No product found, nothing to update
        case Some(product) =>
          val updatedProduct = updateEntity(product)
          deleteProduct(id).flatMap { _ =>
            createProduct(updatedProduct).map(_ => Some(updatedProduct))
          }
      }
    }
  }

  def deleteProduct(id: String): Future[Unit] = Future {
    products = products.filterNot(_.id == id)
  }
}