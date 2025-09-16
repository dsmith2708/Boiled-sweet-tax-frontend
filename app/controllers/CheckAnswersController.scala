package controllers
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
      service.fetchUtr().map(fetchUtrResponse => {
        fetchUtrResponse match {
          case None => BadRequest(errorPage())
          case Some(populatedValue) => Ok(confirmationView(routes.IndexController.show(), populatedValue))
        }
      })
    }
  }


}