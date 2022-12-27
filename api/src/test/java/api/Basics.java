package api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import input.ReusuableMethods;
import input.payload;
public class Basics {

	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.addPlace())
		.when().log().all().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP"))
		.header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();

		System.out.println(response);
		
		JsonPath jp = ReusuableMethods.rawToJSON(response);
		
		String placeId=jp.getString("place_id");
		
		System.out.println("*****Place is********"+placeId);
		
		
		given().log().all().queryParam("key","qaclick123")
		.header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\"70 winter walk, USA\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));
		
		
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", placeId).when().log().all().get("maps/api/place/get/json")
		.then().assertThat().statusCode(200).extract().asString();
		
		JsonPath jp1=ReusuableMethods.rawToJSON(getPlaceResponse);
		String address = jp1.getString("address");
		System.out.println("address is***********"+address);
		
		Assert.assertEquals(address, "70 winter walk, USA");
	}  

	
}
