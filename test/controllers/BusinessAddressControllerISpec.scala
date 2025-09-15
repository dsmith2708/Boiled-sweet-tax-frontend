package controllers

import helpers.ComponentSpecHelper
import org.jsoup.Jsoup
import helpers.ViewSpecHelper._
import play.api.http.Status.{OK, SEE_OTHER}

class BusinessAddressControllerISpec extends ComponentSpecHelper {

  object BusinessAddressMessages {
    private val suffix = " - Register for Boiled Sweet Tax - GOV.UK"
    val heading: String = "What is the address of your business?"
    val title: String = heading + suffix
    val hint: String = "This is the address of your business as it appears on official documents"
    val continueButton = "Continue"
  }

  "GET '/business-address'" should {
    "return 200 OK" in {
      val result = get("/business-address")

      result.status shouldBe OK
    }

    "return a view" in {
      val result = get("/business-address")

      val document = Jsoup.parse(result.body)

      document.title shouldBe BusinessAddressMessages.title
      document.getHeadingElements.text() shouldBe BusinessAddressMessages.heading
      document.getTextFieldInput("houseNumber").hasText shouldBe false
      document.getTextFieldInput("street").hasText shouldBe false
      document.getTextFieldInput("city").hasText shouldBe false
      document.getTextFieldInput("county").hasText shouldBe false
      document.getTextFieldInput("postcode").hasText shouldBe false
      document.getSubmitButton.text shouldBe BusinessAddressMessages.continueButton
    }
  }

  "POST '/business-address'" should {
    "redirect to the next page" in {
      val result = post("/business-address")(
        "houseNumber" -> "1",
        "street" -> "Test Street",
        "city" -> "Test City",
        "county" -> "Test County",
        "postcode" -> "AA1 1AA"
      )

      result.status shouldBe SEE_OTHER
      result.header("Location") shouldBe Some("/check-answers")
    }
  }

}
