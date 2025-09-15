package controllers

import play.api.mvc._
import views.html.IndexView

import javax.inject._

@Singleton
class IndexController @Inject()(view: IndexView) extends MessagesInjectedController {

  def show(): Action[AnyContent] = Action {
    implicit request: MessagesRequest[AnyContent] =>
      Ok(view(formAction = routes.IndexController.submit()))
  }

  def submit(): Action[AnyContent] = Action(Redirect(routes.BusinessNameController.show()))

}
