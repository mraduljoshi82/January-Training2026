package restAPI_testing;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

import com.aventstack.extentreports.gherkin.model.Given;


public class GET_user {
  @Test
  public void getuser() {
RestAssured.baseURI="https://api.restful-api.dev";
	  
	  RestAssured.given()
			  .when()
			  .get("/objects")
			  .then()
			  .statusCode(200)
			  .log().all();
			  
	  
	  
	  
  }
}
