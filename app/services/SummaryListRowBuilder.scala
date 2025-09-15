package services

import controllers.routes
import models.BusinessAddress
import models.BusinessDate
import play.api.mvc.Request
import uk.gov.hmrc.govukfrontend.views.Aliases.{Actions, HtmlContent, SummaryListRow, Value}
import uk.gov.hmrc.govukfrontend.views.viewmodels.content.Text
import uk.gov.hmrc.govukfrontend.views.viewmodels.summarylist.{ActionItem, Key}

import javax.inject.{Inject, Singleton}

@Singleton
class SummaryListRowBuilder @Inject() {

  def build()(implicit request: Request[_]): Seq[SummaryListRow] = {

    val businessName = request.session.get("businessName").getOrElse("")
    val address = BusinessAddress(
      houseNumber = request.session.get("businessAddress.houseNumber").getOrElse(""),
      street = request.session.get("businessAddress.street").getOrElse(""),
      city = request.session.get("businessAddress.city").getOrElse(""),
      county = request.session.get("businessAddress.county").getOrElse(""),
      postcode = request.session.get("businessAddress.postcode").getOrElse("")
    )

    val businessDate = request.session.get("businessDate").getOrElse("")
    val date = BusinessDate(
      day = request.session.get("businessDate.day").getOrElse(""),
      month = request.session.get("businessDate.month").getOrElse(""),
      year = request.session.get("businessDate.year").getOrElse("")
    )

    Seq(
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
        value = Value(HtmlContent(s"${date.day} / ${date.month} / ${date.year}")),
        actions = Some(Actions(items = Seq(
          ActionItem(
            content = Text("Change"),
            href = routes.BusinessDateController.show().url
          )
        )))
      ),
      SummaryListRow(
        key = Key(Text("Business address")),
        value = Value(HtmlContent(s"${address.houseNumber} ${address.street}<br />${address.city}<br />${address.county}<br />${address.postcode}")),
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
