package api.endpoints;

public class Routes {
	
	public static String baseURL = "https://petstore.swagger.io/v2";
	
	//USER URL's
	//-----------------
	
	//Create user
	public static String postURL = baseURL +"/user";                               //https://petstore.swagger.io/v2/user
	//View user with user name
	public static String getURL = baseURL +"/user/{USERNAME}";                     //https://petstore.swagger.io/v2/user/shabeer                               
	// update user with user name
	public static String updateURL = baseURL +"/user/{USERNAME}";                  //https://petstore.swagger.io/v2/user/shabeer
	// delete user
	public static String dltURL = baseURL +"/user/{USERNAME}";                     //https://petstore.swagger.io/v2/user/shabeer
	// Create with array
	public static String postURLwithArray = baseURL +"/user/createWithArray";      //https://petstore.swagger.io/v2/user/createWithArray
	// Login with user credentials
	public static String loginURL = baseURL +"/user/{mypath}";                        //https://petstore.swagger.io/v2/user/login?username=abc&password=123123123
	// Logout 
	public static String logoutURL = baseURL +"/user/{path}";                      //https://petstore.swagger.io/v2/user/logout
	// Create with list
	public static String createListURL = baseURL +"/user/createWithList";          //https://petstore.swagger.io/v2/user/createWithList
	
	
	
	//Store
	//---------
	
	//Returns pet inventory by status
	public static String getInvURL = baseURL +"/store/inventory";                  //https://petstore.swagger.io/v2/store/inventory
	//Place order 
	public static String postInvURL = baseURL +"/store/order";                     //https://petstore.swagger.io/v2/store/order
	//Find purchase by id
	public static String getPurchaseURL = baseURL +"/store/order/{order_id}";      //https://petstore.swagger.io/v2/store/order/9
	//Delete purchase by id
	public static String dltorderURL = baseURL +"/store/order/{order_id}";         //https://petstore.swagger.io/v2/store/order/9
	
	
	
	//PET
	//-----
	
	//Add pet details
	public static String postPetURL = baseURL+"/pet";
	//Update pet details
	public static String putPetURL = baseURL+"/pet";
	//Find pet by id
	public static String getPetURL = baseURL+"/pet/{pet_id}";
	//
	
	
	
}
