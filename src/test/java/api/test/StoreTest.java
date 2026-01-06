package api.test;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.github.javafaker.Faker;

import api.endpoints.StoreEndpoints;
import api.payloads.StoreData;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
public class StoreTest {
	
	
	Faker random = new Faker();
	StoreData input = new StoreData();
	public Logger log;
	String new_id;
	
	@Test(priority=1)
	public void placeOrder()
	{
		log = LogManager.getLogger(this.getClass());
		log.info("*************** Placing order ***************");
		input.setId(random.idNumber().hashCode());
		input.setPetId(random.number().hashCode());
		input.setQuantity(random.number().hashCode());
		input.setShipDate(random.date().birthday());
		input.setStatus("Active");
		input.setComplete("true");
		
		Response response = StoreEndpoints.placeOrder(input);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		this.new_id = response.jsonPath().getString("id");
		System.out.println(new_id);
		log.info("*************** Order placed ***************");
	}
	
	@Test(priority=2)
	public void inventoryDetailsSchema ()
	{
		log = LogManager.getLogger(this.getClass());
		log.info("*************** inventoryDetailsSchema Execution started ***************");
		Response res = StoreEndpoints.storeInventory();
		res.then().log().all();
		res.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SchemaStoreInv"));
		log.info("*************** inventoryDetailsSchema Execution ends ***************");
	}
	
	@Test(priority=3)
	public void purchaseDetails()
	{
		log = LogManager.getLogger(this.getClass());
		log.info("*************** purchaseDetails Execution started ***************");
		Response res = StoreEndpoints.findPurchase(6);
		res.then().log().body();
		log.info("*************** purchaseDetails Execution ends ***************");
	}
	
	@Test(priority=4)
	public void dltPurchase()
	{
		log = LogManager.getLogger(this.getClass());
		log.info("*************** dltPurchase Execution started ***************");
		Response res = StoreEndpoints.dltOrder(new_id);
		res.then().log().all();
		AssertJUnit.assertEquals(res.statusCode(), 200);
		AssertJUnit.assertEquals(res.then().extract().jsonPath().getString("message"), new_id);
		log.info("*************** dltPurchase Execution ends ***************");
	}

}
