package connectors

import play.api.libs.json.JsValue

import javax.inject.{Inject, Singleton}
import play.api.libs.ws.{EmptyBody, WSClient, WSResponse}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class BoiledSweetTax @Inject()(ws: WSClient)(implicit ec: ExecutionContext) {

  private val url = "http://localhost:9002/boiled-sweet-tax-registration/submit"

  def getUtrValue: Future[JsValue] = {
      ws.url(url).post("").map(_.json)
  }
}
