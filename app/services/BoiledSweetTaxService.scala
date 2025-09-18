package services

import connectors.BoiledSweetTax
import controllers.routes

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class BoiledSweetTaxService @Inject()(boiledSweetTax: BoiledSweetTax)(implicit ec: ExecutionContext) {

  def fetchUtr(): Future[Option[String]] = {
    boiledSweetTax.getUtrValue.map{
      response => {
        response.map{ value =>
          (value \ "utr").as[String]
        }
      }
    }

  }
}

