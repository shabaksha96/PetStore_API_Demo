package api.endpoints;
import static io.restassured.RestAssured.*;

import api.payloads.StoreData;
import io.restassured.response.Response;
public class StoreEndpoints {
	
	public static Response storeInventory () 
	{
		Response response = given()
		.accept("application/json")
		
		.when()
		.get(Routes.getInvURL);
		
		return response;
	}
	
	public static Response placeOrder (StoreData data)
	{
		return given()
		.contentType("application/json").accept("application/json").body(data)
		
		.when()
		.post(Routes.postInvURL);
	}
	
	public static Response findPurchase(int id)
	{
		Response res = given()
		.accept("application/json").pathParam("order_id", id)
		
		.when()
		.get(Routes.getPurchaseURL);
		return res;
	}
	
	public static Response dltOrder(String created_id)
	{
		Response response = given().accept("application/json").pathParam("order_id", created_id)
		.when().delete(Routes.dltorderURL);
		return response;
	}

}
