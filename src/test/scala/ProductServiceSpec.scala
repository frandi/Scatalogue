package scatalogue

import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import spray.http._
import StatusCodes._

class ProductServiceSpec extends Specification with Specs2RouteTest with ProductService {
  def actorRefFactory = system
  
  "ProductService" should {
    "return Ping! for GET request to the ping path" in {
        Get("/ping") ~> rootRoute ~> check {
            responseAs[String] must contain("Ping!")
        }
    }
    
    "return Pong!? for GET request to the pong path" in {
        Get("/pong") ~> rootRoute ~> check {
            responseAs[String] must contain("Pong!?")
        }
    }
  }
}