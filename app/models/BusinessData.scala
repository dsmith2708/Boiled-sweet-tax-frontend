/*
 * Copyright 2025 HM Revenue & Customs
 *
 */

package models
import play.api.libs.json._
case class BusinessData(
                         businessName: String,
                         businessDate: BusinessDate,
                         businessAddress: BusinessAddress
                       )

object BusinessData {
  implicit val format: OFormat[BusinessData] = Json.format[BusinessData]
}
