package testCases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import SapModules.SaptcoModules;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

import pageObjects.BaseClass;
import pageObjects.Database;
import SapModules.BrowserContants;







public class saptcoSRPResultsFlow {
	static WebDriver driver;
	private int iTestCaseRow;
	boolean status;
	private Database PnrDetails;
	
	@Test
	public void test() throws Exception {
		
		
		if(BrowserContants.ENV.equals("PRD")) {
		RestAssured.baseURI = BrowserContants.PRD_API_URL;
		System.out.println(BrowserContants.PRD_API_URL);
		
	} else if (BrowserContants.ENV.equals("STG")) {
		RestAssured.baseURI = BrowserContants.STG_API_URL;
		System.out.println(BrowserContants.STG_API_URL);
	}
	
	
	//RestAssured.baseURI ="https://stagerehlatcommonapi.rehlat.com/v1/scraping";
	RequestSpecification request = RestAssured.given();
	request.header("Content-Type", "text/json");
	Response response = request.get("/GetF3Routes");
	System.out.println("Response body: " + response.body().asString());
	String s=response.body().asString();
	System.out.println(s);
	int statusCode = response.getStatusCode();
	System.out.println("The status code recieved: " + statusCode);
	
	Gson g = new Gson();
	Database[] mcArray = g.fromJson(s, Database[].class);
	List<Database> p = Arrays.asList(mcArray);
	for(Database data:p){
		try{
			PnrDetails=data;
			
         String[] depar=data.DepartureDate.split(" ");
			
			String Dep_date=depar[0];
			String Dep_Mon=depar[1];
			String Dep_year=depar[2];
			String monthYear=Dep_Mon+" "+Dep_year;
			System.out.println("DepartureDate:"+Dep_date);
			 System.out.println("DepartureMonth:"+Dep_Mon);
			 System.out.println("DepartureYear:"+Dep_year);
		   String DateOfDep=Dep_date+"/"+Dep_Mon+"/"+Dep_year;
		   System.out.println("DateOfDep:"+DateOfDep);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			System.setProperty("webdriver.chrome.driver", "D:\\jarfiles\\chromedriver.exe");
			driver = new ChromeDriver(options);
	
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			// Log.info("Implicit wait applied on the driver for 10 seconds");
			driver.manage().deleteAllCookies();
			driver.get("https://www.saptco.com.sa/Buses/Home.aspx?lang=en-US");
			new BaseClass(driver);
			
			SaptcoModules.tripType_SRP();
			SaptcoModules.enterFromAndTo_SRP(PnrDetails);
			SaptcoModules.enterDateAndNumberOfPassenger_SRP(DateOfDep);
			//SaptcoModules.Srp_Page_details_SRP();
			SaptcoModules.Srp_Results_From_SRP(driver,PnrDetails);
			//driver.quit();
			
		
			}
	
		
		catch(Exception e)
		{
			
		}
		
	}
	}
	
	}	
		

	
	

