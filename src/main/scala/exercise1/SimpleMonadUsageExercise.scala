package exercise1

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class PriceCalculator(productService: ExternalProductService, priceService: ExternalPriceService) {
  def calculatePrice: Future[Price] = ???
}

case class Product(name: String)
case class Price(value: Long)

trait ExternalProductService {
  def getProduct: Future[Product]
}

trait ExternalPriceService {
  def getPrice(product: Product): Future[Price]
}
