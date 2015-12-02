
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://www.nature.com")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.(t|o)tf""", """.*\.png"""), WhiteList(""".*\.css"""))



    val uri1 = "www.nature.com"

	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.get("/public/resources/shunter-laserwolf/subjects/main-d13cc90de52fce27fffaeb701ef276f9.css"))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}