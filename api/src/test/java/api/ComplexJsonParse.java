package api;

import input.payload;
import io.restassured.path.json.JsonPath;
//Code for mocking of the API's 
public class ComplexJsonParse {

	public static void main(String[] args) {
		JsonPath js = new JsonPath(payload.coursePrice());
		
		//1. Print no. of courses return by API
		
		int getNo = js.getInt("courses.size()");
		System.out.println(getNo);

		//2.Print Purchase Amount
		int purchaseAmt = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmt);
		
		//3. Print Title of the first course
		String firstcourse = js.getString("courses.title[0]");
		System.out.println(firstcourse);
		
		//4. Print All course titles and their respective Prices
		
	}
	
	

}
