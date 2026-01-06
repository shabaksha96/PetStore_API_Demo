package api.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payloads.Customer;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTest {
	
	@Test (priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void dataDrivenCreateUserTest(String UserID, String UserName, String FirstName, String LastName, String Email, String Password, String Phone)
	{
		Customer input = new Customer();
		input.setId(Integer.parseInt(UserID));
		input.setUsername(UserName);
		input.setFirstName(FirstName);
		input.setLastname(LastName);
		input.setEmail(Email);
		input.setPassword(Password);
		input.setPhoneNumber(Phone);
		
		Response response = UserEndpoints.createUser(input);
		response.then().log().all();
		AssertJUnit.assertEquals(response.statusCode(), 200);
		
	}
	
	@Test (priority=2, dataProvider="Username", dataProviderClass=DataProviders.class)
	public void dataDrivenDltUserTest(String Username)
	{
		Customer input1 = new Customer();
		input1.setUsername(Username);
		
		Response res =UserEndpoints.dltUser(Username);
		AssertJUnit.assertEquals(res.statusCode(), 200);
		res.then().log().body();
	}

}
