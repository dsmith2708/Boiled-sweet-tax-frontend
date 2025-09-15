package controllers

import helpers.ComponentSpecHelper
import helpers.ViewSpecHelper._
import org.jsoup.Jsoup
import play.api.http.Status.{OK, SEE_OTHER}

class BusinessDateControllerISpec extends ComponentSpecHelper {

  object BusinessDateMessages {
    private val suffix = " - Register for Boiled Sweet Tax - GOV.UK"
    val heading: String = "What is the Date that your business started trading?"
    val title: String = heading + suffix
    val hint: String = "For example, 27 3 2007"
    val continueButton = "Continue"
  }

  "GET '/business-date'" should {
    "return 200 OK" in {
      val result = get("/business-date")

      result.status shouldBe OK
    }

    "return a view" in {
      val result = get("/business-date")

      val document = Jsoup.parse(result.body)

      document.title shouldBe BusinessDateMessages.title
      document.getHeadingElements.text() shouldBe BusinessDateMessages.heading
      document.getHints.text() shouldBe BusinessDateMessages.hint
      document.getTextFieldInput("day").hasText shouldBe false
      document.getTextFieldInput("month").hasText shouldBe false
      document.getTextFieldInput("year").hasText shouldBe false
      document.getSubmitButton.text shouldBe BusinessDateMessages.continueButton
    }
  }

  "POST '/business-date'" should {
    "redirect to the next page" in {
      val result = post("/business-date")(

        "business-start-day" -> "21",
        "business-start-month" -> "12",
        "business-start-year" -> "2007"
      )

      result.status shouldBe SEE_OTHER
      result.header("Location") shouldBe Some("/business-address")
    }
  }

}
