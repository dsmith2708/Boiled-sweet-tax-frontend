package connectors

import com.fasterxml.jackson.databind.JsonNode
import play.libs.Json
import play.api.libs.json._

import models.BusinessData


import javax.inject.{Inject, Singleton}
import play.api.libs.ws.{BodyWritable, EmptyBody, WSClient, WSResponse}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class BoiledSweetTax @Inject()(ws: WSClient)(implicit ec: ExecutionContext) {

  private val getUtrUrl = "http://localhost:9002/boiled-sweet-tax-registration/submit"
  private val submitDataUrl = "http://localhost:9002/boiled-sweet-tax-registration/submitData"

  def getUtrValue: Future[Option[JsValue]] = {
      ws.url(getUtrUrl).post("").map( value => Some(value.json)).recover(e => None)
  }

  def submitBusinessData(data: BusinessData): Unit = {
    val json: JsValue = JsObject(Seq(
      "businessName" -> JsString(data.businessName),
      "businessDate" -> JsObject(Seq(
        "day" -> JsString(data.businessDate.day),
        "month" -> JsString(data.businessDate.month),
        "year" -> JsString(data.businessDate.year)
      )),
      "businessAddress" -> JsObject(Seq(
        "houseNumber" -> JsString(data.businessAddress.houseNumber),
        "street" -> JsString(data.businessAddress.street),
        "city" -> JsString(data.businessAddress.city),
        "county" -> JsString(data.businessAddress.county),
        "postcode" -> JsString(data.businessAddress.postcode)
      ))
    ))

    ws.url(submitDataUrl).post(json)
  }




}
