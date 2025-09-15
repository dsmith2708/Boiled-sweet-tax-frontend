package forms

import org.scalatest.matchers.should
import org.scalatest.wordspec.AnyWordSpec
import play.api.data.FormError

class BusinessNameFormSpec extends AnyWordSpec with should.Matchers {

  "BusinessNameForm" should {
    "bind successfully with valid data" in {
      val data = Map(
        "businessName" -> "Test Business Name"
      )
      val form = BusinessNameForm.form.bind(data)
      form.errors shouldBe empty
    }

    "fail to bind when businessName is empty" in {
      val data = Map(
        "businessName" -> ""
      )
      val form = BusinessNameForm.form.bind(data)
      form.errors should contain(FormError("businessName", "error.businessName.empty"))
    }
  }

}
