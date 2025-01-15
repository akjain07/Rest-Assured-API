package cuck.DemoAPI;

import org.testng.Assert;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	public static void main(String[] args) {

//		For the time being we don't have any API that gives this response so we have taken a mock response and this will be used for the
//		automation test development

		JsonPath js = new JsonPath(payload.coursePrice());

//		1. Print No of courses returned by API
		int courseCount = js.getInt("courses.size()");
		System.out.println(courseCount);

//		2.Print Purchase Amount
		int totalAmt = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmt);

//		3. Print Title of the first course
		String title = js.get("courses[0].title");
		System.out.println(title);

//		4. Print All course titles and their respective Prices
		for (int i = 0; i < courseCount; i++) {
			String courseTitles = js.get("courses[" + i + "].title");
			System.out.println(courseTitles);
			System.out.println(js.get("courses[" + i + "].price").toString());

//			we can't simply put this 'js.get("courses["+i+"].price")' inside the system.out.println as it doesn't understand what this js object is returning
//			so we can add '.toString' or we can store it explicitly in some variable like 'courseTitles'.

		}

//		5. Print no of copies sold by RPA Course
		for (int i = 0; i < courseCount; i++) {
			String courseTitle = js.get("courses[" + i + "].title");
			if (courseTitle.equalsIgnoreCase("RPA")) {
				System.out.println(js.get("courses[" + i + "].copies").toString());
				break;
			}
		}

	}
}
