package cuck.DemoAPI;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*; // this package we need to import it manually since the functions in it are static
import static org.hamcrest.Matchers.*; // another static package 

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import files.payload;

public class staticJsonPayload {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
//		To Handle the static Json Payloads
		
//		First we need to read the content of static json file >> convert the content into byte >> convert the byte into Strings
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
//		Files is a static package and this method 'readAllBytes' requires a path of the external file. And we need to use Paths class to pass the path.
//		Now the whole content is in Bytes, then by adding 'new String', it will convert into string

		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(new String(Files.readAllBytes(Paths.get("D:\\Work\\Rest Assured\\addPlace.json")))).when().post("maps/api/place/add/json").then().log().all().assertThat()
				.statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)");

	}

}
