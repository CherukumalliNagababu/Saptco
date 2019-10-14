package SapModules;


import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import pageObjects.Database;
import pageObjects.PageUtils;

import pageObjects.saptcoPage;

public class SaptcoModules extends SapPassengerDetails{
	static WebDriver driver;
	public static void tripType_SRP() throws Exception
	{
		PageUtils.isElementVisibil(driver, saptcoPage.oneyWay());
		saptcoPage.oneyWay().click();
		/*PageUtils.isElementVisibil(driver, saptcoPage.roundTrip());
		saptcoPage.roundTrip().click();*/
	}
	public static void enterFromAndTo_SRP(Database PnrDetails) throws Exception
	{
		PageUtils.isElementVisibil(driver, saptcoPage.From());
		saptcoPage.From().sendKeys(PnrDetails.From);
		saptcoPage.From().sendKeys(Keys.ARROW_DOWN);
		saptcoPage.From().sendKeys(Keys.ENTER);
		PageUtils.isElementVisibil(driver, saptcoPage.To());
		saptcoPage.To().sendKeys(PnrDetails.To);
		Thread.sleep(1000);
		saptcoPage.To().sendKeys(Keys.ARROW_DOWN);
		saptcoPage.To().sendKeys(Keys.ENTER);
		

	}
	public static void enterDateAndNumberOfPassenger_SRP(String Date) throws Exception
	{
		//PageUtils.isElementVisibil(driver, saptcoPage.enter_Date());
		PageUtils.waitForFixedTime(BrowserContants.WAIT_VERY_SMALL);
		saptcoPage.enter_Date().click();
		saptcoPage.enter_Date().clear();
		Thread.sleep(1000);
		saptcoPage.enter_Date().sendKeys("23/10/2019");
		//20/09/2019 - 23/09/2019
		//PageUtils.sendKeysAfterClearingElement(driver, saptcoPage.enter_Date(), );
		saptcoPage.btn_passenger().click();
		
		int Adult = Integer.parseInt("1");
		System.out.println("Adult:"+Adult);
		for (int i = 0; i < Adult - 1; i++) {
			saptcoPage.adult_increase().click();
		}
		int Child = Integer.parseInt("0");
		System.out.println("Child:"+Child);
		for (int i = 0; i < Child; i++) {
			saptcoPage.child_increase().click();

		}
		int infant = Integer.parseInt("0");
		System.out.println("infant:"+infant);

		for (int i = 0; i < infant; i++) {
			saptcoPage.infant_increase().click();
		}
		
         PageUtils.waitForFixedTime(BrowserContants.WAIT_VERY_SMALL_ENGINE);
        saptcoPage.btn_Home_Search().click();
         PageUtils.isElementLocated(driver, By.xpath("//span[contains(text(),'Trips of your search')]"));
         
		}
		
	
	public static void Srp_Results_From_SRP(WebDriver driver,Database PnrDetails) throws Exception {
		
		PageUtils.waitForFixedTime(BrowserContants.WAIT_MEDIUM);
		String StartTime;
		String From=PnrDetails.From;
		String TO=PnrDetails.To;
		String DepDate=PnrDetails.DepartureDate;
		String hours;
		String onePlus = null;
		String EndTime;
		String regularPriceText;
		String regularCost;
		String OfferPriceText;
		String OfferPriceCost = null;
	   List<SapBusDetails> finalList =  new ArrayList<SapBusDetails>();
		try {
			
	
			
			List<WebElement> Tab2 = driver.findElements(By.xpath("//div[@id='tabs-0']//div[@class='tripContainer']"));
			System.out.println("Tab2");
			
			
			

			for(WebElement e:Tab2)
			{
				SapBusDetails currentBusRegular = new SapBusDetails();
				SapBusDetails currentBusOffers = new SapBusDetails();
				String ele=e.getText();
			
				
				String str1=ele.replaceAll("[\r\n]+", ",");
				System.out.println(str1);
				
				
				String h=str1.split(",")[0];
				String f=str1.split(",")[1];
				String d=str1.split(",")[2];
				String d1=str1.split(",")[3];
				//System.out.println(h+" "+f+"  "+d+" "+d1);
				System.out.println("--------------------------");
				
				String StartTimeAndFrom=str1.split(",")[0];
				 StartTime=StartTimeAndFrom.split(" ")[0];
				 From=StartTimeAndFrom.split(" ")[1];
				 hours=str1.split(",")[1];
				System.out.println("START TIME:"+StartTime);
				System.out.println("FROM CITY:"+From);
				System.out.println("hours:"+hours);
				
				String onePlusTest=str1.split(",")[2];
				if("1+".equals(onePlusTest))
				{
					 onePlus=str1.split(",")[2];
					System.out.println("onePlus:"+onePlus);
					String EndTimeAndTo=str1.split(",")[3];
					 EndTime=EndTimeAndTo.split(" ")[1];
					 TO=EndTimeAndTo.split(" ")[2];
					System.out.println("END TIME:"+EndTime);
					System.out.println("TO CITY:"+TO);
					 regularPriceText=str1.split(",")[4];
					System.out.println("RegularPriceText:"+regularPriceText);
					 regularCost=str1.split(",")[5];
					System.out.println("RegularPriceCOST:"+regularCost);
					
					try{
					 OfferPriceText=str1.split(",")[6];
					System.out.println("OfferPriceText:"+OfferPriceText);
					 OfferPriceCost=str1.split(",")[7];
					System.out.println("OfferPriceCOST:"+OfferPriceCost);
					}
					catch(Exception Of)
					{
						
					}
					
					
				}
				else
				{
					String EndTimeAndTo=str1.split(",")[2];
					 EndTime=EndTimeAndTo.split(" ")[0];
					 TO=EndTimeAndTo.split(" ")[1];
					System.out.println("TO TIME:"+EndTime);
					System.out.println("TO CITY:"+TO);
					 regularPriceText=str1.split(",")[3];
					System.out.println("RegularPriceText:"+regularPriceText);
					 regularCost=str1.split(",")[4];
					System.out.println("RegularPriceCOST:"+regularCost);
					
					try{
					 OfferPriceText=str1.split(",")[5];
					System.out.println("OfferPriceText:"+OfferPriceText);
					 OfferPriceCost=str1.split(",")[6];
					System.out.println("OfferPriceCOST:"+OfferPriceCost);
					}
					catch(Exception Of)
					{
						
					}
					
				}
					
					
					currentBusOffers.FareType=currentBusRegular.FareType="Regular";
					currentBusOffers.Class=currentBusRegular.Class="Economy";
					currentBusOffers.StartLoc = currentBusRegular.StartLoc =From;
					currentBusOffers.EndLoc=currentBusRegular.EndLoc=TO;
					//currentBusOffers.StartDt=currentBusRegular.StartDt=Depdate;
					currentBusOffers.ADTBG=currentBusRegular.ADTBG="";
					currentBusOffers.CHDBG=currentBusRegular.CHDBG="";
					currentBusOffers.INFBG=currentBusRegular.INFBG="";
					currentBusOffers.DayChg=currentBusRegular.DayChg = onePlus;
					//currentBusOffers.Fltnum=currentBusRegular.Fltnum=FlightNum;
					currentBusOffers.JrnyTm=currentBusRegular.JrnyTm=hours;
					currentBusOffers.StartTm=currentBusRegular.StartTm=StartTime;
					currentBusOffers.EndTm=currentBusRegular.EndTm=EndTime;
					currentBusOffers.NoOfSeats=currentBusRegular.NoOfSeats="99";
					//currentBusOffers.StartTerminal=currentBusRegular.StartTerminal=StartTerminal;
					//currentBusOffers.EndTerminal=currentBusRegular.EndTerminal=EndTerminal;
					currentBusOffers.AdultBasePrice=currentBusRegular.AdultBasePrice=regularCost;
					currentBusOffers.AdultTaxes=currentBusRegular.AdultTaxes ="";
					//currentBusOffers.ChildBasePrice=currentBusRegular.ChildBasePrice=regularCost/2;
					currentBusOffers.ChildTaxes=currentBusRegular.ChildTaxes="";
					currentBusOffers.InfantBasePrice=currentBusRegular.InfantBasePrice ="";
					currentBusOffers.InfantTaxes=currentBusRegular.InfantTaxes="";
					currentBusOffers.TotalApiFare=currentBusRegular.TotalApiFare="";
					
					
					
					finalList.add(currentBusRegular);
					
					currentBusOffers.FareType="Offer";
					currentBusOffers.ADTBG="";
					currentBusOffers.CHDBG="";
					currentBusOffers.AdultBasePrice=OfferPriceCost;
					currentBusOffers.AdultTaxes="";
					//currentBusOffers.ChildBasePrice=flyPlusFare;
					currentBusOffers.ChildTaxes="";
					currentBusOffers.InfantBasePrice="";
					
					finalList.add(currentBusOffers);
					
				}
				
			
			
			} 
			catch (Exception e) {
			throw (e);
		}
		
		ApiMethods.sendResults(From, TO,DepDate, finalList);
		
	}
	



public static void tripType(String tripType) throws Exception
{
	if("OneWay".equals(tripType))
	{
		PageUtils.isElementVisibil(driver, saptcoPage.oneyWay());
		saptcoPage.oneyWay().click();
	}
	else if("RoundTrip".equals(tripType)){
	PageUtils.isElementVisibil(driver, saptcoPage.roundTrip());
	saptcoPage.roundTrip().click();
	}
}
public static void enterFromAndTo(String FROM,String TO) throws Exception
{
	PageUtils.isElementVisibil(driver, saptcoPage.From());
	saptcoPage.From().sendKeys(FROM);
	saptcoPage.From().sendKeys(Keys.ARROW_DOWN);
	saptcoPage.From().sendKeys(Keys.ENTER);
	PageUtils.isElementVisibil(driver, saptcoPage.To());
	saptcoPage.To().sendKeys(TO);
	Thread.sleep(1000);
	saptcoPage.To().sendKeys(Keys.ARROW_DOWN);
	saptcoPage.To().sendKeys(Keys.ENTER);
	

}
public static void enterDateAndNumberOfPassenger(String tripType,String depDate,String retDate,String A,String C,String I) throws Exception
{
	//PageUtils.isElementVisibil(driver, saptcoPage.enter_Date());
	PageUtils.waitForFixedTime(BrowserContants.WAIT_VERY_SMALL);
	saptcoPage.enter_Date().click();
	saptcoPage.enter_Date().clear();
	Thread.sleep(1000);
	if("OneWay".equals(tripType))
	{
		saptcoPage.enter_Date().sendKeys(depDate);
	}
	if("RoundTrip".equals(tripType))
	{
		saptcoPage.enter_Date().sendKeys(retDate);
	}
	//20/09/2019 - 23/09/2019
	//PageUtils.sendKeysAfterClearingElement(driver, saptcoPage.enter_Date(), );
	saptcoPage.btn_passenger().click();
	
	int Adult = Integer.parseInt(A);
	System.out.println("Adult:"+Adult);
	for (int i = 0; i < Adult - 1; i++) {
		saptcoPage.adult_increase().click();
	}
	int Child = Integer.parseInt(C);
	System.out.println("Child:"+Child);
	for (int i = 0; i < Child; i++) {
		saptcoPage.child_increase().click();

	}
	int infant = Integer.parseInt(I);
	System.out.println("infant:"+infant);

	for (int i = 0; i < infant; i++) {
		saptcoPage.infant_increase().click();
	}
	
     PageUtils.waitForFixedTime(BrowserContants.WAIT_VERY_SMALL_ENGINE);
    saptcoPage.btn_Home_Search().click();
     PageUtils.isElementLocated(driver, By.xpath("//span[contains(text(),'Trips of your search')]"));
     
	}
	
public static void select_Booking_Details(WebDriver driver,String tripType) throws Exception
{
	if("OneWay".equals(tripType))
	{
		 Date mDate = new Date();
		 DateFormat date = new SimpleDateFormat("dd-MMMM-yyyy");
		 String Date=date.format(mDate);
		 System.out.println(Date);
			if (Date.equals("01-October-2019")) {
				Srp_Page_details_currentdate(driver);
			} else {
				Srp_Page_details(driver);
			}
		
	}
	if("RoundTrip".equals(tripType))
	{
		Date mDate = new Date();
		 DateFormat date = new SimpleDateFormat("dd-MMMM-yyyy");
		 String Date=date.format(mDate);
		 System.out.println(Date);
			if (Date.equals("01-October-2019")) {
				Srp_Page_details_currentdate(driver);
			} else {
				Srp_Page_details(driver);
			}
		
		Srp_Page_details_Round(driver);
	}
	PageUtils.waitForFixedTime(BrowserContants.WAIT_SMALL);
	
}

public static void Srp_Page_details(WebDriver driver) throws Exception
{
	PageUtils.waitForFixedTime(BrowserContants.WAIT_MEDIUM);
	String StartTime;
	String From;
	String TO;
	String hours;
	String onePlus = null;
	String EndTime;
	String regularPriceText;
	String regularCost;
	String OfferPriceText;
	String OfferPriceCost = null;
  
	try {
		

		
		List<WebElement> Tab2 = driver.findElements(By.xpath("//div[@id='tabs-0']//div[@class='tripContainer']"));
		System.out.println("Tab2");
		
		
		int i=0;

		for(WebElement e:Tab2)
		{
			String ele=e.getText();
			
			i=i+1;
			String str1=ele.replaceAll("[\r\n]+", ",");
			System.out.println(str1);
			System.out.println("--------------------------");
			
			String StartTimeAndFrom=str1.split(",")[0];
			 StartTime=StartTimeAndFrom.split(" ")[0];
			 From=StartTimeAndFrom.split(" ")[1];
			 hours=str1.split(",")[1];
			System.out.println("START TIME:"+StartTime);
			System.out.println("FROM CITY:"+From);
			System.out.println("hours:"+hours);
			
			String onePlusTest=str1.split(",")[2];
			if("1+".equals(onePlusTest))
			{
				 onePlus=str1.split(",")[2];
				System.out.println("onePlus:"+onePlus);
				String EndTimeAndTo=str1.split(",")[3];
				 EndTime=EndTimeAndTo.split(" ")[1];
				 TO=EndTimeAndTo.split(" ")[2];
				System.out.println("END TIME:"+EndTime);
				System.out.println("TO CITY:"+TO);
				 regularPriceText=str1.split(",")[4];
				System.out.println("RegularPriceText:"+regularPriceText);
				 regularCost=str1.split(",")[5];
				System.out.println("RegularPriceCOST:"+regularCost);
				
				try{
				 OfferPriceText=str1.split(",")[6];
				System.out.println("OfferPriceText:"+OfferPriceText);
				 OfferPriceCost=str1.split(",")[7];
				System.out.println("OfferPriceCOST:"+OfferPriceCost);
				}
				catch(Exception Of)
				{
					
				}
				
				
			}
			else
			{
				String EndTimeAndTo=str1.split(",")[2];
				 EndTime=EndTimeAndTo.split(" ")[0];
				 TO=EndTimeAndTo.split(" ")[1];
				System.out.println("TO TIME:"+EndTime);
				System.out.println("TO CITY:"+TO);
				 regularPriceText=str1.split(",")[3];
				System.out.println("RegularPriceText:"+regularPriceText);
				 regularCost=str1.split(",")[4];
				System.out.println("RegularPriceCOST:"+regularCost);
				
				try{
				 OfferPriceText=str1.split(",")[5];
				System.out.println("OfferPriceText:"+OfferPriceText);
				 OfferPriceCost=str1.split(",")[6];
				System.out.println("OfferPriceCOST:"+OfferPriceCost);
				}
				catch(Exception Of)
				{
					
				}
				
			}
			if(StartTime.equals("18:30"))
			{
				
				String R=regularCost.replace(" ", "");
				WebElement price=driver.findElement(By.xpath("//div[2]/div/div/div[3]/div[2]/div["+i+"]/div/div[5]//label[contains(text(),'"+R+"')]"));
				price.click();
				Thread.sleep(2000);
				PageUtils.scrollDown(driver);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div["+i+"]/div[2]/div[3]/input[@type='button']")).click();
				break;
			}
			
			
				
			}
			
		
		
		} 
		catch (Exception e) {
		throw (e);
	}
	
}
public static void Srp_Page_details_currentdate(WebDriver driver) throws Exception
{
	PageUtils.waitForFixedTime(BrowserContants.WAIT_MEDIUM);
	String StartTime;
	String From;
	String TO;
	String hours;
	String onePlus = null;
	String EndTime;
	String regularPriceText;
	String regularCost;
	String OfferPriceText;
	String OfferPriceCost = null;
  
	try {
		

		
		List<WebElement> Tab2 = driver.findElements(By.xpath("//div[@id='tabs--1']//div[@class='tripContainer']"));
		System.out.println("Tab2");
		
		
		int i=0;

		for(WebElement e:Tab2)
		{
			String ele=e.getText();
			
			i=i+1;
			String str1=ele.replaceAll("[\r\n]+", ",");
			System.out.println(str1);
			System.out.println("--------------------------");
			
			String StartTimeAndFrom=str1.split(",")[0];
			 StartTime=StartTimeAndFrom.split(" ")[0];
			 From=StartTimeAndFrom.split(" ")[1];
			 hours=str1.split(",")[1];
			System.out.println("START TIME:"+StartTime);
			System.out.println("FROM CITY:"+From);
			System.out.println("hours:"+hours);
			
			String onePlusTest=str1.split(",")[2];
			if("1+".equals(onePlusTest))
			{
				 onePlus=str1.split(",")[2];
				System.out.println("onePlus:"+onePlus);
				String EndTimeAndTo=str1.split(",")[3];
				 EndTime=EndTimeAndTo.split(" ")[1];
				 TO=EndTimeAndTo.split(" ")[2];
				System.out.println("END TIME:"+EndTime);
				System.out.println("TO CITY:"+TO);
				 regularPriceText=str1.split(",")[4];
				System.out.println("RegularPriceText:"+regularPriceText);
				 regularCost=str1.split(",")[5];
				System.out.println("RegularPriceCOST:"+regularCost);
				
				try{
				 OfferPriceText=str1.split(",")[6];
				System.out.println("OfferPriceText:"+OfferPriceText);
				 OfferPriceCost=str1.split(",")[7];
				System.out.println("OfferPriceCOST:"+OfferPriceCost);
				}
				catch(Exception Of)
				{
					
				}
				
				
			}
			else
			{
				String EndTimeAndTo=str1.split(",")[2];
				 EndTime=EndTimeAndTo.split(" ")[0];
				 TO=EndTimeAndTo.split(" ")[1];
				System.out.println("TO TIME:"+EndTime);
				System.out.println("TO CITY:"+TO);
				 regularPriceText=str1.split(",")[3];
				System.out.println("RegularPriceText:"+regularPriceText);
				 regularCost=str1.split(",")[4];
				System.out.println("RegularPriceCOST:"+regularCost);
				
				try{
				 OfferPriceText=str1.split(",")[5];
				System.out.println("OfferPriceText:"+OfferPriceText);
				 OfferPriceCost=str1.split(",")[6];
				System.out.println("OfferPriceCOST:"+OfferPriceCost);
				}
				catch(Exception Of)
				{
					
				}
				
			}
			if(StartTime.equals("18:30"))
			{
				
				String R=regularCost.replace(" ", "");
				WebElement price=driver.findElement(By.xpath("//div[2]/div/div/div[3]/div[3]/div["+i+"]/div/div[5]//label[contains(text(),'"+R+"')]"));
				price.click();                                
				Thread.sleep(2000);
				PageUtils.scrollDown(driver);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div["+i+"]/div[2]/div[3]/input[@type='button']")).click();
				break;
			}
			
			
				
			}
			
		
		
		} 
		catch (Exception e) {
		throw (e);
	}
	
}



public static void Srp_Page_details_Round(WebDriver driver) throws Exception
{
	PageUtils.waitForFixedTime(BrowserContants.WAIT_MEDIUM);
	String StartTime;
	String From;
	String TO;
	String hours;
	String onePlus = null;
	String EndTime;
	String regularPriceText;
	String regularCost;
	String OfferPriceText;
	String OfferPriceCost = null;
  
	try {
		

		
		List<WebElement> Tab2 = driver.findElements(By.xpath("//div[@id='tabs-0']//div[@class='tripContainer']"));
		System.out.println("Tab2");
		
		
		int i=0;

		for(WebElement e:Tab2)
		{
			String ele=e.getText();
			
			i=i+1;
			String str1=ele.replaceAll("[\r\n]+", ",");
			System.out.println(str1);
			System.out.println("--------------------------");
			
			String StartTimeAndFrom=str1.split(",")[0];
			 StartTime=StartTimeAndFrom.split(" ")[0];
			 From=StartTimeAndFrom.split(" ")[1];
			 hours=str1.split(",")[1];
			System.out.println("START TIME:"+StartTime);
			System.out.println("FROM CITY:"+From);
			System.out.println("hours:"+hours);
			
			String onePlusTest=str1.split(",")[2];
			if("1+".equals(onePlusTest))
			{
				 onePlus=str1.split(",")[2];
				System.out.println("onePlus:"+onePlus);
				String EndTimeAndTo=str1.split(",")[3];
				 EndTime=EndTimeAndTo.split(" ")[1];
				 TO=EndTimeAndTo.split(" ")[2];
				System.out.println("END TIME:"+EndTime);
				System.out.println("TO CITY:"+TO);
				 regularPriceText=str1.split(",")[4];
				System.out.println("RegularPriceText:"+regularPriceText);
				 regularCost=str1.split(",")[5];
				System.out.println("RegularPriceCOST:"+regularCost);
				
				try{
				 OfferPriceText=str1.split(",")[6];
				System.out.println("OfferPriceText:"+OfferPriceText);
				 OfferPriceCost=str1.split(",")[7];
				System.out.println("OfferPriceCOST:"+OfferPriceCost);
				}
				catch(Exception Of)
				{
					
				}
				
				
			}
			else
			{
				String EndTimeAndTo=str1.split(",")[2];
				 EndTime=EndTimeAndTo.split(" ")[0];
				 TO=EndTimeAndTo.split(" ")[1];
				System.out.println("TO TIME:"+EndTime);
				System.out.println("TO CITY:"+TO);
				 regularPriceText=str1.split(",")[3];
				System.out.println("RegularPriceText:"+regularPriceText);
				 regularCost=str1.split(",")[4];
				System.out.println("RegularPriceCOST:"+regularCost);
				
				try{
				 OfferPriceText=str1.split(",")[5];
				System.out.println("OfferPriceText:"+OfferPriceText);
				 OfferPriceCost=str1.split(",")[6];
				System.out.println("OfferPriceCOST:"+OfferPriceCost);
				}
				catch(Exception Of)
				{
					
				}
				
			}
			if(StartTime.equals("17:23"))
			{
				
				String R=regularCost.replace(" ", "");
				WebElement price=driver.findElement(By.xpath("//div[2]/div/div/div[3]/div[2]/div["+i+"]/div/div[5]//label[contains(text(),'"+R+"')]"));
				price.click();
				Thread.sleep(2000);
				PageUtils.scrollDown(driver);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div["+i+"]/div[2]/div[3]/input[@type='button']")).click();
				break;
			}
			
			
				
			}
			
		
		
		} 
		catch (Exception e) {
		throw (e);
	}
	
}


public static void enterPassengerDetails(WebDriver driver,Database PnrDetails) throws Exception
{

	try{
		SapPassengerDetails.paxAPI(PnrDetails);
	
	String numofAdults =PnrDetails.Adults; 
	String numofChilds = PnrDetails.Childs;
	String numofInfants = PnrDetails.Infants;
	
	
	if (numofAdults.equals("1")) {
		PageUtils.getScreenShot(PnrDetails.PnrId, driver);
		SapPassengerDetails.adult1(PnrDetails);
		
		
	
	} else if (numofAdults.equals("2")) {
		System.out.println("Adults 2-----------------");
		SapPassengerDetails.adult1(PnrDetails);
		SapPassengerDetails.adult2();
	}
	else if (numofAdults.equals("3")) {
		SapPassengerDetails.adult1(PnrDetails);
		SapPassengerDetails.adult2();
		SapPassengerDetails.adult3();
	}
	else if (numofAdults.equals("4")) {
		SapPassengerDetails.adult1(PnrDetails);
		SapPassengerDetails.adult2();
		SapPassengerDetails.adult3();
		SapPassengerDetails.adult4();
	}
	else if (numofAdults.equals("5")) {
		SapPassengerDetails.adult1(PnrDetails);
		SapPassengerDetails.adult2();
		SapPassengerDetails.adult3();
		SapPassengerDetails.adult4();
		SapPassengerDetails.adult5();
	
	}
	else if (numofAdults.equals("6")) {
		SapPassengerDetails.adult1(PnrDetails);
		SapPassengerDetails.adult2();
		SapPassengerDetails.adult3();
		SapPassengerDetails.adult4();
		SapPassengerDetails.adult5();
		SapPassengerDetails.adult6();
	}
	else if (numofAdults.equals("7")) {
		SapPassengerDetails.adult1(PnrDetails);
		SapPassengerDetails.adult2();
		SapPassengerDetails.adult3();
		SapPassengerDetails.adult4();
		SapPassengerDetails.adult5();
		SapPassengerDetails.adult6();
		SapPassengerDetails.adult7();
	}
	else if (numofAdults.equals("8")) {
		SapPassengerDetails.adult1(PnrDetails);
		SapPassengerDetails.adult2();
		SapPassengerDetails.adult3();
		SapPassengerDetails.adult4();
		SapPassengerDetails.adult5();
		SapPassengerDetails.adult6();
		SapPassengerDetails.adult7();
		SapPassengerDetails.adult8();
	}
	else if (numofAdults.equals("9")) {
		SapPassengerDetails.adult1(PnrDetails);
		SapPassengerDetails.adult2();
		SapPassengerDetails.adult3();
		SapPassengerDetails.adult4();
		SapPassengerDetails.adult5();
		SapPassengerDetails.adult6();
		SapPassengerDetails.adult7();
		SapPassengerDetails.adult8();
		SapPassengerDetails.adult9();
	}

	if(numofChilds.equals("1"))
	{
		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child1(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child2(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child3(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child4(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("5".equals(numofAdults)) {
			SapPassengerDetails.child5(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("6".equals(numofAdults)) {
			SapPassengerDetails.child6(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("7".equals(numofAdults)) {
			SapPassengerDetails.child7(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("8".equals(numofAdults)) {
			SapPassengerDetails.child8(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		
	}
	else if(numofChilds.equals("2"))
	{
		
		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child1(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child2(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child3(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child4(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("5".equals(numofAdults)) {
			SapPassengerDetails.child5(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("6".equals(numofAdults)) {
			SapPassengerDetails.child6(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("7".equals(numofAdults)) {
			SapPassengerDetails.child7(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("8".equals(numofAdults)) {
			SapPassengerDetails.child8(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		System.out.println("----");
		
		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child2(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child3(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child4(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child5(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("5".equals(numofAdults)) {
			SapPassengerDetails.child6(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("6".equals(numofAdults)) {
			SapPassengerDetails.child7(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("7".equals(numofAdults)) {
			SapPassengerDetails.child8(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		
	}
	else if(numofChilds.equals("3"))
	{
		
		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child1(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child2(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child3(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child4(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("5".equals(numofAdults)) {
			SapPassengerDetails.child5(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("6".equals(numofAdults)) {
			SapPassengerDetails.child6(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("7".equals(numofAdults)) {
			SapPassengerDetails.child7(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("8".equals(numofAdults)) {
			SapPassengerDetails.child8(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		System.out.println("----");
		
		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child2(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child3(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child4(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child5(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("5".equals(numofAdults)) {
			SapPassengerDetails.child6(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("6".equals(numofAdults)) {
			SapPassengerDetails.child7(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("7".equals(numofAdults)) {
			SapPassengerDetails.child8(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
         System.out.println("----");
		
		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child3(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child4(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child5(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child6(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		else if ("5".equals(numofAdults)) {
			SapPassengerDetails.child7(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		else if ("6".equals(numofAdults)) {
			SapPassengerDetails.child8(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		
	
		
	}
	else if(numofChilds.equals("4"))
	{
		

		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child1(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child2(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child3(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child4(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("5".equals(numofAdults)) {
			SapPassengerDetails.child5(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("6".equals(numofAdults)) {
			SapPassengerDetails.child6(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("7".equals(numofAdults)) {
			SapPassengerDetails.child7(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("8".equals(numofAdults)) {
			SapPassengerDetails.child8(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		System.out.println("----");
		
		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child2(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child3(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child4(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child5(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("5".equals(numofAdults)) {
			SapPassengerDetails.child6(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("6".equals(numofAdults)) {
			SapPassengerDetails.child7(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("7".equals(numofAdults)) {
			SapPassengerDetails.child8(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
         System.out.println("----");
		
		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child3(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child4(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child5(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child6(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		else if ("5".equals(numofAdults)) {
			SapPassengerDetails.child7(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		else if ("6".equals(numofAdults)) {
			SapPassengerDetails.child8(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		
		System.out.println("----");

		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child4(child_4_firstname,child_4_middlename,child_4_lastname,child4_dof_d,child4_dof_m,child4_dof_y,child4_doc_Type,child_4_IC,child4_doc_Number,child4_pass_d,child4_pass_m,child4_pass_y,child_4_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child5(child_4_firstname,child_4_middlename,child_4_lastname,child4_dof_d,child4_dof_m,child4_dof_y,child4_doc_Type,child_4_IC,child4_doc_Number,child4_pass_d,child4_pass_m,child4_pass_y,child_4_nation);

		} else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child6(child_4_firstname,child_4_middlename,child_4_lastname,child4_dof_d,child4_dof_m,child4_dof_y,child4_doc_Type,child_4_IC,child4_doc_Number,child4_pass_d,child4_pass_m,child4_pass_y,child_4_nation);

		} else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child7(child_4_firstname,child_4_middlename,child_4_lastname,child4_dof_d,child4_dof_m,child4_dof_y,child4_doc_Type,child_4_IC,child4_doc_Number,child4_pass_d,child4_pass_m,child4_pass_y,child_4_nation);

		} else if ("5".equals(numofAdults)) {
			SapPassengerDetails.child8(child_4_firstname,child_4_middlename,child_4_lastname,child4_dof_d,child4_dof_m,child4_dof_y,child4_doc_Type,child_4_IC,child4_doc_Number,child4_pass_d,child4_pass_m,child4_pass_y,child_4_nation);

		}
	
	}
	else if(numofChilds.equals("5"))
	{
		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child1(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child2(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child3(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child4(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("5".equals(numofAdults)) {
			SapPassengerDetails.child5(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("6".equals(numofAdults)) {
			SapPassengerDetails.child6(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("7".equals(numofAdults)) {
			SapPassengerDetails.child7(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("8".equals(numofAdults)) {
			SapPassengerDetails.child8(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		System.out.println("----");
		
		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child2(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child3(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child4(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child5(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("5".equals(numofAdults)) {
			SapPassengerDetails.child6(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("6".equals(numofAdults)) {
			SapPassengerDetails.child7(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("7".equals(numofAdults)) {
			SapPassengerDetails.child8(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
         System.out.println("----");
		
		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child3(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child4(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child5(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child6(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		else if ("5".equals(numofAdults)) {
			SapPassengerDetails.child7(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		else if ("6".equals(numofAdults)) {
			SapPassengerDetails.child8(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		
		System.out.println("----");

		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child4(child_4_firstname,child_4_middlename,child_4_lastname,child4_dof_d,child4_dof_m,child4_dof_y,child4_doc_Type,child_4_IC,child4_doc_Number,child4_pass_d,child4_pass_m,child4_pass_y,child_4_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child5(child_4_firstname,child_4_middlename,child_4_lastname,child4_dof_d,child4_dof_m,child4_dof_y,child4_doc_Type,child_4_IC,child4_doc_Number,child4_pass_d,child4_pass_m,child4_pass_y,child_4_nation);

		} else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child6(child_4_firstname,child_4_middlename,child_4_lastname,child4_dof_d,child4_dof_m,child4_dof_y,child4_doc_Type,child_4_IC,child4_doc_Number,child4_pass_d,child4_pass_m,child4_pass_y,child_4_nation);

		} else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child7(child_4_firstname,child_4_middlename,child_4_lastname,child4_dof_d,child4_dof_m,child4_dof_y,child4_doc_Type,child_4_IC,child4_doc_Number,child4_pass_d,child4_pass_m,child4_pass_y,child_4_nation);

		} else if ("5".equals(numofAdults)) {
			SapPassengerDetails.child8(child_4_firstname,child_4_middlename,child_4_lastname,child4_dof_d,child4_dof_m,child4_dof_y,child4_doc_Type,child_4_IC,child4_doc_Number,child4_pass_d,child4_pass_m,child4_pass_y,child_4_nation);

		}
		System.out.println("----");

		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child5(child_5_firstname,child_5_middlename,child_5_lastname,child5_dof_d,child5_dof_m,child5_dof_y,child5_doc_Type,child_5_IC,child5_doc_Number,child5_pass_d,child5_pass_m,child5_pass_y,child_5_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child6(child_5_firstname,child_5_middlename,child_5_lastname,child5_dof_d,child5_dof_m,child5_dof_y,child5_doc_Type,child_5_IC,child5_doc_Number,child5_pass_d,child5_pass_m,child5_pass_y,child_5_nation);

		} else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child7(child_5_firstname,child_5_middlename,child_5_lastname,child5_dof_d,child5_dof_m,child5_dof_y,child5_doc_Type,child_5_IC,child5_doc_Number,child5_pass_d,child5_pass_m,child5_pass_y,child_5_nation);

		} else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child8(child_5_firstname,child_5_middlename,child_5_lastname,child5_dof_d,child5_dof_m,child5_dof_y,child5_doc_Type,child_5_IC,child5_doc_Number,child5_pass_d,child5_pass_m,child5_pass_y,child_5_nation);

		}
		
	}
	else if(numofChilds.equals("6"))
	{
		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child1(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child2(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child3(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child4(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("5".equals(numofAdults)) {
			SapPassengerDetails.child5(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("6".equals(numofAdults)) {
			SapPassengerDetails.child6(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("7".equals(numofAdults)) {
			SapPassengerDetails.child7(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("8".equals(numofAdults)) {
			SapPassengerDetails.child8(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		System.out.println("----");
		
		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child2(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child3(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child4(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child5(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("5".equals(numofAdults)) {
			SapPassengerDetails.child6(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("6".equals(numofAdults)) {
			SapPassengerDetails.child7(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("7".equals(numofAdults)) {
			SapPassengerDetails.child8(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
         System.out.println("----");
		
		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child3(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child4(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child5(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child6(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		else if ("5".equals(numofAdults)) {
			SapPassengerDetails.child7(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		else if ("6".equals(numofAdults)) {
			SapPassengerDetails.child8(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		
		System.out.println("----");

		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child4(child_4_firstname,child_4_middlename,child_4_lastname,child4_dof_d,child4_dof_m,child4_dof_y,child4_doc_Type,child_4_IC,child4_doc_Number,child4_pass_d,child4_pass_m,child4_pass_y,child_4_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child5(child_4_firstname,child_4_middlename,child_4_lastname,child4_dof_d,child4_dof_m,child4_dof_y,child4_doc_Type,child_4_IC,child4_doc_Number,child4_pass_d,child4_pass_m,child4_pass_y,child_4_nation);

		} else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child6(child_4_firstname,child_4_middlename,child_4_lastname,child4_dof_d,child4_dof_m,child4_dof_y,child4_doc_Type,child_4_IC,child4_doc_Number,child4_pass_d,child4_pass_m,child4_pass_y,child_4_nation);

		} else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child7(child_4_firstname,child_4_middlename,child_4_lastname,child4_dof_d,child4_dof_m,child4_dof_y,child4_doc_Type,child_4_IC,child4_doc_Number,child4_pass_d,child4_pass_m,child4_pass_y,child_4_nation);

		} else if ("5".equals(numofAdults)) {
			SapPassengerDetails.child8(child_4_firstname,child_4_middlename,child_4_lastname,child4_dof_d,child4_dof_m,child4_dof_y,child4_doc_Type,child_4_IC,child4_doc_Number,child4_pass_d,child4_pass_m,child4_pass_y,child_4_nation);

		}
		System.out.println("----");

		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child5(child_5_firstname,child_5_middlename,child_5_lastname,child5_dof_d,child5_dof_m,child5_dof_y,child5_doc_Type,child_5_IC,child5_doc_Number,child5_pass_d,child5_pass_m,child5_pass_y,child_5_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child6(child_5_firstname,child_5_middlename,child_5_lastname,child5_dof_d,child5_dof_m,child5_dof_y,child5_doc_Type,child_5_IC,child5_doc_Number,child5_pass_d,child5_pass_m,child5_pass_y,child_5_nation);

		} else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child7(child_5_firstname,child_5_middlename,child_5_lastname,child5_dof_d,child5_dof_m,child5_dof_y,child5_doc_Type,child_5_IC,child5_doc_Number,child5_pass_d,child5_pass_m,child5_pass_y,child_5_nation);

		} else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child8(child_5_firstname,child_5_middlename,child_5_lastname,child5_dof_d,child5_dof_m,child5_dof_y,child5_doc_Type,child_5_IC,child5_doc_Number,child5_pass_d,child5_pass_m,child5_pass_y,child_5_nation);

		}
		
		System.out.println("----");

		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child6(child_6_firstname,child_6_middlename,child_6_lastname,child6_dof_d,child6_dof_m,child6_dof_y,child6_doc_Type,child_6_IC,child6_doc_Number,child6_pass_d,child6_pass_m,child6_pass_y,child_6_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child7(child_6_firstname,child_6_middlename,child_6_lastname,child6_dof_d,child6_dof_m,child6_dof_y,child6_doc_Type,child_6_IC,child6_doc_Number,child6_pass_d,child6_pass_m,child6_pass_y,child_6_nation);

		} else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child8(child_6_firstname,child_6_middlename,child_6_lastname,child6_dof_d,child6_dof_m,child6_dof_y,child6_doc_Type,child_6_IC,child6_doc_Number,child6_pass_d,child6_pass_m,child6_pass_y,child_6_nation);

		}
			
	}
	else if(numofChilds.equals("7"))
	{
		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child1(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child2(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child3(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child4(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("5".equals(numofAdults)) {
			SapPassengerDetails.child5(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("6".equals(numofAdults)) {
			SapPassengerDetails.child6(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("7".equals(numofAdults)) {
			SapPassengerDetails.child7(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("8".equals(numofAdults)) {
			SapPassengerDetails.child8(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		System.out.println("----");
		
		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child2(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child3(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child4(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child5(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("5".equals(numofAdults)) {
			SapPassengerDetails.child6(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("6".equals(numofAdults)) {
			SapPassengerDetails.child7(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("7".equals(numofAdults)) {
			SapPassengerDetails.child8(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
         System.out.println("----");
		
		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child3(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child4(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child5(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child6(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		else if ("5".equals(numofAdults)) {
			SapPassengerDetails.child7(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		else if ("6".equals(numofAdults)) {
			SapPassengerDetails.child8(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		
		System.out.println("----");

		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child4(child_4_firstname,child_4_middlename,child_4_lastname,child4_dof_d,child4_dof_m,child4_dof_y,child4_doc_Type,child_4_IC,child4_doc_Number,child4_pass_d,child4_pass_m,child4_pass_y,child_4_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child5(child_4_firstname,child_4_middlename,child_4_lastname,child4_dof_d,child4_dof_m,child4_dof_y,child4_doc_Type,child_4_IC,child4_doc_Number,child4_pass_d,child4_pass_m,child4_pass_y,child_4_nation);

		} else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child6(child_4_firstname,child_4_middlename,child_4_lastname,child4_dof_d,child4_dof_m,child4_dof_y,child4_doc_Type,child_4_IC,child4_doc_Number,child4_pass_d,child4_pass_m,child4_pass_y,child_4_nation);

		} else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child7(child_4_firstname,child_4_middlename,child_4_lastname,child4_dof_d,child4_dof_m,child4_dof_y,child4_doc_Type,child_4_IC,child4_doc_Number,child4_pass_d,child4_pass_m,child4_pass_y,child_4_nation);

		} else if ("5".equals(numofAdults)) {
			SapPassengerDetails.child8(child_4_firstname,child_4_middlename,child_4_lastname,child4_dof_d,child4_dof_m,child4_dof_y,child4_doc_Type,child_4_IC,child4_doc_Number,child4_pass_d,child4_pass_m,child4_pass_y,child_4_nation);

		}
		System.out.println("----");

		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child5(child_5_firstname,child_5_middlename,child_5_lastname,child5_dof_d,child5_dof_m,child5_dof_y,child5_doc_Type,child_5_IC,child5_doc_Number,child5_pass_d,child5_pass_m,child5_pass_y,child_5_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child6(child_5_firstname,child_5_middlename,child_5_lastname,child5_dof_d,child5_dof_m,child5_dof_y,child5_doc_Type,child_5_IC,child5_doc_Number,child5_pass_d,child5_pass_m,child5_pass_y,child_5_nation);

		} else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child7(child_5_firstname,child_5_middlename,child_5_lastname,child5_dof_d,child5_dof_m,child5_dof_y,child5_doc_Type,child_5_IC,child5_doc_Number,child5_pass_d,child5_pass_m,child5_pass_y,child_5_nation);

		} else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child8(child_5_firstname,child_5_middlename,child_5_lastname,child5_dof_d,child5_dof_m,child5_dof_y,child5_doc_Type,child_5_IC,child5_doc_Number,child5_pass_d,child5_pass_m,child5_pass_y,child_5_nation);

		}
		
		System.out.println("----");

		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child6(child_6_firstname,child_6_middlename,child_6_lastname,child6_dof_d,child6_dof_m,child6_dof_y,child6_doc_Type,child_6_IC,child6_doc_Number,child6_pass_d,child6_pass_m,child6_pass_y,child_6_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child7(child_6_firstname,child_6_middlename,child_6_lastname,child6_dof_d,child6_dof_m,child6_dof_y,child6_doc_Type,child_6_IC,child6_doc_Number,child6_pass_d,child6_pass_m,child6_pass_y,child_6_nation);

		} else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child8(child_6_firstname,child_6_middlename,child_6_lastname,child6_dof_d,child6_dof_m,child6_dof_y,child6_doc_Type,child_6_IC,child6_doc_Number,child6_pass_d,child6_pass_m,child6_pass_y,child_6_nation);

		}
		
		System.out.println("----");

		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child7(child_7_firstname,child_7_middlename,child_7_lastname,child7_dof_d,child7_dof_m,child7_dof_y,child7_doc_Type,child_7_IC,child7_doc_Number,child7_pass_d,child7_pass_m,child7_pass_y,child_7_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child8(child_7_firstname,child_7_middlename,child_7_lastname,child7_dof_d,child7_dof_m,child7_dof_y,child7_doc_Type,child_7_IC,child7_doc_Number,child7_pass_d,child7_pass_m,child7_pass_y,child_7_nation);

		}
	}
	
	
	else if(numofChilds.equals("8"))
	{
		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child1(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child2(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child3(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child4(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("5".equals(numofAdults)) {
			SapPassengerDetails.child5(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("6".equals(numofAdults)) {
			SapPassengerDetails.child6(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("7".equals(numofAdults)) {
			SapPassengerDetails.child7(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		else if ("8".equals(numofAdults)) {
			SapPassengerDetails.child8(child_1_firstname,child_1_middlename,child_1_lastname,child1_dof_d,child1_dof_m,child1_dof_y,child1_doc_Type,child_1_IC,child1_doc_Number,child1_pass_d,child1_pass_m,child1_pass_y,child_1_nation);

		}
		System.out.println("----");
		
		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child2(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child3(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child4(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child5(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("5".equals(numofAdults)) {
			SapPassengerDetails.child6(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("6".equals(numofAdults)) {
			SapPassengerDetails.child7(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
		else if ("7".equals(numofAdults)) {
			SapPassengerDetails.child8(child_2_firstname,child_2_middlename,child_2_lastname,child2_dof_d,child2_dof_m,child2_dof_y,child2_doc_Type,child_2_IC,child2_doc_Number,child2_pass_d,child2_pass_m,child2_pass_y,child_2_nation);

		}
         System.out.println("----");
		
		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child3(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child4(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child5(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child6(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		else if ("5".equals(numofAdults)) {
			SapPassengerDetails.child7(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		else if ("6".equals(numofAdults)) {
			SapPassengerDetails.child8(child_3_firstname,child_3_middlename,child_3_lastname,child3_dof_d,child3_dof_m,child3_dof_y,child3_doc_Type,child_3_IC,child3_doc_Number,child3_pass_d,child3_pass_m,child3_pass_y,child_3_nation);

		}
		
		System.out.println("----");

		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child4(child_4_firstname,child_4_middlename,child_4_lastname,child4_dof_d,child4_dof_m,child4_dof_y,child4_doc_Type,child_4_IC,child4_doc_Number,child4_pass_d,child4_pass_m,child4_pass_y,child_4_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child5(child_4_firstname,child_4_middlename,child_4_lastname,child4_dof_d,child4_dof_m,child4_dof_y,child4_doc_Type,child_4_IC,child4_doc_Number,child4_pass_d,child4_pass_m,child4_pass_y,child_4_nation);

		} else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child6(child_4_firstname,child_4_middlename,child_4_lastname,child4_dof_d,child4_dof_m,child4_dof_y,child4_doc_Type,child_4_IC,child4_doc_Number,child4_pass_d,child4_pass_m,child4_pass_y,child_4_nation);

		} else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child7(child_4_firstname,child_4_middlename,child_4_lastname,child4_dof_d,child4_dof_m,child4_dof_y,child4_doc_Type,child_4_IC,child4_doc_Number,child4_pass_d,child4_pass_m,child4_pass_y,child_4_nation);

		} else if ("5".equals(numofAdults)) {
			SapPassengerDetails.child8(child_4_firstname,child_4_middlename,child_4_lastname,child4_dof_d,child4_dof_m,child4_dof_y,child4_doc_Type,child_4_IC,child4_doc_Number,child4_pass_d,child4_pass_m,child4_pass_y,child_4_nation);

		}
		System.out.println("----");

		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child5(child_5_firstname,child_5_middlename,child_5_lastname,child5_dof_d,child5_dof_m,child5_dof_y,child5_doc_Type,child_5_IC,child5_doc_Number,child5_pass_d,child5_pass_m,child5_pass_y,child_5_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child6(child_5_firstname,child_5_middlename,child_5_lastname,child5_dof_d,child5_dof_m,child5_dof_y,child5_doc_Type,child_5_IC,child5_doc_Number,child5_pass_d,child5_pass_m,child5_pass_y,child_5_nation);

		} else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child7(child_5_firstname,child_5_middlename,child_5_lastname,child5_dof_d,child5_dof_m,child5_dof_y,child5_doc_Type,child_5_IC,child5_doc_Number,child5_pass_d,child5_pass_m,child5_pass_y,child_5_nation);

		} else if ("4".equals(numofAdults)) {
			SapPassengerDetails.child8(child_5_firstname,child_5_middlename,child_5_lastname,child5_dof_d,child5_dof_m,child5_dof_y,child5_doc_Type,child_5_IC,child5_doc_Number,child5_pass_d,child5_pass_m,child5_pass_y,child_5_nation);

		}
		
		System.out.println("----");

		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child6(child_6_firstname,child_6_middlename,child_6_lastname,child6_dof_d,child6_dof_m,child6_dof_y,child6_doc_Type,child_6_IC,child6_doc_Number,child6_pass_d,child6_pass_m,child6_pass_y,child_6_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child7(child_6_firstname,child_6_middlename,child_6_lastname,child6_dof_d,child6_dof_m,child6_dof_y,child6_doc_Type,child_6_IC,child6_doc_Number,child6_pass_d,child6_pass_m,child6_pass_y,child_6_nation);

		} else if ("3".equals(numofAdults)) {
			SapPassengerDetails.child8(child_6_firstname,child_6_middlename,child_6_lastname,child6_dof_d,child6_dof_m,child6_dof_y,child6_doc_Type,child_6_IC,child6_doc_Number,child6_pass_d,child6_pass_m,child6_pass_y,child_6_nation);

		}
		
		System.out.println("----");

		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child7(child_7_firstname,child_7_middlename,child_7_lastname,child7_dof_d,child7_dof_m,child7_dof_y,child7_doc_Type,child_7_IC,child7_doc_Number,child7_pass_d,child7_pass_m,child7_pass_y,child_7_nation);
		} else if ("2".equals(numofAdults)) {
			SapPassengerDetails.child8(child_7_firstname,child_7_middlename,child_7_lastname,child7_dof_d,child7_dof_m,child7_dof_y,child7_doc_Type,child_7_IC,child7_doc_Number,child7_pass_d,child7_pass_m,child7_pass_y,child_7_nation);

		}
		
		System.out.println("----");

		if ("1".equals(numofAdults)) {
			SapPassengerDetails.child8(child_8_firstname,child_8_middlename,child_8_lastname,child8_dof_d,child8_dof_m,child8_dof_y,child8_doc_Type,child_8_IC,child8_doc_Number,child8_pass_d,child8_pass_m,child8_pass_y,child_8_nation);
		} 
	}
	
	
	Integer totalValue = Integer.parseInt(numofChilds) + Integer.parseInt(numofAdults);
	System.out.println("totalValue:" + totalValue);
	String numberAsString = Integer.toString(totalValue);
	System.out.println(numberAsString);
	
	if ("1".equals(numofInfants)) {
		if ("1".equals(numberAsString)) {
		   SapPassengerDetails.Infant1(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
		} else if ("2".equals(numberAsString)) {
			 SapPassengerDetails.Infant2(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
			
		} else if ("3".equals(numberAsString)) {
			 SapPassengerDetails.Infant3(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
		} else if ("4".equals(numberAsString)) {
			 SapPassengerDetails.Infant4(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
		
		} else if ("5".equals(numberAsString)) {
			 SapPassengerDetails.Infant5(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
			
		} else if ("6".equals(numberAsString)) {
			 SapPassengerDetails.Infant6(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
			
		} else if ("7".equals(numberAsString)) {
			 SapPassengerDetails.Infant7(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
			
		} else if ("8".equals(numberAsString)) {
			 SapPassengerDetails.Infant8(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
			
		}
		
	}
	
	if ("2".equals(numofInfants)) {
		
		if ("1".equals(numberAsString)) {
			   SapPassengerDetails.Infant1(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
			} else if ("2".equals(numberAsString)) {
				 SapPassengerDetails.Infant2(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
				
			} else if ("3".equals(numberAsString)) {
				 SapPassengerDetails.Infant3(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
			} else if ("4".equals(numberAsString)) {
				 SapPassengerDetails.Infant4(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
			
			} else if ("5".equals(numberAsString)) {
				 SapPassengerDetails.Infant5(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
				
			} else if ("6".equals(numberAsString)) {
				 SapPassengerDetails.Infant6(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
				
			} else if ("7".equals(numberAsString)) {
				 SapPassengerDetails.Infant7(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
				
			} else if ("8".equals(numberAsString)) {
				 SapPassengerDetails.Infant8(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
				
			}
		      System.out.println("----");
		     if ("1".equals(numberAsString)) {
			   SapPassengerDetails.Infant2(infant_2_Title,infant_2_firstname,infant_2_lastname,infant2_dof_d,infant2_dof_m,infant2_dof_y,infant2_doc_Type,infant_2_IC,infant2_doc_Number,infant2_pass_d,infant2_pass_m,infant2_pass_y,infant_2_nation);
			} else if ("2".equals(numberAsString)) {
				 SapPassengerDetails.Infant3(infant_2_Title,infant_2_firstname,infant_2_lastname,infant2_dof_d,infant2_dof_m,infant2_dof_y,infant2_doc_Type,infant_2_IC,infant2_doc_Number,infant2_pass_d,infant2_pass_m,infant2_pass_y,infant_2_nation);
				
			} else if ("3".equals(numberAsString)) {
				 SapPassengerDetails.Infant4(infant_2_Title,infant_2_firstname,infant_2_lastname,infant2_dof_d,infant2_dof_m,infant2_dof_y,infant2_doc_Type,infant_2_IC,infant2_doc_Number,infant2_pass_d,infant2_pass_m,infant2_pass_y,infant_2_nation);
			} else if ("4".equals(numberAsString)) {
				 SapPassengerDetails.Infant5(infant_2_Title,infant_2_firstname,infant_2_lastname,infant2_dof_d,infant2_dof_m,infant2_dof_y,infant2_doc_Type,infant_2_IC,infant2_doc_Number,infant2_pass_d,infant2_pass_m,infant2_pass_y,infant_2_nation);
			
			} else if ("5".equals(numberAsString)) {
				 SapPassengerDetails.Infant6(infant_2_Title,infant_2_firstname,infant_2_lastname,infant2_dof_d,infant2_dof_m,infant2_dof_y,infant2_doc_Type,infant_2_IC,infant2_doc_Number,infant2_pass_d,infant2_pass_m,infant2_pass_y,infant_2_nation);
				
			} else if ("6".equals(numberAsString)) {
				 SapPassengerDetails.Infant7(infant_2_Title,infant_2_firstname,infant_2_lastname,infant2_dof_d,infant2_dof_m,infant2_dof_y,infant2_doc_Type,infant_2_IC,infant2_doc_Number,infant2_pass_d,infant2_pass_m,infant2_pass_y,infant_2_nation);
				
			} else if ("7".equals(numberAsString)) {
				 SapPassengerDetails.Infant8(infant_2_Title,infant_2_firstname,infant_2_lastname,infant2_dof_d,infant2_dof_m,infant2_dof_y,infant2_doc_Type,infant_2_IC,infant2_doc_Number,infant2_pass_d,infant2_pass_m,infant2_pass_y,infant_2_nation);
				
			} 
	}
		
		if ("3".equals(numofInfants)) {
			
			if ("1".equals(numberAsString)) {
				   SapPassengerDetails.Infant1(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
				} else if ("2".equals(numberAsString)) {
					 SapPassengerDetails.Infant2(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
					
				} else if ("3".equals(numberAsString)) {
					 SapPassengerDetails.Infant3(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
				} else if ("4".equals(numberAsString)) {
					 SapPassengerDetails.Infant4(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
				
				} else if ("5".equals(numberAsString)) {
					 SapPassengerDetails.Infant5(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
					
				} else if ("6".equals(numberAsString)) {
					 SapPassengerDetails.Infant6(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
					
				} else if ("7".equals(numberAsString)) {
					 SapPassengerDetails.Infant7(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
					
				} else if ("8".equals(numberAsString)) {
					 SapPassengerDetails.Infant8(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
					
				}
			      System.out.println("----");
			     if ("1".equals(numberAsString)) {
				   SapPassengerDetails.Infant2(infant_2_Title,infant_2_firstname,infant_2_lastname,infant2_dof_d,infant2_dof_m,infant2_dof_y,infant2_doc_Type,infant_2_IC,infant2_doc_Number,infant2_pass_d,infant2_pass_m,infant2_pass_y,infant_2_nation);
				} else if ("2".equals(numberAsString)) {
					 SapPassengerDetails.Infant3(infant_2_Title,infant_2_firstname,infant_2_lastname,infant2_dof_d,infant2_dof_m,infant2_dof_y,infant2_doc_Type,infant_2_IC,infant2_doc_Number,infant2_pass_d,infant2_pass_m,infant2_pass_y,infant_2_nation);
					
				} else if ("3".equals(numberAsString)) {
					 SapPassengerDetails.Infant4(infant_2_Title,infant_2_firstname,infant_2_lastname,infant2_dof_d,infant2_dof_m,infant2_dof_y,infant2_doc_Type,infant_2_IC,infant2_doc_Number,infant2_pass_d,infant2_pass_m,infant2_pass_y,infant_2_nation);
				} else if ("4".equals(numberAsString)) {
					 SapPassengerDetails.Infant5(infant_2_Title,infant_2_firstname,infant_2_lastname,infant2_dof_d,infant2_dof_m,infant2_dof_y,infant2_doc_Type,infant_2_IC,infant2_doc_Number,infant2_pass_d,infant2_pass_m,infant2_pass_y,infant_2_nation);
				
				} else if ("5".equals(numberAsString)) {
					 SapPassengerDetails.Infant6(infant_2_Title,infant_2_firstname,infant_2_lastname,infant2_dof_d,infant2_dof_m,infant2_dof_y,infant2_doc_Type,infant_2_IC,infant2_doc_Number,infant2_pass_d,infant2_pass_m,infant2_pass_y,infant_2_nation);
					
				} else if ("6".equals(numberAsString)) {
					 SapPassengerDetails.Infant7(infant_2_Title,infant_2_firstname,infant_2_lastname,infant2_dof_d,infant2_dof_m,infant2_dof_y,infant2_doc_Type,infant_2_IC,infant2_doc_Number,infant2_pass_d,infant2_pass_m,infant2_pass_y,infant_2_nation);
					
				} else if ("7".equals(numberAsString)) {
					 SapPassengerDetails.Infant8(infant_2_Title,infant_2_firstname,infant_2_lastname,infant2_dof_d,infant2_dof_m,infant2_dof_y,infant2_doc_Type,infant_2_IC,infant2_doc_Number,infant2_pass_d,infant2_pass_m,infant2_pass_y,infant_2_nation);
					
				} 
			     System.out.println("----");
			     if ("1".equals(numberAsString)) {
				   SapPassengerDetails.Infant3(infant_3_Title,infant_3_firstname,infant_3_lastname,infant3_dof_d,infant3_dof_m,infant3_dof_y,infant3_doc_Type,infant_3_IC,infant3_doc_Number,infant3_pass_d,infant3_pass_m,infant3_pass_y,infant_3_nation);
				} else if ("2".equals(numberAsString)) {
					 SapPassengerDetails.Infant4(infant_3_Title,infant_3_firstname,infant_3_lastname,infant3_dof_d,infant3_dof_m,infant3_dof_y,infant3_doc_Type,infant_3_IC,infant3_doc_Number,infant3_pass_d,infant3_pass_m,infant3_pass_y,infant_3_nation);
					
				} else if ("3".equals(numberAsString)) {
					 SapPassengerDetails.Infant5(infant_3_Title,infant_3_firstname,infant_3_lastname,infant3_dof_d,infant3_dof_m,infant3_dof_y,infant3_doc_Type,infant_3_IC,infant3_doc_Number,infant3_pass_d,infant3_pass_m,infant3_pass_y,infant_3_nation);
				} else if ("4".equals(numberAsString)) {
					 SapPassengerDetails.Infant6(infant_3_Title,infant_3_firstname,infant_3_lastname,infant3_dof_d,infant3_dof_m,infant3_dof_y,infant3_doc_Type,infant_3_IC,infant3_doc_Number,infant3_pass_d,infant3_pass_m,infant3_pass_y,infant_3_nation);
				
				} else if ("5".equals(numberAsString)) {
					 SapPassengerDetails.Infant7(infant_3_Title,infant_3_firstname,infant_3_lastname,infant3_dof_d,infant3_dof_m,infant3_dof_y,infant3_doc_Type,infant_3_IC,infant3_doc_Number,infant3_pass_d,infant3_pass_m,infant3_pass_y,infant_3_nation);
					
				} else if ("6".equals(numberAsString)) {
					 SapPassengerDetails.Infant8(infant_3_Title,infant_3_firstname,infant_3_lastname,infant3_dof_d,infant3_dof_m,infant3_dof_y,infant3_doc_Type,infant_3_IC,infant3_doc_Number,infant3_pass_d,infant3_pass_m,infant3_pass_y,infant_3_nation);
					
				} 
			     
			     
		}
		
		if ("4".equals(numofInfants)) {
			
			if ("1".equals(numberAsString)) {
				   SapPassengerDetails.Infant1(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
				} else if ("2".equals(numberAsString)) {
					 SapPassengerDetails.Infant2(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
					
				} else if ("3".equals(numberAsString)) {
					 SapPassengerDetails.Infant3(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
				} else if ("4".equals(numberAsString)) {
					 SapPassengerDetails.Infant4(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
				
				} else if ("5".equals(numberAsString)) {
					 SapPassengerDetails.Infant5(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
					
				} else if ("6".equals(numberAsString)) {
					 SapPassengerDetails.Infant6(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
					
				} else if ("7".equals(numberAsString)) {
					 SapPassengerDetails.Infant7(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
					
				} else if ("8".equals(numberAsString)) {
					 SapPassengerDetails.Infant8(infant_1_Title,infant_1_firstname,infant_1_lastname,infant1_dof_d,infant1_dof_m,infant1_dof_y,infant1_doc_Type,infant_1_IC,infant1_doc_Number,infant1_pass_d,infant1_pass_m,infant1_pass_y,infant_1_nation);
					
				}
			      System.out.println("----");
			     if ("1".equals(numberAsString)) {
				   SapPassengerDetails.Infant2(infant_2_Title,infant_2_firstname,infant_2_lastname,infant2_dof_d,infant2_dof_m,infant2_dof_y,infant2_doc_Type,infant_2_IC,infant2_doc_Number,infant2_pass_d,infant2_pass_m,infant2_pass_y,infant_2_nation);
				} else if ("2".equals(numberAsString)) {
					 SapPassengerDetails.Infant3(infant_2_Title,infant_2_firstname,infant_2_lastname,infant2_dof_d,infant2_dof_m,infant2_dof_y,infant2_doc_Type,infant_2_IC,infant2_doc_Number,infant2_pass_d,infant2_pass_m,infant2_pass_y,infant_2_nation);
					
				} else if ("3".equals(numberAsString)) {
					 SapPassengerDetails.Infant4(infant_2_Title,infant_2_firstname,infant_2_lastname,infant2_dof_d,infant2_dof_m,infant2_dof_y,infant2_doc_Type,infant_2_IC,infant2_doc_Number,infant2_pass_d,infant2_pass_m,infant2_pass_y,infant_2_nation);
				} else if ("4".equals(numberAsString)) {
					 SapPassengerDetails.Infant5(infant_2_Title,infant_2_firstname,infant_2_lastname,infant2_dof_d,infant2_dof_m,infant2_dof_y,infant2_doc_Type,infant_2_IC,infant2_doc_Number,infant2_pass_d,infant2_pass_m,infant2_pass_y,infant_2_nation);
				
				} else if ("5".equals(numberAsString)) {
					 SapPassengerDetails.Infant6(infant_2_Title,infant_2_firstname,infant_2_lastname,infant2_dof_d,infant2_dof_m,infant2_dof_y,infant2_doc_Type,infant_2_IC,infant2_doc_Number,infant2_pass_d,infant2_pass_m,infant2_pass_y,infant_2_nation);
					
				} else if ("6".equals(numberAsString)) {
					 SapPassengerDetails.Infant7(infant_2_Title,infant_2_firstname,infant_2_lastname,infant2_dof_d,infant2_dof_m,infant2_dof_y,infant2_doc_Type,infant_2_IC,infant2_doc_Number,infant2_pass_d,infant2_pass_m,infant2_pass_y,infant_2_nation);
					
				} else if ("7".equals(numberAsString)) {
					 SapPassengerDetails.Infant8(infant_2_Title,infant_2_firstname,infant_2_lastname,infant2_dof_d,infant2_dof_m,infant2_dof_y,infant2_doc_Type,infant_2_IC,infant2_doc_Number,infant2_pass_d,infant2_pass_m,infant2_pass_y,infant_2_nation);
					
				} 
			     System.out.println("----");
			     if ("1".equals(numberAsString)) {
				   SapPassengerDetails.Infant3(infant_3_Title,infant_3_firstname,infant_3_lastname,infant3_dof_d,infant3_dof_m,infant3_dof_y,infant3_doc_Type,infant_3_IC,infant3_doc_Number,infant3_pass_d,infant3_pass_m,infant3_pass_y,infant_3_nation);
				} else if ("2".equals(numberAsString)) {
					 SapPassengerDetails.Infant4(infant_3_Title,infant_3_firstname,infant_3_lastname,infant3_dof_d,infant3_dof_m,infant3_dof_y,infant3_doc_Type,infant_3_IC,infant3_doc_Number,infant3_pass_d,infant3_pass_m,infant3_pass_y,infant_3_nation);
					
				} else if ("3".equals(numberAsString)) {
					 SapPassengerDetails.Infant5(infant_3_Title,infant_3_firstname,infant_3_lastname,infant3_dof_d,infant3_dof_m,infant3_dof_y,infant3_doc_Type,infant_3_IC,infant3_doc_Number,infant3_pass_d,infant3_pass_m,infant3_pass_y,infant_3_nation);
				} else if ("4".equals(numberAsString)) {
					 SapPassengerDetails.Infant6(infant_3_Title,infant_3_firstname,infant_3_lastname,infant3_dof_d,infant3_dof_m,infant3_dof_y,infant3_doc_Type,infant_3_IC,infant3_doc_Number,infant3_pass_d,infant3_pass_m,infant3_pass_y,infant_3_nation);
				
				} else if ("5".equals(numberAsString)) {
					 SapPassengerDetails.Infant7(infant_3_Title,infant_3_firstname,infant_3_lastname,infant3_dof_d,infant3_dof_m,infant3_dof_y,infant3_doc_Type,infant_3_IC,infant3_doc_Number,infant3_pass_d,infant3_pass_m,infant3_pass_y,infant_3_nation);
					
				} else if ("6".equals(numberAsString)) {
					 SapPassengerDetails.Infant8(infant_3_Title,infant_3_firstname,infant_3_lastname,infant3_dof_d,infant3_dof_m,infant3_dof_y,infant3_doc_Type,infant_3_IC,infant3_doc_Number,infant3_pass_d,infant3_pass_m,infant3_pass_y,infant_3_nation);
					
				} 
			     
			     System.out.println("----");
			     if ("1".equals(numberAsString)) {
				   SapPassengerDetails.Infant4(infant_4_Title,infant_4_firstname,infant_4_lastname,infant4_dof_d,infant4_dof_m,infant4_dof_y,infant4_doc_Type,infant_4_IC,infant4_doc_Number,infant4_pass_d,infant4_pass_m,infant4_pass_y,infant_4_nation);
				} else if ("2".equals(numberAsString)) {
					 SapPassengerDetails.Infant5(infant_4_Title,infant_4_firstname,infant_4_lastname,infant4_dof_d,infant4_dof_m,infant4_dof_y,infant4_doc_Type,infant_4_IC,infant4_doc_Number,infant4_pass_d,infant4_pass_m,infant4_pass_y,infant_4_nation);
					
				} else if ("3".equals(numberAsString)) {
					 SapPassengerDetails.Infant6(infant_4_Title,infant_4_firstname,infant_4_lastname,infant4_dof_d,infant4_dof_m,infant4_dof_y,infant4_doc_Type,infant_4_IC,infant4_doc_Number,infant4_pass_d,infant4_pass_m,infant4_pass_y,infant_4_nation);
				} else if ("4".equals(numberAsString)) {
					 SapPassengerDetails.Infant7(infant_4_Title,infant_4_firstname,infant_4_lastname,infant4_dof_d,infant4_dof_m,infant4_dof_y,infant4_doc_Type,infant_4_IC,infant4_doc_Number,infant4_pass_d,infant4_pass_m,infant4_pass_y,infant_4_nation);
				
				} else if ("5".equals(numberAsString)) {
					 SapPassengerDetails.Infant8(infant_4_Title,infant_4_firstname,infant_4_lastname,infant4_dof_d,infant4_dof_m,infant4_dof_y,infant4_doc_Type,infant_4_IC,infant4_doc_Number,infant4_pass_d,infant4_pass_m,infant4_pass_y,infant_4_nation);
					
				}
			     
		}
	
		}
	catch(Exception e)
	{
		PageUtils.getScreenShot(PnrDetails.PnrId,driver);
		SapPassengerDetails.returnStatus_fail(PnrDetails.Domain,PnrDetails.PnrId,"Document Details Element Not Found");
	driver.quit();
	}
	
	
	
	Thread.sleep(2000);
  
	System.out.println("Enter All Passenger Details Successfully");
	////PageUtils.getScreenShot(pnrdetails.PnrId,driver);
	/*flynasPage.txt_Conf_Pwd().sendKeys("test");
	flynasPage.txt_Conf_Pwd().clear();*/
	
	
	
	
}


public static void enterContactInformation() throws Exception
{
	PageUtils.selectByVisibleText(saptcoPage.select_Mobile_Number_CountryCode(), "");
	saptcoPage.enter_Mobile_Number().sendKeys("");
	saptcoPage.enter_email().sendKeys("");
	saptcoPage.enter_Confirm_email().sendKeys("");
	
	saptcoPage.btn_Continue_Passengers().click();
}

public static void checkTicket() throws InterruptedException, Exception
{
	PageUtils.isElementLocated(driver, By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ToStep5_btn"));
	
	if(!saptcoPage.chk_Agree().isSelected()){
		saptcoPage.chk_Agree().click();
		PageUtils.waitForFixedTime(BrowserContants.WAIT_VERY_SMALL);
	}
	saptcoPage.btn_Continue_Ticket().click();
}

public static void enterCardDetails() throws Exception
{
	PageUtils.isElementLocated(driver, By.xpath("//span[contains(text(),'Payment by credit card')]"));
    saptcoPage.select_cardType_CC().click();
    
    PageUtils.isElementLocated(driver, By.xpath("//img[@name='MasterCard']"));
    
    saptcoPage.txt_card_number().sendKeys("");
    saptcoPage.txt_exp_month().sendKeys("");
    saptcoPage.txt_exp_year().sendKeys("");
    saptcoPage.txt_Cvv_Number().sendKeys("");
    saptcoPage.Paybutton().click();
    
    
    

	
}
}