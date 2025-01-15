package cuck.DemoAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*; // this package we need to import it manually since the functions in it are static
import static org.hamcrest.Matchers.*; // another static package 

import org.testng.Assert;

import files.payload;

public class E2E {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		Scenario 2: Add Place >> Update place with New Address >> Get Place to validate if New Address is present in response
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
//		In order to extract the Place Id, we need to extract the response in String format, then store it in a string variable
//		.extract().response().asString() is used to store the response in String variable
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.AddPlace()).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)")
		.extract().response().asString();
		
			System.out.println(response);
			
//			We need to parse this String response, so for that we need JsonPath class which takes String as an input and parse it into a json
			JsonPath js=new JsonPath(response);
			String placeId = js.getString("place_id");
			
			System.out.println(placeId);
			
//			Since in the json response, there is no parent that's why we have given directly place_id. Otherwise we would have to give the path like this "parent.child"
			
//		Update Place API
			
			String newAddress=" Indiana Jones ka ghosla";
			
			given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
			.body("{\r\n"
					+ "\"place_id\":\""+placeId+"\",\r\n"	// replace the place id value with "+placeId+"
					+ "\"address\":\""+newAddress+"\",\r\n"
					+ "\"key\":\"qaclick123\"\r\n"
					+ "}\r\n"
					+ "").when().put("maps/api/place/update/json")
			.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
			
			
//			Get Place API
			String updatedResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
			.when().get("maps/api/place/get/json")
			.then().log().all().assertThat().statusCode(200).extract().response().asString();
			
//			System.out.println(updatedResponse);
			JsonPath js1=new JsonPath(updatedResponse);
			String updatedAddress = js1.getString("address");
			
			System.out.println(updatedAddress);
			Assert.assertEquals(updatedAddress, newAddress);
			
	}

}
