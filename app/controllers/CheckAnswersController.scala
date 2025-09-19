package controllers

import connectors.BoiledSweetTax
import models.{BusinessAddress, BusinessData, BusinessDate}
import play.api.libs.ws.WSResponse
import play.api.mvc._
import services.{BoiledSweetTaxService, SummaryListRowBuilder}
import views.html.{CheckAnswersView, ConfirmationView, ErrorPageView}

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}

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
        )
      )
      var submitResponse: Future[WSResponse] = connector.submitBusinessData(businessData)

      submitResponse.flatMap { response =>
        response.status match {
          // The data entered isn't valid to be registered
          case BAD_REQUEST => Future(BadRequest(errorPage(response.body)))

          // The data was successfully submitted, fetch the utr and load confirmation page
          case OK => service.fetchUtr().map {
            case Some(populatedValue) => Ok(confirmationView(routes.IndexController.show(), populatedValue))
            case None => InternalServerError(errorPage("500 Error: Internal server error"))
          }(scala.concurrent.ExecutionContext.global)

          // Unknown error
          case _ => Future(Status(response.status)(errorPage(response.statusText + response.body)))
        }
      }
    }
  }
}