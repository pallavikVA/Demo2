package testAPI;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.minidev.json.JSONObject;

public class postAPIrequest {
	
	@Test
	public void createbooking() {
		
	JSONObject booking = new JSONObject();
	JSONObject bookingdates = new JSONObject();
	
	booking.put("firstname","apitesting");
	booking.put("lastname","API test");
	booking.put("totalprice",1000);
	booking.put("depositpaid",true);
	booking.put("additionalneeds","breakest");
	booking.put("bookingdates",bookingdates);
	
	bookingdates.put("checkin","2018-01-01");
	bookingdates.put("checkout","2019-06-01");
	
	RestAssured
		.given()
		.contentType(ContentType.JSON)
		.body(booking.toString())
		.baseUri("https://restful-booker.herokuapp.com/booking")
		.when()
		.post()
		.then()
		.assertThat()
		.statusCode(200)
		.body("booking.firstname", Matchers.equalTo("api testing"))
		.body("booking.totalprice", Matchers.equalTo(1000));
	}

}
