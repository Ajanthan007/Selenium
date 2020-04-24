package selenium_90days;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class TC007_Honda {

	public static void main(String[] args) throws InterruptedException {

 //1)  Go to https://www.honda2wheelersindia.com/
 System.setProperty("webdriver.chrome.driver", "D:\\TEST LEAF\\MavenProjrct\\chromedriver.exe");
 ChromeOptions options = new ChromeOptions();
 options.addArguments("--disable-notifications");
 ChromeDriver driver = new ChromeDriver(options);
 driver.get("https://www.honda2wheelersindia.com/");
 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
 driver.manage().window().maximize();
 
 //To close notification
 driver.findElementByXPath("//button[@class='close']").click();
 
 //2) Click on scooters and click dio 
 driver.findElementByLinkText("Scooter").click();
 Thread.sleep(3000);
 driver.findElementByXPath("(//div[@class='item']//img)[4]").click(); 
 
 //3) Click on Specifications and mouseover on ENGINE
 driver.findElementByLinkText("Specifications").click();
 driver.findElementByLinkText("ENGINE").click(); 
 
 // 4) Get Displacement value
 Thread.sleep(3000);
 String displaDio = driver.findElementByXPath("//span[text()='Displacement']/following-sibling::span").getText();
 String str1 = displaDio.replaceAll("\\D", "").substring(0, 3);
 System.out.println("Dio displacement is : " + displaDio);
 
 //5) Go to Scooters and click Activa 125
 driver.findElementByLinkText("Scooter").click();
 Thread.sleep(3000);
 driver.findElementByXPath("(//div[@class='item']//img)[6]").click(); 
 
 //6) Click on Specifications and mouseover on ENGINE
 driver.findElementByLinkText("Specifications").click();
 driver.findElementByLinkText("ENGINE").click(); 
 
 //7) Get Displacement value
 Thread.sleep(3000);
 String displaActiva = driver.findElementByXPath("//span[text()='Displacement']/following-sibling::span").getText();
 String str2 = displaActiva.replaceAll("\\D", "");
	int dio = Integer.parseInt(str1);
	int activa = Integer.parseInt(str2);
 System.out.println("Activa125 displacement is : " + displaActiva);
 
 //8) Compare Displacement of Dio and Activa 125 and print the Scooter name having better Displacement.
 if (activa > dio) {
		System.out.println("Better displacement is Activa" + activa + "cc");
	} else {
		System.out.println("Better displacement is Dio" + dio + "cc");
	}
 
 //9)Click FAQ from Menu
 driver.findElementByLinkText("FAQ").click();
 
 //10) Click Activa 125 BS-VI under Browse By Product
 driver.findElementByLinkText("Activa 125 BS-VI").click();
 
 //11)Click Vehicle Price
 driver.findElementById("li6").click();
 
 // 12) Make sure Activa 125 BS-VI selected and click submit
 String Model = driver.findElementByXPath("//select[@id='ModelID6']").getText();
 System.out.println("Model autoselected is : " + Model);
 if(Model.contains("Activa 125 BS-VI"))
 {
   driver.findElementByXPath("//button[@onclick='validateformPriceMaster(6)']").click();
 }
 else
 {
   System.out.println("Model Name is wrong select the correct model again");	 
 }
 
// 13) click the price link
 driver.findElementByXPath("//table[@class='table table-bordered']//a[1]").click();

 // * 14) Go to the new Window and select the state as Tamil Nadu and city as Chennai
 Set<String> winSet = driver.getWindowHandles();
 List<String> winLis = new ArrayList<String>(winSet);
 driver.switchTo().window(winLis.get(1));
 
 WebElement dd1 = driver.findElementById("StateID");
 Select ddState = new Select(dd1);
 ddState.selectByVisibleText("Tamil Nadu");
 Thread.sleep(2000);
 WebElement dd2 = driver.findElementById("CityID");
 Select ddCity = new Select(dd2);
 ddCity.selectByVisibleText("Chennai");
 
 //15) Click Search
 driver.findElementByXPath("//button[@onclick='ShowProductPrice()']").click();
 
 //16) Print all the 3 models and their prices
		
		  String Model1 =  driver.findElementByXPath("//td[@rowspan='50']/following-sibling::td[1]").
		  getText(); String Price1 = driver.findElementByXPath("//td[@rowspan='50']/following-sibling::td[2]").getText().replaceAll("[^0-9]", ""); 
		  System.out.println("Model is : " + Model1+ " and ExShowroom Price is : " + Price1);
		  
		  String Model2 = driver.findElementByXPath(" (//table[@rules='all']//td)[4]").getText();
		  String Price2 = driver.findElementByXPath(" (//table[@rules='all']//td)[5]").getText().replaceAll("[^0-9]", "");
		  System.out.println("Model is : " + Model2 + " and ExShowroom Price is : " + Price2);
		  
		  String Model3 = driver.findElementByXPath(" (//table[@rules='all']//td)[6]").getText();
		  String Price3 = driver.findElementByXPath(" (//table[@rules='all']//td)[7]").getText().
		  replaceAll("[^0-9]", ""); System.out.println("Model is : " + Model3 + " and ExShowroom Price is : " + Price3);
		 
 
 //17) Close the Browser
 driver.quit();
	}

}
