package stepDefinition;

import java.io.IOException;

import io.cucumber.core.backend.StepDefinition;
import io.cucumber.java.Before;

public class Hooks {
	
	
//	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		
		stepdefinitions m=new stepdefinitions();
		
		if(stepdefinitions.placeid==null)
		{
		m.add_place_payload("Rakesh", "Karnataka", "India");
		m.user_calls_with_post_http_request("AddplaceAPI", "POST");
		m.verify_place_id_created_maps_to_using("Rakesh", "getPlaceAPI");
		}
	}

}
