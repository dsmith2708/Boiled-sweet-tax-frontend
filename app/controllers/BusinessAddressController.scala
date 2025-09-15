package controllers

import forms.BusinessAddressForm
import play.api.mvc.{Action, AnyContent, MessagesInjectedController, MessagesRequest}
import views.html.BusinessAddressView

import javax.inject.{Inject, Singleton}

@Singleton
class BusinessAddressController @Inject()(view: BusinessAddressView) extends MessagesInjectedController {

  def show(): Action[AnyContent] = Action {
    implicit request: MessagesRequest[AnyContent] =>
      Ok(view(
        businessAddressForm = BusinessAddressForm.form,
        formAction = routes.BusinessAddressController.submit()
      ))
  }

  def submit(): Action[AnyContent] = Action {
    implicit request: MessagesRequest[AnyContent] =>
      BusinessAddressForm.form.bindFromRequest().fold(
        formWithErrors => BadRequest(view(
          businessAddressForm = formWithErrors,
          formAction = routes.BusinessAddressController.submit()
        )),
        businessAddress => Redirect(routes.CheckAnswersController.show()).addingToSession(
          "businessAddress.houseNumber" -> businessAddress.houseNumber,
          "businessAddress.street" -> businessAddress.street,
          "businessAddress.city" -> businessAddress.city,
          "businessAddress.county" -> businessAddress.county,
          "businessAddress.postcode" -> businessAddress.postcode
        )
      )
  }

}
