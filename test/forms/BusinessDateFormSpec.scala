package forms

import org.scalatest.matchers.should
import org.scalatest.wordspec.AnyWordSpec
import play.api.data.FormError

class BusinessDateFormSpec extends AnyWordSpec with should.Matchers {


  "BusinessDateForm" should {
    "bind successfully with valid data" in {
      val data = Map(
        "business-start-day" -> "12",
        "business-start-month" -> "5",
        "business-start-year" -> "2007"
      )
      val form = BusinessDateForm.form.bind(data)
      form.errors shouldBe empty
    }

    "fail to bind when day is empty" in {
      val data = Map(
        "business-start-day" -> "",
        "business-start-month" -> "5",
        "business-start-year" -> "2007"
      )
      val form = BusinessDateForm.form.bind(data)
      form.errors should contain(FormError("business-start-day", "error.businessDate.day.empty"))
    }

    "fail to bind when month is empty" in {
      val data = Map(
        "business-start-day" -> "21",
        "business-start-month" -> "",
        "business-start-year" -> "2007"
      )
      val form = BusinessDateForm.form.bind(data)
      form.errors should contain(FormError("business-start-month", "error.businessDate.month.empty"))
    }

    "fail to bind when year is empty" in {
      val data = Map(
        "business-start-day" -> "12",
        "business-start-month" -> "5",
        "business-start-year" -> ""
      )
      val form = BusinessDateForm.form.bind(data)
      form.errors should contain(FormError("business-start-year", "error.businessDate.year.empty"))
    }

    "fail to bind when all are empty" in {
      val data = Map(
        "business-start-day" -> "",
        "business-start-month" -> "",
        "business-start-year" -> ""
      )
      val form = BusinessDateForm.form.bind(data)
      form.errors should contain(FormError("business-start-day", "error.businessDate.day.empty"))
      form.errors should contain(FormError("business-start-month", "error.businessDate.month.empty"))
      form.errors should contain(FormError("business-start-year", "error.businessDate.year.empty"))
    }
  }
}

