package controllers

import forms.BusinessDateForm
import play.api.mvc.{Action, AnyContent, MessagesInjectedController, MessagesRequest}
import views.html.BusinessDateView

import javax.inject.{Inject, Singleton}

@Singleton
class BusinessDateController @Inject()(view: BusinessDateView) extends MessagesInjectedController {

  def show(): Action[AnyContent] = Action {
    implicit request: MessagesRequest[AnyContent] =>
      Ok(view(
        businessDateForm = BusinessDateForm.form,
        formAction = routes.BusinessDateController.submit()
      ))
  }

  def submit(): Action[AnyContent] = Action {
    implicit request: MessagesRequest[AnyContent] =>
      BusinessDateForm.form.bindFromRequest().fold(
        formWithErrors => BadRequest(view(
          businessDateForm = formWithErrors,
          formAction = routes.BusinessDateController.submit()
        )),
        businessDate => Redirect(routes.BusinessAddressController.show()).addingToSession(
          "businessDate.day" -> businessDate.day,
          "businessDate.month" -> businessDate.month,
          "businessDate.year" -> businessDate.year,
        )
      )
  }

}
