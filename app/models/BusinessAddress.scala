package models

import play.api.libs.json.{Json, OFormat}

case class BusinessAddress(houseNumber: String, street: String, city: String, county: String, postcode: String)

object BusinessAddress {
  implicit val format: OFormat[BusinessAddress] = Json.format[BusinessAddress]
}
