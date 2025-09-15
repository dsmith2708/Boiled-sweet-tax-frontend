# Boiled Sweet Tax Frontend

This is a Scala/Play frontend to allow shopkeepers to register with HMRC to pay the boiled sweet tax.

This service uses the GOV.UK Design System. For more information see [GOV.UK Design System](https://design-system.service.gov.uk/).

A helpful Chrome extension that shows the Twirl templates for the GOV.UK Design System components can be found [here](https://github.com/hmrc/play-frontend-govuk-extension).

### How to run the service

1. Run the frontend locally using `sbt run`
2. Go to `http://localhost:9000/register-for-boiled-sweet-tax` in your browser
3. Follow the instructions on the page to register for the boiled sweet tax

### Running the tests

The tests use the ScalaTest framework, for more information see [ScalaTest](http://www.scalatest.org/).

Run the tests using `sbt test`

### Other useful commands

- `sbt clean` - cleans the project of all build files
- `sbt clean test` - cleans and runs the tests for the project
- `sbt compile` - compiles the project to new build files
- `sbt clean compile` - cleans and compiles the project
- `sbt clean compile test` - cleans, compiles and runs the tests for the project

