package services

import controllers.routes
import org.scalatest.matchers.should
import org.scalatest.wordspec.AnyWordSpec
import play.api.test.FakeRequest
import uk.gov.hmrc.govukfrontend.views.Aliases.{ActionItem, Actions, HtmlContent, SummaryListRow, Text, Value}
import uk.gov.hmrc.govukfrontend.views.viewmodels.summarylist.Key

class SummaryListRowBuilderSpec extends AnyWordSpec with should.Matchers {

  "SummaryListRowBuilder.build()" should {
    "build a sequence of summary list rows" when {
      "business name and business address exist in session" in {
        val service = new SummaryListRowBuilder()
        val businessName = "Test Business Name"

        val result = service.build()(FakeRequest().withSession(
          "businessName" -> businessName,
          "businessDate.day" -> "12",
          "businessDate.month" -> "5",
          "businessDate.year" -> "2007",
          "businessAddress.houseNumber" -> "Test House Number",
          "businessAddress.street" -> "Test Street",
          "businessAddress.city" -> "Test City",
          "businessAddress.county" -> "Test County",
          "businessAddress.postcode" -> "Test Postcode"
        ))

        result shouldBe Seq(
          SummaryListRow(
            key = Key(Text("Business name")),
            value = Value(Text(businessName)),
            actions = Some(Actions(items = Seq(
              ActionItem(
                content = Text("Change"),
                href = routes.BusinessNameController.show().url
              )
            )))
          ),
          SummaryListRow(
            key = Key(Text("Business Start Date")),
            value = Value(HtmlContent("12 / 5 / 2007")),
            actions = Some(Actions(items = Seq(
              ActionItem(
                content = Text("Change"),
                href = routes.BusinessDateController.show().url
              )
            )))
          ),
          SummaryListRow(
            key = Key(Text("Business address")),
            value = Value(HtmlContent("Test House Number Test Street<br />Test City<br />Test County<br />Test Postcode")),
            actions = Some(Actions(items = Seq(
              ActionItem(
                content = Text("Change"),
                href = routes.BusinessAddressController.show().url
              )
            )))
          )
        )
      }
      "there is no data in session" in {
        val service = new SummaryListRowBuilder()

        val result = service.build()(FakeRequest())

        result shouldBe Seq(
          SummaryListRow(
            key = Key(Text("Business name")),
            value = Value(Text("")),
            actions = Some(Actions(items = Seq(
              ActionItem(
                content = Text("Change"),
                href = routes.BusinessNameController.show().url
              )
            )))
          ),
          SummaryListRow(
            key = Key(Text("Business Start Date")),
            value = Value(HtmlContent(" /  / ")),
            actions = Some(Actions(items = Seq(
              ActionItem(
                content = Text("Change"),
                href = routes.BusinessDateController.show().url
              )
            )))
          ),
          SummaryListRow(
            key = Key(Text("Business address")),
            value = Value(HtmlContent(" <br /><br /><br />")),
            actions = Some(Actions(items = Seq(
              ActionItem(
                content = Text("Change"),
                href = routes.BusinessAddressController.show().url
              )
            )))
          )
        )
      }
    }
  }

}
