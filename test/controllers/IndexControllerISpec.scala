package controllers

import helpers.ComponentSpecHelper
import helpers.ViewSpecHelper._
import org.jsoup.Jsoup
import play.api.http.Status.{OK, SEE_OTHER}
import play.api.test.Helpers.LOCATION

class IndexControllerISpec extends ComponentSpecHelper {

  object IndexMessages {
    private val suffix = " - Register for Boiled Sweet Tax - GOV.UK"
    val heading: String = "Welcome to the Boiled Sweet Tax registration service"
    val title: String = heading + suffix
    val subheadingOne = ""
    val subheadingTwo = "Click continue to begin"
    val continueButton = "Continue"
  }

  "GET '/'" should {
    "return 200 OK" in {
      val result = get("/")

      result.status shouldBe OK
    }

    "return a view" in {
      val result = get("/")

      val document = Jsoup.parse(result.body)

      document.title shouldBe IndexMessages.title
      document.getHeadingElements.text() shouldBe IndexMessages.heading
      document.getSubheadingElements.text() should include(List(IndexMessages.subheadingOne, IndexMessages.subheadingTwo).mkString(" "))
      document.getSubmitButton.text() shouldBe IndexMessages.continueButton
    }
  }

  "POST '/'" should {
    "redirect to the next page" in {
      val result = post("/")()

      result.status shouldBe SEE_OTHER
      result.header(LOCATION) shouldBe Some("/business-name")
    }
  }

}
