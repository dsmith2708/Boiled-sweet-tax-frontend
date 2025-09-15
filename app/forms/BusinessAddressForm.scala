package forms

import models.BusinessAddress
import play.api.data.Form
import play.api.data.Forms._

object BusinessAddressForm {

  val form: Form[BusinessAddress] = Form(
    mapping(
      "houseNumber" -> text
          .verifying("error.businessAddress.houseNumber.empty", _.nonEmpty)
          .verifying("error.businessAddress.houseNumber.invalid", _.matches("[A-Za-z0-9 '-]*")),
      "street" -> text
          .verifying("error.businessAddress.street.empty", _.nonEmpty)
          .verifying("error.businessAddress.street.invalid", _.matches("[A-Za-z '-]*[.]?")),
      "city" -> text
          .verifying("error.businessAddress.city.empty", _.nonEmpty)
          .verifying("error.businessAddress.city.invalid", _.matches("[A-Za-z '-]*")),
      "county" -> text
          .verifying("error.businessAddress.county.invalid", _.matches("[A-Za-z '-]*")),
      "postcode" -> text
          .verifying("error.businessAddress.postcode.invalid", _.matches("[A-Za-z0-9]{2,4} [A-Za-z0-9]{3}"))
    )(models.BusinessAddress.apply)(models.BusinessAddress.unapply)
  )

}
