package stepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.Api_resources;
import resources.TestDataBuild;
import resources.utilities;

public class stepdefinitions extends utilities{
	RequestSpecification req1;
	ResponseSpecification respon;
	 Response res;
	static String placeid;
	JsonPath js;
	TestDataBuild testdata=new TestDataBuild();
	static String placeidfromres;
	
	@Given("Add place payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {
		
//		Below is the response spec builder
		 respon=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		 req1=
		    given().spec(requestspecification())   //spec taken from RequestSpecification
//		           .log().all() 
		           .body(testdata.AddPlace(name,language,address)); ///passing the method by calling object inside testBuild
		
	}
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource,String method) {
		Api_resources resourceapi=Api_resources.valueOf(resource); // this will get value from API_resources class for resource which it receives in this method
		System.out.println(resourceapi.getResource());
		System.out.println("Resources which are being triggered:" + resourceapi.getResource());
		if(method.equalsIgnoreCase("POST"))
		 res=req1.when().post(resourceapi.getResource()); //respon is having all the response specification 
		else if(method.equalsIgnoreCase("GET"))
			res=req1.when().get(resourceapi.getResource());	
	}
	
	
	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {                 
		assertEquals(res.getStatusCode(),200);
	}
	
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String ExpectedValue) {	
		assertEquals(getJsonPath(res,keyValue),ExpectedValue); //passing the response to JsonPath class object
	}
	
	
	@Then("Verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String actualname, String resource) throws IOException
	{
		 placeidfromres=getJsonPath(res,"place_id").toString();
               //requestspec
		req1=given().spec(requestspecification()).queryParam("place_id", placeidfromres);
		user_calls_with_post_http_request(resource,"GET");
		   String resname=getJsonPath(res,"name");
		   assertEquals(resname,actualname);
	}

	
//	Delete place API from here.....
	
	@Given("DeletePlacePayload")
	public void delete_place_payload() throws IOException {
		
		req1=given().spec(requestspecification()).body(testdata.DeletePlace(placeidfromres));
	}


}
