package restAPI_testing;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class Get_user_2 {
  @Test
  public void getuser2() {
RestAssured.baseURI="https://api.restful-api.dev/objects?id=3&id=5&id=10";
	  
	  RestAssured.given()
			  .when()
			  .get("/objects")
			  .then()
			  .statusCode(404)
			  .log().all();
			  
  }
}
