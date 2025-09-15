name := """boiled-sweet-tax-registration-frontend"""

version := "0.1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.14"

libraryDependencies += guice
libraryDependencies += ws
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.1" % Test
libraryDependencies += "uk.gov.hmrc" %% "play-frontend-hmrc-play-30" % "10.9.0"
libraryDependencies += "org.jsoup" % "jsoup" % "1.18.1"


resolvers += MavenRepository("HMRC-open-artefacts-maven2", "https://open.artefacts.tax.service.gov.uk/maven2")
resolvers += Resolver.url("HMRC-open-artefacts-ivy2", url("https://open.artefacts.tax.service.gov.uk/ivy2"))(Resolver.ivyStylePatterns)
resolvers += Resolver.typesafeRepo("releases")


// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/app.routes
// play.sbt.app.routes.RoutesKeys.routesImport += "com.example.binders._"
