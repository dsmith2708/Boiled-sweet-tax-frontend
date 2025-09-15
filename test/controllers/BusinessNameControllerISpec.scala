package controllers

import helpers.ComponentSpecHelper
import helpers.ViewSpecHelper._
import org.jsoup.Jsoup
import play.api.http.Status.{OK, SEE_OTHER}
import play.api.test.Helpers.LOCATION

class BusinessNameControllerISpec extends ComponentSpecHelper {

  object BusinessNameMessages {
    private val suffix = " - Register for Boiled Sweet Tax - GOV.UK"
    val heading: String = "What is the name of your business?"
    val title: String = heading + suffix
    val hint: String = "This is the name of your business as it appears on official documents"
    val continueButton = "Continue"
  }

  "GET '/business-name'" should {
    "return 200 OK" in {
      val result = get("/business-name")

      result.status shouldBe OK
    }

    "return a view" in {
      val result = get("/business-name")

      val document = Jsoup.parse(result.body)

      document.title shouldBe BusinessNameMessages.title
      document.getHeadingElements.text() shouldBe BusinessNameMessages.heading
      document.getHints.text() shouldBe BusinessNameMessages.hint
      document.getTextFieldInput("businessName").hasText shouldBe false
      document.getSubmitButton.text shouldBe BusinessNameMessages.continueButton
    }
  }

  "POST '/business-name'" should {
    "redirect to the next page" in {
      val result = post("/business-name")("businessName" -> "Test Business Name")

      result.status shouldBe SEE_OTHER
      result.header(LOCATION) shouldBe Some("/business-date")
    }
  }

}
