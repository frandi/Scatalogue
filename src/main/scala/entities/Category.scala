package scatalogue.entities

case class Category(id: String, name: String, parentid: String)

case class CategoryUpdate(name: Option[String], parentid: Option[String])