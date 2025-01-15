package cuck.DemoAPI;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*; // this package we need to import it manually since the functions in it are static
import static org.hamcrest.Matchers.*;	// another static package 

import files.payload;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Scenario 1: Validate if Add Place API is working as expected
		
//		Rest Assured works in three principles
//		1) given - all input details
//		2) when - submit the API (resource and Http methods always goes into when method)
//		3) then - validate the response
		
//		Any rest Assured automation tests should be written in these 3 principles only.
		
//		First we have to set base URL
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		// given method is coming from this package "static io.restassured.RestAssured.*"
		// then we are chaining up all the details inside the given method to pass all the details
		
//		log().all() is used to see the input and output generated
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.AddPlace()).when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)");
		
		// whatever we write after the 'assertThat', it will used to assert the components
		//equalTo is also a static package from this package "static org.hamcrest.Matchers.*"
		// .header in the 'when' principle takes the input but after the 'then' principle it is used for the output
		
		
//		Scenario 2: Add Place >> Update place with New Address >> Get Place to validate if New Address is present in response
		
	}

}
