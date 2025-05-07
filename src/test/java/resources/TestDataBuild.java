package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace AddPlace(String name, String language, String address)
	{
		AddPlace p=new AddPlace();  //created object for main class
		Location l=new Location();   //created object to access Location class
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		
		p.setAccuracy(50);     
		p.setName(name);
		p.setPhone_number("(+91) 983 893 3937");
		p.setAddress(address);
		
		List<String> mylist=new ArrayList<String>();  // Created object to store list of values  
		mylist.add("shoe park");
		mylist.add("shop");
		p.setTypes(mylist);
		
		p.setWebsite("http://google.com");
		p.setLanguage(language);
		return p;
	}
	
	public String DeletePlace(String placeId)
	{
		return "{\"place_id\":\""+placeId+"\"}";
	}

}
