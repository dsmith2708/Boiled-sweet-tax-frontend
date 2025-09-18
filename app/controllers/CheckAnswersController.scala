package controllers

import connectors.BoiledSweetTax
import models.{BusinessData, BusinessAddress, BusinessDate}
import play.api.mvc._
import services.{BoiledSweetTaxService, SummaryListRowBuilder}
import views.html.{CheckAnswersView, ConfirmationView, ErrorPageView}

import javax.inject._
import scala.concurrent.ExecutionContext

@Singleton
class CheckAnswersController @Inject()(checkAnswersView: CheckAnswersView,
                                       summaryListRowBuilder: SummaryListRowBuilder,
                                       confirmationView: ConfirmationView,
                                       service: BoiledSweetTaxService,
                                       errorPage: ErrorPageView,
                                       connector: BoiledSweetTax,
                                       implicit val ec: ExecutionContext
                                      ) extends MessagesInjectedController {
  def show(): Action[AnyContent] = Action {
    implicit request: MessagesRequest[AnyContent] =>
      Ok(checkAnswersView(
        summaryListRows = summaryListRowBuilder.build(),
        formAction = routes.CheckAnswersController.submit()
      ))
  }

  def submit() : Action[AnyContent] = Action.async {
    implicit request: MessagesRequest[AnyContent] => {

      val businessData = BusinessData(
        request.session.get("businessName").getOrElse(""),
        BusinessDate(
          day = request.session.get("businessDate.day").getOrElse(""),
          month = request.session.get("businessDate.month").getOrElse(""),
          year = request.session.get("businessDate.year").getOrElse("")
        ),
        BusinessAddress(
          houseNumber = request.session.get("businessAddress.houseNumber").getOrElse(""),
          street = request.session.get("businessAddress.street").getOrElse(""),
          city = request.session.get("businessAddress.city").getOrElse(""),
          county = request.session.get("businessAddress.county").getOrElse(""),
          postcode = request.session.get("businessAddress.postcode").getOrElse("")
        ))

      connector.submitBusinessData(businessData)

      service.fetchUtr().map {
        case None => BadRequest(errorPage())
        case Some(populatedValue) => Ok(confirmationView(routes.IndexController.show(), populatedValue))
      }(scala.concurrent.ExecutionContext.global)
    }
  }


}