package selenium_90days;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import com.google.common.collect.Ordering;

public class TC012_Carwale {
	
	public static RemoteWebDriver driver;
	
	public String pickWindow() { 
		Set<String> winSet = driver.getWindowHandles(); 
		List<String> winList = new ArrayList<String>(winSet); 
		int size = winList.size(); 
		return winList.get(size-1);
	}

	public static void main(String[] args) throws InterruptedException {
		

  TC012_Carwale hand = new TC012_Carwale(); 
		
//1)Go to https://www.carwale.com/
System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
ChromeOptions options = new ChromeOptions();
options.addArguments("--disable-notifications");
ChromeDriver driver = new ChromeDriver(options);
driver.get("https://www.carwale.com/");
driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
driver.manage().window().maximize();		

//Actions act = new Actions(driver); 
JavascriptExecutor js = (JavascriptExecutor) driver;

//2) Click on Used
driver.findElementByXPath("//li[@data-tabs='usedCars']").click();

// 3) Select the City as Chennai
driver.findElementById("usedCarsList").sendKeys("Chennai");
Thread.sleep(1500);
driver.findElementByXPath("//a[@cityname='chennai,tamilnadu']").click();

// 4) Select budget min (8L) and max(12L) and Click Search 		 
driver.findElementById("minInput").sendKeys("8");
driver.findElementById("maxInput").sendKeys("12",Keys.ENTER);  
driver.findElementByXPath("//button[@id='btnFindCar']").click(); 

// 5) Select Cars with Photos under Only Show Cars With 
driver.findElementByXPath("//li[@name='CarsWithPhotos']").click(); 

//To close Tips
driver.findElementByXPath("(//a[@class='nomoreTips cur-pointer'])[1]").click(); 
Thread.sleep(3000);

// 6) Select Manufacturer as "Hyundai" --> Creta
Thread.sleep(2000);
js.executeScript("window.scrollBy(0, 250)");
driver.findElementByXPath("//li[@data-manufacture-en='Hyundai']").click(); 
Thread.sleep(1500);
driver.findElementByXPath("//span[text()='Creta']").click(); 

// 7) Select Fuel Type as Petrol
js.executeScript("window.scrollBy(0, 400)");
driver.findElementByXPath("//div[@name='fuel']").click();
Thread.sleep(1500);
driver.findElementByXPath("//li[@name='Petrol']").click();   

//8) Select Best Match as "KM: Low to High"
WebElement dd = driver.findElementByXPath("//select[@id='sort']");
Select sort = new Select(dd);
sort.selectByVisibleText("KM: Low to High");

// 9) Validate the Cars are listed with KMs Low to High
List<WebElement> kmElement = driver.findElementsByXPath("//span[@class='slkms vehicle-data__item']"); 
int size = kmElement.size(); 
System.out.println("Size is : " +size);

List<Integer> kmList = new ArrayList<Integer>(); 
for (int i = 0; i < size; i++) 
{
	String text = kmElement.get(i).getText(); 
	System.out.println(text); 
	int kmValues = Integer.parseInt(text.replaceAll("\\D", "")); 
	kmList.add(kmValues); 	
}

	// Validating the Integer List is sorted or not 
	boolean sorted = Ordering.natural().isOrdered(kmList); 

if (sorted == true) {
	System.out.println("Cars are sorted correctly.");
} else { 
	System.out.println("Sorting is incorrect.");
}

// Sorting the List 
Collections.sort(kmList); 
System.out.println("*****************************"); 
System.out.println(kmList); 

Integer lowestKm = kmList.get(0); 

// Converting this integer value to String to pass it in the below Xpath 
String str = Integer.toString(lowestKm);  
str = new StringBuilder(str).insert(str.length()-3, ",").toString(); 
System.out.println(str);

//10)Add the least KM ran car to Wishlist 
// Selecting the lowest KMs car  
Thread.sleep(3000); 
WebElement favCar = driver.findElementByXPath("(//span[contains(text(),'"+str+"')]//"
		+ "ancestor::div[@class='stock-detail']//span[@class='shortlist-icon--inactive shortlist'])[1]");
js.executeScript("window.scrollBy(0, 800)");
//js.executeScript("window.scrollIntoView(true)", favCar);  
favCar.click();

//11) Go to Wishlist and Click on More Details 
// Navigating to Favourites page and viewing the details 
driver.findElementByXPath("//li[@data-cat='UsedCarSearch']").click(); 
driver.findElementByLinkText("More details »").click(); 

driver.switchTo().window(hand.pickWindow()); 
Thread.sleep(5000); 

//12) Print all the details under Overview in the Same way as displayed
js.executeScript("window.scrollBy(0, 500)");
List<WebElement> eleAttribute = driver.findElementsByXPath("//div[@id='overview']//li//div[1]"); 
List<WebElement> eleValue = driver.findElementsByXPath("//div[@id='overview']//li//div[2]");

Map<String, String> carDetails = new LinkedHashMap<String, String>(); 

for (int i=0; i < eleAttribute.size(); i++) { 
	String attributeStr = eleAttribute.get(i).getText(); 
	String valueStr = eleValue.get(i).getText(); 
	carDetails.put(attributeStr, valueStr); 
} 

System.out.println("\nDetails of the Favourite Car");
for (Map.Entry<String, String> eachEntry : carDetails.entrySet()) { 
	System.out.println(eachEntry.getKey() + " ----------- " + eachEntry.getValue());
}

//13) Close the browser
driver.quit();
	}

}
