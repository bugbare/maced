package maced

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("https://registration-uat.ws.macmillaneducation.com")
		.inferHtmlResources()

	object Home {

    val home = exec(http("Home")
        .get(uri02 + "/")
        .check(status.is(200)))
        .pause(1)
  	}

	val headers_plain_text = Map(
		"Accept" -> "text/plain, */*; q=0.01",
		"Accept-Encoding" -> "gzip, deflate",
		"Cache-Control" -> "no-cache",
		"Origin" -> "https://skillful-uat.macmillan.education",
		"Pragma" -> "no-cache")

	val headers_cache_control = Map(
		"Cache-Control" -> "no-cache",
		"Pragma" -> "no-cache")

	val headers_html_xml_webp = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Cache-Control" -> "no-cache",
		"Pragma" -> "no-cache",
		"Upgrade-Insecure-Requests" -> "1")

	//val headers_css = Map(
	//	"Accept" -> "text/css,*/*;q=0.1",
	//	"Cache-Control" -> "no-cache",
	//	"Pragma" -> "no-cache") 

	//val headers_resources_page = Map(
	//	"accept" -> "*/*",
	//	"accept-encoding" -> "gzip, deflate, sdch",
	//	"accept-language" -> "en-US,en;q=0.8",
	//	"cache-control" -> "no-cache",
	//	"pragma" -> "no-cache",
	//	"referer" -> "https://skillful-uat.macmillan.education/resources/",
	//	"user-agent" -> "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36")

	val headers_23 = Map(
		"Accept" -> "image/webp,image/*,*/*;q=0.8",
		"Cache-Control" -> "no-cache",
		"Pragma" -> "no-cache")

	val headers_24 = Map(
		"Cache-Control" -> "no-cache",
		"Origin" -> "https://skillful-uat.macmillan.education",
		"Pragma" -> "no-cache")

	val headers_authorization = Map(
		"Access-Control-Request-Headers" -> "accept, authorization",
		"Access-Control-Request-Method" -> "GET",
		"Cache-Control" -> "no-cache",
		"Origin" -> "https://skillful-uat.macmillan.education",
		"Pragma" -> "no-cache")

	val headers_35 = Map(
		"Accept" -> "application/json, text/javascript, */*; q=0.01",
		"Cache-Control" -> "no-cache",
		"Origin" -> "https://skillful-uat.macmillan.education",
		"Pragma" -> "no-cache")

    //val uri01 = "rdc-cdn.lms.macmillaneducation.com"
    val uri02 = "https://skillful-uat.macmillan.education"
    //val uri03 = "https://maced-code-repo.s3.amazonaws.com/integrate"
    //val uri04 = "https://code.ws.macmillaneducation.com/integrate"
    val uri05 = "https://mee-uat-dataservices-subscription.ws.macmillaneducation.com/Svc/Subscription"
    //val uri06 = "code.jquery.com"
    //val uri07 = "rdc-uat.macmillan.education"
    val uri08 = "https://registration-uat.ws.macmillaneducation.com/index-xml.php"
    //val uri09 = "https://mee-uat-cdn.ws.macmillaneducation.com/WebUI"
    //val uri10 = "https://use.typekit.net"

	val scn = scenario("RecordedSimulation")
		.exec(Home.home)
		.exec(http("getLogin")
			.post("/index-xml.php?site_id=RDCv2")
			.headers(headers_plain_text)
			.formParam("action", "get_standalone_login_html")
			.formParam("SID", "qe82igsgtngvjpqejfafl8sou1")
			.formParam("callback", "MEC_webservices.WS_launch_fancybox"))
		.pause(17)
		.exec(http("postLoginDetails")
			.post("/index-xml.php?site_id=RDCv2")
			.headers(headers_plain_text)
			.formParam("username", "rshah")
			.formParam("password", "PerfTest01")
			.formParam("action", "loginpost")
			.formParam("SID", "qe82igsgtngvjpqejfafl8sou1")
			.formParam("second_callback", "is_logged_in_hook")
			.formParam("callback", "MEC_webservices.WS_callback_function_CORS256063")
			.resources(http("getResourceList1")
			.get(uri08 + "?site_id=RDCv2&callback=jQuery183106605701891239733_1448553188598&action=get_sgk_cookie&SID=qe82igsgtngvjpqejfafl8sou1&_=1448553266077")
			.headers(headers_cache_control),
            http("getResourceList2")
			.get(uri08 + "?site_id=RDCv2&callback=jQuery183106605701891239733_1448553188599&action=get_MEE_OAUTH_cookie&SID=qe82igsgtngvjpqejfafl8sou1&_=1448553266080")
			.headers(headers_cache_control),
            http("getResourceList3")
			.get(uri08 + "?site_id=RDCv2&callback=jQuery183106605701891239733_1448553188598&action=get_MEE_OAUTH_cookie&SID=qe82igsgtngvjpqejfafl8sou1&_=1448553266210")
			.headers(headers_cache_control),
            http("getResourcePage")
			.get(uri02 + "/resources/")
			.headers(headers_html_xml_webp))
			)
		.pause(1)
		.exec(http("postRDC")
			.post("/index-xml.php?site_id=RDCv2")
			.headers(headers_plain_text)
			.formParam("action", "is_valid_session_id")
			.formParam("SID", "qe82igsgtngvjpqejfafl8sou1")
			.formParam("callback", "MEC_webservices.WS_callback_function_CORS135987")
			.resources(http("postResourceCheck1")
			.post(uri08 + "?site_id=RDCv2")
			.headers(headers_plain_text)
			.formParam("action", "isloggedin")
			.formParam("SID", "qe82igsgtngvjpqejfafl8sou1")
			.formParam("second_callback", "WS_INIT")
			.formParam("callback", "MEC_webservices.WS_callback_function_CORS74532"),
            http("postResourceCheck2")
			.post(uri08 + "?site_id=RDCv2")
			.headers(headers_plain_text)
			.formParam("action", "isloggedin")
			.formParam("SID", "qe82igsgtngvjpqejfafl8sou1")
			.formParam("second_callback", "is_logged_in_hook")
			.formParam("callback", "MEC_webservices.WS_callback_function_CORS703806"),
            http("getResources1")
			.options(uri05 + "/ResourceLinks/?_=1448553267473")
			.headers(headers_authorization),
            http("getResources2")
			.options(uri05 + "/ResourcePacks/?_=1448553267474")
			.headers(headers_authorization))
            /*http("request_35")
			.get(uri05 + "/ResourceLinks/?_=1448553267473")
			.headers(headers_35),
            http("request_36")
			.get(uri05 + "/ResourcePacks/?_=1448553267474")
			.headers(headers_35))*/
		)

	setUp(scn.inject(splitUsers(5) into (rampUsers(1) over (10 seconds)) separatedBy(59 seconds))).protocols(httpProtocol)
}