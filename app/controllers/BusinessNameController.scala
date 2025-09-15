package controllers

import forms.BusinessNameForm
import play.api.mvc.{Action, AnyContent, MessagesInjectedController, MessagesRequest}
import views.html.BusinessNameView

import javax.inject.{Inject, Singleton}

@Singleton
class BusinessNameController @Inject()(view: BusinessNameView) extends MessagesInjectedController {

  def show(): Action[AnyContent] = Action {
    implicit request: MessagesRequest[AnyContent] =>
      Ok(view(
        businessNameForm = BusinessNameForm.form,
        formAction = routes.BusinessNameController.submit()
      ))
  }

  def submit(): Action[AnyContent] = Action {
    implicit request: MessagesRequest[AnyContent] =>
      BusinessNameForm.form.bindFromRequest().fold(
        formWithErrors => BadRequest(view(
          businessNameForm = formWithErrors,
          formAction = routes.BusinessNameController.submit()
        )),
        businessName => Redirect(routes.BusinessDateController.show()).addingToSession("businessName" -> businessName)
      )
  }

}
