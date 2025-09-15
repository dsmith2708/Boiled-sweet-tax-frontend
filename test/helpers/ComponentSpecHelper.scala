package helpers

import org.scalatest.matchers.should
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import play.api.Application
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.ws.{WSClient, WSRequest, WSResponse}
import play.api.test.Helpers._

trait ComponentSpecHelper extends AnyWordSpec with should.Matchers with GuiceOneServerPerSuite {

  override lazy val app: Application = new GuiceApplicationBuilder()
    .configure("play.http.router" -> "app.Routes")
    .build()

  implicit val ws: WSClient = app.injector.instanceOf[WSClient]

  def get(uri: String): WSResponse = await(buildClient(uri).get())

  def post(uri: String)(form: (String, String)*): WSResponse = {
    val formBody = (form map { case (k, v) => (k, Seq(v)) }).toMap
    await(buildClient(uri).post(formBody))
  }

  private def buildClient(path: String): WSRequest =
    ws.url(s"http://localhost:$port$path").withFollowRedirects(false)

}
