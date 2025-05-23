package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class utilities {
	public static RequestSpecification req;
	public RequestSpecification requestspecification() throws IOException
	{
		if(req==null) {
		PrintStream log=new PrintStream(new FileOutputStream("Logging.txt"));
		 req=new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key","qaclick123")
				 .addFilter(RequestLoggingFilter.logRequestTo(log))
				 .addFilter(ResponseLoggingFilter.logResponseTo(log))
			     .setContentType(ContentType.JSON).build();
		}
		 return req;
	}
	
	public static String getGlobalValue(String key) throws IOException
	{
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream("D:\\Learning\\Rest API(Automation)-Rest Assured Java\\eclipse-workspacefor-RestAPI-RestAssured\\RestAssured_Framework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		String actkey=prop.getProperty(key);
		return actkey;
	
	}
	
	public String getJsonPath(Response response,String key)
	{
		String responsestring=response.asString();
		JsonPath js=new JsonPath(responsestring);
		return js.get(key).toString();
	}

}
