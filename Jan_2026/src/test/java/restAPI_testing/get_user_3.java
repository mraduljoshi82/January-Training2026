package restAPI_testing;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class get_user_3 {
  @Test
  public void getuser3() {
	  
RestAssured.baseURI="https://api.restful-api.dev/objects/7";
	  
	  RestAssured.given()
			  .when()
			  .get("/objects")
			  .then()
			  .statusCode(404)
			  .log().all();
	  
  }
}


