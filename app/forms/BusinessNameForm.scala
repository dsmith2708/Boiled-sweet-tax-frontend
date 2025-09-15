package forms

import play.api.data.Form
import play.api.data.Forms.text

object BusinessNameForm {

  val form: Form[String] = Form(
    "businessName" -> text.verifying("error.businessName.empty", _.nonEmpty)
      .verifying("error.businessName.invalid", _.matches("[A-Za-z '-]*[.]?"))
  )

}
