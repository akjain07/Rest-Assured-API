package cuck.DemoAPI;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

//6. Verify if Sum of all Course prices matches with Purchase Amount

public class SumValidation {
	
//	Since we have TestNG library so we do not need 'public static void main' like we have used in ComplexJsonParse class. When we want to run through java, 
//	then everything need to wrap in 'public static void main'
//	We can use the '@Test' annotation and declare everything in a method
	
	@Test
	public void sumOfCourses() {
		JsonPath js = new JsonPath(payload.coursePrice());
		int totalAmt = js.getInt("dashboard.purchaseAmount");
		int courseCount = js.getInt("courses.size()");
		float courseAmt=0;
		for(int i=0;i<courseCount;i++) {
			float cPrice=js.getFloat("courses[" + i + "].price");
			float cCopies=js.getFloat("courses[" + i + "].copies");
			courseAmt=courseAmt+(cPrice*cCopies);
			
		}
		System.out.println(courseAmt);
		Assert.assertEquals(courseAmt, totalAmt);
	}
	
}
