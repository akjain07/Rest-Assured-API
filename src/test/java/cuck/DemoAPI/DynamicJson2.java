package cuck.DemoAPI;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson2 {

//	By giving the same name to this test, we have connected our test with the DataProvider
	@Test(dataProvider = "BooksData")
	public void addBook(String isbn, String aisle) { // we need to pass same number of parameters which we have defined
														// in the array
		RestAssured.baseURI = "http://216.10.245.166";

//		Payload Creation Strategy 2: Parameterize the API tests with multiple data sets
																							
		String response = given().log().all().header("Content-Type", "application/json")
				.body(payload.AddBook2(isbn, aisle)).when().post("Library/Addbook.php").then().log().all().assertThat()
				.statusCode(200).extract().response().asString();

		JsonPath js = new JsonPath(response);
		String id = js.get("ID");
		System.out.println(id);

	}

//	we need to give one name to this dataProvider so we can link this to our test
	@DataProvider(name = "BooksData")
	public Object[][] getData() {
//		link between multi-dimensioanl array and parametrization : each array in row represents one set of data. When we link this DataProvider to our test,
//		then the test will run for the times we have data
		return new Object[][] { { "asdf", "1001" }, { "fghj", "1002" }, { "ghjk", "1003" } };

	}

}
