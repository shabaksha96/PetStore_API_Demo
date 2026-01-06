package api.endpoints;
import static io.restassured.RestAssured.*;

import api.payloads.Customer;
import io.restassured.response.Response;

public class UserEndpoints {

	public static Response createUser(Customer input)
	{
		Response response =given()
		.contentType("application/json").accept("application/json").body(input)
		
		.when()
		.post(Routes.postURL);
		
		return response;
	}
	
	public static Response readUser(String username)
	{
		Response response =given()
		.accept("application/json").pathParam("USERNAME", username)
		
		.when()
		.get(Routes.getURL);
		
		return response;
	}
	
	public static Response updateUser(String username, Customer payload)
	{
		Response response =given()
		.contentType("application/json").accept("application/json").pathParam("USERNAME", username).body(payload)
		
		.when()
		.put(Routes.updateURL);
		
		return response;
	}
	
	public static Response dltUser(String username)
	{
		Response response =given()
		.accept("application/json").pathParam("USERNAME", username)
		
		.when()
		.delete(Routes.dltURL);
		
		return response;
	}
	
	public static Response createWithArray(String string)
	{
		Response response = given()
		.contentType("application/json").accept("application/json").body(string)
		
		.when()
		.post(Routes.postURLwithArray);
		
		return response;
	}
	
	
	
	
	public static Response createWithList(String string)
	{
		Response response = given()
		.contentType("application/json").accept("application/json").body(string)
		
		.when()
		.post(Routes.createListURL);
		
		return response;
	}
	
	
	
	
	public static Response login(String txt)
	{
		Response response = given()
		.accept("application/json").pathParam("mypath", txt).queryParam("username", "shabeer").queryParam("password", "123123123")
				
		.when()
		.get(Routes.loginURL);
				
		return response;
	}

	public static Response logout(String n)
	{
		Response response = given()
		.accept("application/json").pathParam("path", n)
				
		.when()
		.get(Routes.logoutURL);
				
		return response;
	}
}
