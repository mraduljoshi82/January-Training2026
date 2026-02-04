package restAPI_testing;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
 
import io.restassured.http.ContentType;
 
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
 
 
 
public class put_user {
  @Test
  public void postuser() {
	  baseURI = "https://api.restful-api.dev";
	  JSONObject data=new JSONObject();
	  data.put("year", 2019);
	  data.put("price", 2000.00 );
	  data.put("CPU model", "Intel Core i9");
	  data.put("Hard disk size","1 TB");
	  
	  JSONObject reqbody=new JSONObject();
	  reqbody.put("name", "Apple MacBook Pro 16");
	  reqbody.put("data",data);
	  given()
	  .contentType(ContentType.JSON)
	  .body(reqbody.toString())
	  .when()
	  .post("/objects")
	  .then()
	  .statusCode(200)
	  .log().all();
  }
  
  
  
}
