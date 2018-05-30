package exercise1

import org.scalatest.{Matchers, WordSpec}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class PriceCalculatorTest extends WordSpec with Matchers {

  class ProductService extends ExternalProductService {
    override def getProduct: Future[Product] = Future.successful {
      Product("Banana")
    }
  }

  class PriceService extends ExternalPriceService {
    override def getPrice(product: Product): Future[Price] = Future.successful {
      product match {
        case Product("Banana") => Price(2)
        case Product("Apple") => Price(1)
        case _ => throw new Exception("Not having price for product")
      }
    }
  }

  "Price should be calculated for product returned by ProductService" in {
    val service = new PriceCalculator(new ProductService, new PriceService)

    service.calculatePrice.map(_ shouldBe Price(2))
  }

}
