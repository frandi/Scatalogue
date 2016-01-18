package scatalogue.entities

case class Product(id: String, name: String, categoryid: String)

case class ProductUpdate(name: Option[String], categoryid: Option[String])