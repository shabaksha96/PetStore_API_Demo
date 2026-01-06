package api.endpoints;
import static io.restassured.RestAssured.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONObject;
import org.json.JSONTokener;

import io.restassured.response.Response;

public class PetEndpoints {
	
	public static Response addPetDetails () throws IOException 
	{
		File f = new File("/Users/shabeerbaksha/SDET/PetStoreAutomation/JSONFile/PetDetails.JSON");
		FileReader fr = new FileReader(f);
		JSONTokener ab = new JSONTokener(fr);
		JSONObject body = new JSONObject(ab);
		
		Response response = given()
		.contentType("application/json").accept("application/json").body(body)
		
		.when()
		.post(Routes.postPetURL);
		
		return response;
	}
	
	public static Response updatePetDetails () throws IOException
	{
		File f = new File("/Users/shabeerbaksha/SDET/PetStoreAutomation/JSONFile/UpdatedPetDetails.JSON");
		FileReader fr = new FileReader(f);
		JSONTokener ab = new JSONTokener(fr);
		JSONObject body = new JSONObject(ab);
		
		Response response = given()
				.contentType("application/json").accept("application/json").body(body)
				
				.when()
				.put(Routes.putPetURL);
				
				return response;
	}
	
	public static Response getPetDetails (String id)
	{
		Response response = given()
		.accept("application/json").pathParam("pet_id", id)
		
		.when()
		.get(Routes.getPetURL);
		
		return response;
	}

}
