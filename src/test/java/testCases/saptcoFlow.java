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








public class saptcoFlow {
	static WebDriver driver;
	private int iTestCaseRow;
	boolean status;
	private Database PnrDetails;
	
	@Test
	public void test() throws Exception {
		
		
		
		
		
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			System.setProperty("webdriver.chrome.driver", "D:\\jarfiles\\chromedriver.exe");
			driver = new ChromeDriver(options);
	
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			// Log.info("Implicit wait applied on the driver for 10 seconds");
			driver.manage().deleteAllCookies();
			driver.get("https://www.saptco.com.sa/Buses/Home.aspx?lang=en-US");
			new BaseClass(driver);
			
			SaptcoModules.tripType("RoundTrip");
			SaptcoModules.enterFromAndTo("Jeddah","Abha");
			SaptcoModules.enterDateAndNumberOfPassenger("RoundTrip","25/09/2019","01/10/2019 - 01/10/2019","1","0","0");
			SaptcoModules.select_Booking_Details(driver,"RoundTrip");
			//SaptcoModules.enterPassengerDetails(driver,PnrDetails);
			
			//driver.quit();
			
		
			}
		
			
	
	}	
		

	
	

