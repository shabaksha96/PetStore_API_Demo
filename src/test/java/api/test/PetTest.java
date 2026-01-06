package api.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import api.endpoints.PetEndpoints;
import io.restassured.response.Response;

public class PetTest {
	
	public Logger logs;
	String pet_id;
	
	@Test (priority=1)
	public void testAddPetDetails() throws IOException
	{
		logs = LogManager.getLogger(this.getClass());
		logs.info("*************** Pet Details Adding ***************");
		Response res = PetEndpoints.addPetDetails();
		res.then().log().all();
		AssertJUnit.assertEquals(res.statusCode(), 200);
		this.pet_id = res.then().extract().jsonPath().getString("id");
		System.out.println("Generated id: "+pet_id);
		logs.info("*************** Pet Details Added ***************");
	}
	
	@Test (priority=2)
	public void testUpdatePetDetails() throws IOException
	{
		logs = LogManager.getLogger(this.getClass());
		logs.info("*************** Pet Details Updating ***************");
		Response res = PetEndpoints.updatePetDetails();
		res.then().log().all();
		AssertJUnit.assertEquals(res.statusCode(), 200);
		logs.info("*************** Pet Details Updated ***************");
	}
	
	@Test (priority=3)
	public void testViewPet()
	{
		logs = LogManager.getLogger(this.getClass());
		logs.info("*************** Getting Pet Details ***************");
		Response res =PetEndpoints.getPetDetails(pet_id);
		res.then().log().all();
		AssertJUnit.assertEquals(res.statusCode(), 200);
		logs.info("*************** Listed Pet Details ***************");
	}

}
