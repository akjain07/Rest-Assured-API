package cuck.DemoAPI;

import org.testng.annotations.Test;

import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {

	@Test
	public void addBook() {
		RestAssured.baseURI = "http://216.10.245.166";
//		Payload Creation Strategy 1: Dynamically build Json Payload with external Data Inputs
//																							sending parameters to payload through test
		String response = given().log().all().header("Content-Type", "application/json")
				.body(payload.AddBook1("ankit", "104")).when().post("Library/Addbook.php").then().log().all()
				.assertThat().statusCode(200).extract().response().asString();

		JsonPath js = new JsonPath(response);
		String id = js.get("ID");
		System.out.println(id);

	}

}
