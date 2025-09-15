package forms

import org.scalatest.matchers.should
import org.scalatest.wordspec.AnyWordSpec
import play.api.data.FormError

class BusinessAddressFormSpec extends AnyWordSpec with should.Matchers {


  "BusinessAddressForm" should {
    "bind successfully with valid data" in {
      val data = Map(
        "houseNumber" -> "123",
        "street" -> "Main Street",
        "city" -> "Anytown",
        "county" -> "Anycounty",
        "postcode" -> "A1AA 1AA"
      )
      val form = BusinessAddressForm.form.bind(data)
      form.errors shouldBe empty
    }

    "fail to bind when houseNumber is empty" in {
      val data = Map(
        "houseNumber" -> "",
        "street" -> "Main Street",
        "city" -> "Anytown",
        "county" -> "Anycounty",
        "postcode" -> "A1AA 1AA"
      )
      val form = BusinessAddressForm.form.bind(data)
      form.errors should contain(FormError("houseNumber", "error.businessAddress.houseNumber.empty"))
    }

    "fail to bind when street is empty" in {
      val data = Map(
        "houseNumber" -> "123",
        "street" -> "",
        "city" -> "Anytown",
        "county" -> "Anycounty",
        "postcode" -> "A1AA 1AA"
      )
      val form = BusinessAddressForm.form.bind(data)
      form.errors should contain(FormError("street", "error.businessAddress.street.empty"))
    }

    "fail to bind when city is empty" in {
      val data = Map(
        "houseNumber" -> "123",
        "street" -> "Main Street",
        "city" -> "",
        "county" -> "Anycounty",
        "postcode" -> "A1AA 1AA"
      )
      val form = BusinessAddressForm.form.bind(data)
      form.errors should contain(FormError("city", "error.businessAddress.city.empty"))
    }

    "fail to bind when postcode is empty" in {
      val data = Map(
        "houseNumber" -> "123",
        "street" -> "Main Street",
        "city" -> "Anytown",
        "county" -> "Anycounty",
        "postcode" -> ""
      )
      val form = BusinessAddressForm.form.bind(data)
      form.errors should contain(FormError("postcode", "error.businessAddress.postcode.invalid"))
    }
    "fail to bind when all are empty" in {
      val data = Map(
        "houseNumber" -> "",
        "street" -> "",
        "city" -> "",
        "county" -> "",
        "postcode" -> ""
      )
      val form = BusinessAddressForm.form.bind(data)
      form.errors should contain(FormError("houseNumber", "error.businessAddress.houseNumber.empty"))
      form.errors should contain(FormError("street", "error.businessAddress.street.empty"))
      form.errors should contain(FormError("city", "error.businessAddress.city.empty"))
      form.errors should contain(FormError("postcode", "error.businessAddress.postcode.invalid"))
    }
  }
}
