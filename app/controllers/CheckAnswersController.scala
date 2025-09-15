package controllers

import connectors.BoiledSweetTax
import play.api.mvc._
import services.SummaryListRowBuilder
import views.html.{CheckAnswersView, ConfirmationView}

import javax.inject._

@Singleton
class CheckAnswersController @Inject()(checkAnswersView: CheckAnswersView,
                                       summaryListRowBuilder: SummaryListRowBuilder,
                                       confirmationView: ConfirmationView,
                                       connector: BoiledSweetTax
                                      ) extends MessagesInjectedController {
  def show(): Action[AnyContent] = Action {
    implicit request: MessagesRequest[AnyContent] =>
      Ok(checkAnswersView(
        summaryListRows = summaryListRowBuilder.build(),
        formAction = routes.CheckAnswersController.submit()
      ))
  }

  def submit()() : Action[AnyContent] = Action.async {
    implicit request: MessagesRequest[AnyContent] =>
      connector.getUtrValue.map( value => {
        Ok(confirmationView(routes.IndexController.show(), value))
      })(scala.concurrent.ExecutionContext.global)
  }


}