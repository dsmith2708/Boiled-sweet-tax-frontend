package models

import play.api.libs.json.{Json, OFormat}

/* case class BusinessDate(day: Int, month: Int, year: Int)*/

case class BusinessDate(day: String, month: String, year: String)

object BusinessDate {
  implicit val format: OFormat[BusinessDate] = Json.format[BusinessDate]
}