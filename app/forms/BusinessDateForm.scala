package forms

import models.BusinessDate
import play.api.data.Form
import play.api.data.Forms.{mapping, text}

import java.time.LocalDate
import scala.util.Try

object BusinessDateForm {

  val form: Form[BusinessDate] = Form(
    mapping(
      "business-start-day" -> text
          .verifying("error.businessDate.day.empty", value => Try(value.toInt).isSuccess)
          .verifying("error.businessDate.day.range", value => if(Try(value.toInt).isSuccess) 0 < value.toInt & value.toInt <= 31 else true),
      "business-start-month" -> text
          .verifying("error.businessDate.month.empty", value => Try(value.toInt).isSuccess)
          .verifying("error.businessDate.month.range", value => if(Try(value.toInt).isSuccess) 0 < value.toInt & value.toInt <= 12 else true),
      "business-start-year" -> text
          .verifying("error.businessDate.year.empty", value => Try(value.toInt).isSuccess)
          .verifying("error.businessDate.year.range", value => if(Try(value.toInt).isSuccess) value.toInt > 0 else true)

    )(BusinessDate.apply)(BusinessDate.unapply)
        .verifying("error.businessDate.invalid", bd => Try(LocalDate.of(bd.year.toInt, bd.month.toInt, bd.day.toInt)).isSuccess)
        .verifying("error.businessDate.future", bd => if(Try(LocalDate.of(bd.year.toInt, bd.month.toInt, bd.day.toInt)).isSuccess) {
          LocalDate.of(bd.year.toInt, bd.month.toInt, bd.day.toInt).isBefore(LocalDate.now())
        } else true)
  )

}
