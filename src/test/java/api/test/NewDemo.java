package api.test;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payloads.Customer;
import io.restassured.response.Response;

public class NewDemo {
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

}
