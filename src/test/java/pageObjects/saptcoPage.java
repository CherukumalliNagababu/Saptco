package pageObjects;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import SapModules.SapBusDetails;
import jxl.biff.drawing.PNGReader;



public class saptcoPage extends BaseClass {

	private static WebElement element;
	
	
	public saptcoPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public static WebElement oneyWay() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.xpath("//label[contains(text(),'One way')]"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement roundTrip() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.xpath("//label[contains(text(),'Round trip')]"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement multiCity() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.xpath("//label[contains(text(),'Multi')]"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	
	public static WebElement From() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.xpath("//input[@placeholder='Departure from']"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	
	
	public static WebElement To() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.xpath("//input[@placeholder='Arrival to']"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	
	public static WebElement enter_Date() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.xpath("//input[@id='daterange']"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	
	public static WebElement btn_passenger() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("PassInfo_Summary"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	
	
	
	
	public static WebElement adult_increase() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Adults'])[1]/following::span[10]"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	
	public static WebElement child_increase() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Children'])[1]/following::span[10]"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	
	public static WebElement infant_increase() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Infants'])[1]/following::span[10]"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	
	public static WebElement btn_Home_Search() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Infants'])[1]/following::input[3]"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static List<WebElement> Srp_Results_From() throws Exception {
		List<WebElement> element = null;
		List<WebElement> RegularPrice=null;
		List<WebElement> offerPrice=null;
		
		 List<SapBusDetails> finalList =  new ArrayList<SapBusDetails>();
		try {
			Thread.sleep(5000);
			try {
				element = driver.findElements(By.xpath("//div[@id='tabs--1']//div[@class='tripContainer']"));
				System.out.println("Tab1");
			} catch (Exception we) {

			}
			try {
				element = driver.findElements(By.xpath("//div[@id='tabs-1']//div[@class='tripContainer']"));
				System.out.println("Tab2");
			} catch (Exception we) {

			}
			try {
				element = driver.findElements(By.xpath("//div[@id='tabs-0']//div[@class='tripContainer']"));
				System.out.println("Tab3");
			} catch (Exception we) {

			}
			
			

			String StartTime;
			String From;
			String TO;
			String hours;
			String onePlus;
			String EndTime;
			String regularPriceText;
			String regularCost;
			String OfferPriceText;
			String OfferPriceCost;
			for(WebElement e:element)
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
				
				
				
				
				
			}
			
			
				
			
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	
	//div[@class='tripContainer']
	
	public static List<WebElement> Srp_RegularPrice() throws Exception {
		List<WebElement> element = null;
		try {
			element = driver.findElements(By.xpath("//div[@class='standerdFee Standard']"));
			for(WebElement e:element)
			{
				System.out.println(e.getText());
			}
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static List<WebElement> Srp_OfferPrice() throws Exception {
		List<WebElement> element = null;
		try {
			element = driver.findElements(By.xpath("//div[@class='standerdFee Discount']"));
			for(WebElement e:element)
			{
				System.out.println(e.getText());
			}
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	
	
	//PAssenger Details
	//***************************************** FIRSTNAME*************************************************************************
	public static WebElement FirstName_1() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl00_PassengerInformationControl_TxtFName"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement FirstName_2() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl01_PassengerInformationControl_TxtFName"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement FirstName_3() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl02_PassengerInformationControl_TxtFName"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement FirstName_4() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl03_PassengerInformationControl_TxtFName"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement FirstName_5() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl04_PassengerInformationControl_TxtFName"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement FirstName_6() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl05_PassengerInformationControl_TxtFName"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement FirstName_7() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl06_PassengerInformationControl_TxtFName"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement FirstName_8() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl07_PassengerInformationControl_TxtFName"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement FirstName_9() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl08_PassengerInformationControl_TxtFName"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	
	//***************************************** MIDDLENAME*************************************************************************
	
	public static WebElement MiddleName_1() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl00_PassengerInformationControl_TxtMName"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement MiddleName_2() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl01_PassengerInformationControl_TxtMName"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement MiddleName_3() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl02_PassengerInformationControl_TxtMName"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement MiddleName_4() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl03_PassengerInformationControl_TxtMName"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement MiddleName_5() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl04_PassengerInformationControl_TxtMName"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement MiddleName_6() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl05_PassengerInformationControl_TxtMName"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement MiddleName_7() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl06_PassengerInformationControl_TxtMName"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement MiddleName_8() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl07_PassengerInformationControl_TxtMName"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement MiddleName_9() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl08_PassengerInformationControl_TxtMName"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	
	//***************************************** FAMILYNAME*************************************************************************
	
	public static WebElement FamilyName_1() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl00_PassengerInformationControl_TxtFamilyName"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement FamilyName_2() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl01_PassengerInformationControl_TxtFamilyName"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement FamilyName_3() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl02_PassengerInformationControl_TxtFamilyName"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement FamilyName_4() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl03_PassengerInformationControl_TxtFamilyName"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement FamilyName_5() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl04_PassengerInformationControl_TxtFamilyName"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement FamilyName_6() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl05_PassengerInformationControl_TxtFamilyName"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement FamilyName_7() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl06_PassengerInformationControl_TxtFamilyName"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement FamilyName_8() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl07_PassengerInformationControl_TxtFamilyName"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement FamilyName_9() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl08_PassengerInformationControl_TxtFamilyName"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	
	//***************************************** ID TYPE*************************************************************************
	public static WebElement IdType_1() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl00_PassengerInformationControl_RBLIDType"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement IdType_2() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl01_PassengerInformationControl_RBLIDType"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement IdType_3() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl02_PassengerInformationControl_RBLIDType"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement IdType_4() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl03_PassengerInformationControl_RBLIDType"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement IdType_5() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl04_PassengerInformationControl_RBLIDType"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement IdType_6() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl05_PassengerInformationControl_RBLIDType"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement IdType_7() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl06_PassengerInformationControl_RBLIDType"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement IdType_8() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl07_PassengerInformationControl_RBLIDType"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement IdType_9() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl08_PassengerInformationControl_RBLIDType"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	
	//***************************************** ID TYPE--National ID*************************************************************************
	
	public static WebElement NationalID_txt_1() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl00_PassengerInformationControl_TxtNATID"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement NationalID_txt_2() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl01_PassengerInformationControl_TxtNATID"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement NationalID_txt_3() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl02_PassengerInformationControl_TxtNATID"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement NationalID_txt_4() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl03_PassengerInformationControl_TxtNATID"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement NationalID_txt_5() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl04_PassengerInformationControl_TxtNATID"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement NationalID_txt_6() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl05_PassengerInformationControl_TxtNATID"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement NationalID_txt_7() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl06_PassengerInformationControl_TxtNATID"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement NationalID_txt_8() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl07_PassengerInformationControl_TxtNATID"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	public static WebElement NationalID_txt_9() throws Exception {
		element = null;
		try {
			element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl08_PassengerInformationControl_TxtNATID"));
			} catch (Exception e) {
			throw (e);
		}
		return element;
	}
	
	//***************************************** ID TYPE--IQAMA*************************************************************************
	
		public static WebElement Iqama_txt_1() throws Exception {
			element = null;
			try {
				element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl00_PassengerInformationControl_TxtIQAID"));
				} catch (Exception e) {
				throw (e);
			}
			return element;
		}
		public static WebElement Iqama_txt_2() throws Exception {
			element = null;
			try {
				element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl01_PassengerInformationControl_TxtIQAID"));
				} catch (Exception e) {
				throw (e);
			}
			return element;
		}
		public static WebElement Iqama_txt_3() throws Exception {
			element = null;
			try {
				element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl02_PassengerInformationControl_TxtIQAID"));
				} catch (Exception e) {
				throw (e);
			}
			return element;
		}
		public static WebElement Iqama_txt_4() throws Exception {
			element = null;
			try {
				element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl03_PassengerInformationControl_TxtIQAID"));
				} catch (Exception e) {
				throw (e);
			}
			return element;
		}
		public static WebElement Iqama_txt_5() throws Exception {
			element = null;
			try {
				element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl04_PassengerInformationControl_TxtIQAID"));
				} catch (Exception e) {
				throw (e);
			}
			return element;
		}
		public static WebElement Iqama_txt_6() throws Exception {
			element = null;
			try {
				element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl05_PassengerInformationControl_TxtIQAID"));
				} catch (Exception e) {
				throw (e);
			}
			return element;
		}
		public static WebElement Iqama_txt_7() throws Exception {
			element = null;
			try {
				element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl06_PassengerInformationControl_TxtIQAID"));
				} catch (Exception e) {
				throw (e);
			}
			return element;
		}
		public static WebElement Iqama_txt_8() throws Exception {
			element = null;
			try {
				element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl07_PassengerInformationControl_TxtIQAID"));
				} catch (Exception e) {
				throw (e);
			}
			return element;
		}
		public static WebElement Iqama_txt_9() throws Exception {
			element = null;
			try {
				element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl08_PassengerInformationControl_TxtIQAID"));
				} catch (Exception e) {
				throw (e);
			}
			return element;
		}
		
		//***************************************** ID TYPE--PASSPORT*************************************************************************
		
			public static WebElement Passport_txt_1() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl00_PassengerInformationControl_TxtPassID"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement Passport_txt_2() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl01_PassengerInformationControl_TxtPassID"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement Passport_txt_3() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl02_PassengerInformationControl_TxtPassID"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement Passport_txt_4() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl03_PassengerInformationControl_TxtPassID"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement Passport_txt_5() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl04_PassengerInformationControl_TxtPassID"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement Passport_txt_6() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl05_PassengerInformationControl_TxtPassID"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement Passport_txt_7() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl06_PassengerInformationControl_TxtPassID"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement Passport_txt_8() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl07_PassengerInformationControl_TxtPassID"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement Passport_txt_9() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl08_PassengerInformationControl_TxtPassID"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			
			//***************************************** ID TYPE--GCC CITIZEN*************************************************************************
			
			public static WebElement GccId_txt_1() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl00_PassengerInformationControl_TxtGCCID"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement GccId_txt_2() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl01_PassengerInformationControl_TxtGCCID"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement GccId_txt_3() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl02_PassengerInformationControl_TxtGCCID"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement GccId_txt_4() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl03_PassengerInformationControl_TxtGCCID"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement GccId_txt_5() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl04_PassengerInformationControl_TxtGCCID"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement GccId_txt_6() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl05_PassengerInformationControl_TxtGCCID"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement GccId_txt_7() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl06_PassengerInformationControl_TxtGCCID"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement GccId_txt_8() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl07_PassengerInformationControl_TxtGCCID"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement GccId_txt_9() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl08_PassengerInformationControl_TxtGCCID"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}

			//***************************************** ID TYPE--NATIONALID----Version*************************************************************************
			
			public static WebElement National_Version_Id_1() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl00_PassengerInformationControl_DDNATVersion"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement National_Version_Id_2() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl01_PassengerInformationControl_DDNATVersion"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement National_Version_Id_3() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl02_PassengerInformationControl_DDNATVersion"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement National_Version_Id_4() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl03_PassengerInformationControl_DDNATVersion"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement National_Version_Id_5() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl04_PassengerInformationControl_DDNATVersion"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement National_Version_Id_6() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl05_PassengerInformationControl_DDNATVersion"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement National_Version_Id_7() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl06_PassengerInformationControl_DDNATVersion"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement National_Version_Id_8() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl07_PassengerInformationControl_DDNATVersion"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement National_Version_Id_9() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl08_PassengerInformationControl_DDNATVersion"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			
       //***************************************** ID TYPE--IQAMA----Version*************************************************************************
			
			public static WebElement Iqama_Version_Id_1() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl00_PassengerInformationControl_RBLIQAIDVersion"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement Iqama_Version_Id_2() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl01_PassengerInformationControl_RBLIQAIDVersion"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement Iqama_Version_Id_3() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl02_PassengerInformationControl_RBLIQAIDVersion"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement Iqama_Version_Id_4() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl03_PassengerInformationControl_RBLIQAIDVersion"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement Iqama_Version_Id_5() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl04_PassengerInformationControl_RBLIQAIDVersion"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement Iqama_Version_Id_6() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl05_PassengerInformationControl_RBLIQAIDVersion"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement Iqama_Version_Id_7() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl06_PassengerInformationControl_RBLIQAIDVersion"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement Iqama_Version_Id_8() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl07_PassengerInformationControl_RBLIQAIDVersion"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement Iqama_Version_Id_9() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl08_PassengerInformationControl_RBLIQAIDVersion"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
         //***************************************** ID TYPE--GCC CITIZEN----Version*************************************************************************
			
			public static WebElement Gcc_Version_Id_1() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl00_PassengerInformationControl_DDGCCVersion"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement Gcc_Version_Id_2() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl01_PassengerInformationControl_DDGCCVersion"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement Gcc_Version_Id_3() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl02_PassengerInformationControl_DDGCCVersion"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement Gcc_Version_Id_4() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl03_PassengerInformationControl_DDGCCVersion"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement Gcc_Version_Id_5() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl04_PassengerInformationControl_DDGCCVersion"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement Gcc_Version_Id_6() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl05_PassengerInformationControl_DDGCCVersion"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement Gcc_Version_Id_7() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl06_PassengerInformationControl_DDGCCVersion"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement Gcc_Version_Id_8() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl07_PassengerInformationControl_DDGCCVersion"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement Gcc_Version_Id_9() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl08_PassengerInformationControl_DDGCCVersion"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			
            //*****************************************Nationality *************************************************************************
			
			public static WebElement select_Nationality_1() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl00_PassengerInformationControl_DDNationality"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			
			public static WebElement select_Nationality_2() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl01_PassengerInformationControl_DDNationality"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement select_Nationality_3() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl02_PassengerInformationControl_DDNationality"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement select_Nationality_4() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl03_PassengerInformationControl_DDNationality"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement select_Nationality_5() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl04_PassengerInformationControl_DDNationality"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement select_Nationality_6() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl05_PassengerInformationControl_DDNationality"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement select_Nationality_7() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl06_PassengerInformationControl_DDNationality"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement select_Nationality_8() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl07_PassengerInformationControl_DDNationality"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement select_Nationality_9() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl08_PassengerInformationControl_DDNationality"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			
            //*****************************************Gender  *************************************************************************
			
			public static WebElement select_Gender_1() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl00_PassengerInformationControl_RBLGender"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement select_Gender_2() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl01_PassengerInformationControl_RBLGender"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement select_Gender_3() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl02_PassengerInformationControl_RBLGender"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement select_Gender_4() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl03_PassengerInformationControl_RBLGender"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement select_Gender_5() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl04_PassengerInformationControl_RBLGender"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement select_Gender_6() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl05_PassengerInformationControl_RBLGender"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement select_Gender_7() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl06_PassengerInformationControl_RBLGender"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement select_Gender_8() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl07_PassengerInformationControl_RBLGender"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement select_Gender_9() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl08_PassengerInformationControl_RBLGender"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			
            //*****************************************DATE PICKER ICON  *************************************************************************
			
			public static WebElement date_Icon_1() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl00_PassengerInformationControl_timePickerDOB_ctl02"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement date_Icon_2() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl01_PassengerInformationControl_timePickerDOB_ctl02"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement date_Icon_3() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl02_PassengerInformationControl_timePickerDOB_ctl02"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement date_Icon_4() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl03_PassengerInformationControl_timePickerDOB_ctl02"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement date_Icon_5() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl04_PassengerInformationControl_timePickerDOB_ctl02"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement date_Icon_6() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl05_PassengerInformationControl_timePickerDOB_ctl02"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement date_Icon_7() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl06_PassengerInformationControl_timePickerDOB_ctl02"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement date_Icon_8() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl07_PassengerInformationControl_timePickerDOB_ctl02"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement date_Icon_9() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_RePassengers_ctl08_PassengerInformationControl_timePickerDOB_ctl02"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			
			//***********************Select Month for All Passengers*****************************
			public static WebElement select_Month() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.xpath("//select[@class='form-control datetime-ui-datepicker-month input-width-20']"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			
			//***********************Select Year for All Passengers*****************************
			public static WebElement select_Year() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.xpath("//select[@class='form-control datetime-ui-datepicker-year input-width-20']"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			//***********************Select Date for All Passengers*****************************
			public static WebElement select_Date(String DD) throws Exception {
				element = null;
				try {
					element = driver.findElement(By.xpath("//a[@class='datetime-ui-state-default'][contains(text(),'"+DD+"')]"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			
			public static WebElement select_Mobile_Number_CountryCode() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_TxtMobile_txt1st"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			
			public static WebElement enter_Mobile_Number() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_TxtMobile_txt2nd"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement enter_email() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_TxtEmail_txtEmailInput"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement enter_Confirm_email() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ReservationContent_UC_ResStep3_UC_TxtEmailConfirm_txtEmailInput"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			
			public static WebElement btn_Continue_Passengers() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ToPassengersInfo_btn"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			
			public static WebElement chk_Agree() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("CheckAgreement_chk"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			
			public static WebElement btn_Continue_Ticket() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("p_lt_ctl02_pageplaceholder_p_lt_ctl01_ReservationContainer_ToStep5_btn"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			
			public static WebElement select_cardType_CC() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.xpath("//span[contains(text(),'Payment by credit card')]"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement select_cardType_SA() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.xpath("//span[contains(text(),'Payment by SADAD')]"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			
			public static WebElement master_Card_IMG() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.xpath("//img[@name='MasterCard']"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			
			public static WebElement visa_Card_IMG() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.xpath("//img[@name='Visa']"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			
			public static WebElement txt_card_number() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("CardNumber"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			public static WebElement txt_exp_month() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("CardMonth"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			
			public static WebElement txt_exp_year() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("CardYear"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			
			public static WebElement txt_Cvv_Number() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("Securecode"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			
			public static WebElement purchase_amount() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("purchase_amount"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			
			public static WebElement Paybutton() throws Exception {
				element = null;
				try {
					element = driver.findElement(By.id("Paybutton"));
					} catch (Exception e) {
					throw (e);
				}
				return element;
			}
			
			
			
			
}
