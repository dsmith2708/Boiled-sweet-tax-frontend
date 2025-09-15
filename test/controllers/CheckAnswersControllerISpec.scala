package controllers

import helpers.ComponentSpecHelper
import helpers.ViewSpecHelper._
import org.jsoup.Jsoup
import play.api.http.Status.{OK, SEE_OTHER}
import play.api.test.Helpers.LOCATION

class CheckAnswersControllerISpec extends ComponentSpecHelper {

  object CheckAnswerMessages {
    private val suffix = " - Register for Boiled Sweet Tax - GOV.UK"
    val heading: String = "Check your answers"
    val title: String = heading + suffix
    val confirmButton = "Confirm"
    val summaryListRowOne = "Business name"
    val summaryListRowTwo = "Business Start Date"
    val summaryListRowThree = "Business address"
  }

  val testBusinessAddress = ""

  "GET '/check-answers'" should {
    "return 200 OK" in {
      val result = get("/check-answers")

      result.status shouldBe OK
    }

    "return a view" in {
      val result = get("/check-answers")

      val document = Jsoup.parse(result.body)

      document.title shouldBe CheckAnswerMessages.title
      document.getHeadingElements.text() shouldBe CheckAnswerMessages.heading
      document.getSummaryListRows.size() shouldBe 3
      document.getSummaryListQuestion shouldBe List(CheckAnswerMessages.summaryListRowOne, CheckAnswerMessages.summaryListRowTwo, CheckAnswerMessages.summaryListRowThree).mkString(" ")
      document.getSubmitButton.text() shouldBe CheckAnswerMessages.confirmButton
    }
  }

  "POST '/check-answers'" should {
    "redirect to the next page" in {
      val result = post("/check-answers")()

      result.status shouldBe OK
    }
  }

}
