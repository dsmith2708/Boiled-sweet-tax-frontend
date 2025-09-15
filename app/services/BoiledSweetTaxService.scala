package services

import connectors.BoiledSweetTax
import controllers.routes

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class BoiledSweetTaxService @Inject()(boiledSweetTax: BoiledSweetTax)(implicit ec: ExecutionContext) {

  def fetchUtr(): Future[String] = {
    boiledSweetTax.getUtrValue.map{
      response => {
        (response \ "utr").as[String]
      }
    }

  }
}

