/*
 * Copyright 2024 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package helpers

import org.jsoup.nodes.Element
import org.jsoup.select.Elements

object ViewSpecHelper {

  implicit class ElementExtensions(element: Element) {

    lazy val getParagraphs: Elements = element.getElementsByTag("p")

    lazy val getBulletPoints: Elements = element.getElementsByTag("li")

    lazy val getHeadingElements: Elements = element.getElementsByTag("h1")

    lazy val getSubheadingElements: Elements = element.getElementsByTag("h2")

    lazy val getFormElements: Elements = element.getElementsByClass("form-field-group")

    lazy val getLabelElement: Elements = element.getElementsByTag("label")

    lazy val getErrorSummaryTitle: Elements = element.getElementsByClass("govuk-error-summary__title")

    lazy val getErrorSummaryBody: Elements = element.getElementsByClass("govuk-error-summary__body")

    lazy val getFieldErrorMessage: Elements = element.getElementsByClass("govuk-error-message")

    lazy val getSubmitButton: Elements = element.getElementsByClass("govuk-button")

    lazy val getHints: Elements = element.getElementsByClass("govuk-hint")

    lazy val getForm: Elements = element.select("form")

    lazy val getSummaryListRows: Elements = element.getElementsByClass("govuk-summary-list__row")

    lazy val getServiceName: Elements = element.getElementsByClass("govuk-header__service-name")

    def getTextFieldInput(id: String): Elements = element.select(s"""input[id=$id]""")

    def getBulletPointList: Elements = element.select("ul[class=list list-bullet]")

    def getSummaryListQuestion: String = element.getElementsByClass("govuk-summary-list__key").text

    def getSummaryListAnswer: String = element.getElementsByClass("govuk-summary-list__value").text

    def getSummaryListChangeLink: String = element.select("dd.govuk-summary-list__actions > a").attr("href")

    def getSummaryListChangeText: String = element.select("dd.govuk-summary-list__actions > a").text

  }

}
