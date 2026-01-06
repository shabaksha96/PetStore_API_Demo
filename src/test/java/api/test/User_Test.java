package api.test;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;
import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payloads.Customer;
import io.restassured.response.Response;
public class User_Test {
	Faker random;
	Customer payload;
	File f;
	
	public Logger logs;
	
	@BeforeClass
	public void setup()
	{
		random = new Faker();
		payload = new Customer();
		
		payload.setId(random.idNumber().hashCode());
		payload.setUsername(random.name().username());
		payload.setFirstName(random.name().firstName());
		payload.setLastname(random.name().lastName());
		payload.setEmail(random.internet().emailAddress());
		payload.setPassword(random.internet().password());
		payload.setPhoneNumber("987899870");
		payload.setUserStatus(0);
		
		
		//logs
		logs =LogManager.getLogger(this.getClass());
	}
	
	@Test (priority=0)
	public void testCreateUser()
	{
		logs.info("************* Creating user with valid details *************");
		Response response = UserEndpoints.createUser(payload);
		response.then().log().all();
		AssertJUnit.assertEquals(response.statusCode(), 200);
		
		logs.info("************* Created user with valid details *************");
		
	}
	
	@Test (priority=1)
	public void testViewUserWithUsername()
	{
		logs.info("************* user details *************");
		Response response =UserEndpoints.readUser(this.payload.getUsername());
		response.then().log().all();
		AssertJUnit.assertEquals(response.statusCode(), 200);
		System.out.println(response.body());
		
	}
	
	@Test (priority=2)
	public void testUpdateUserWithUsername()
	{
		logs.info("************* Updating user with valid details *************");
		payload.setEmail(random.internet().emailAddress());
		payload.setPassword(random.internet().password());
		payload.setPhoneNumber("1111111111");
		
		Response response = UserEndpoints.updateUser(this.payload.getUsername(), payload);
		response.then().log().body().statusCode(200);
		logs.info("************* Updated user with valid details *************");
	}
	
	@Test (priority=3)
	public void testViewUpdatedUserWithUsername()
	{
		logs.info("************* View updated user with valid details *************");
		Response response =UserEndpoints.readUser(this.payload.getUsername());
		response.then().log().body().statusCode(200);
	}
	
	@Test (priority =4)
	public void testdeleteUser()
	{
		logs.info("************* Deleting user *************");
		Response response = UserEndpoints.dltUser(this.payload.getUsername());
		response.then().statusCode(200);
		logs.info("************* Deleted user *************");
	}
	
	@Test (priority=5)
	public void testViewDltedUserInfo()
	{
		Response response =UserEndpoints.readUser(this.payload.getUsername());
		response.then().log().body().statusCode(404);
	}
	
	@Test (priority=6)
	public void testLogin()
	{
		Response res = UserEndpoints.login("login");
		res.then().log().body();
		AssertJUnit.assertEquals(res.statusCode(), 200);
	}
	
	@Test (priority = 7)
	public void testLogout () 
	{
		Response res = UserEndpoints.logout("logout");
		res.then().log().body();
		AssertJUnit.assertEquals(res.statusCode(), 200);
		System.out.println(res);
	}
	
	/*
	@Test (priority=9)
	public void CreateWithArray() throws IOException
	{
		f = new File("/Users/shabeerbaksha/SDET/PetStoreAutomation/JSONFile/ALFile.JSON");
		FileReader fr = new FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);
		
		Response response = UserEndpoints.createWithArray(data.toString());
		response.then().log().all();
		AssertJUnit.assertEquals(response.statusCode(), 200);
	}
	
	@Test (priority=8)
	public void testCreateWithList() throws IOException
	{
		
		f = new File("Users/shabeerbaksha/SDET/PetStoreAutomation/ALFile.JSON");
		FileReader fr = new FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);
		
		Response response = UserEndpoints.createWithList(data.toString());
		response.then().log().all();
		AssertJUnit.assertEquals(response.statusCode(), 200);
	}*/
	
}
