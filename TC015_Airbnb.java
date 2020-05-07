package selenium_90days;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC015_Airbnb {

	public static void main(String[] args) throws InterruptedException {
		
//1) Go to https://www.airbnb.co.in/  
System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
ChromeOptions options = new ChromeOptions();
options.addArguments("--disable-notifications");
ChromeDriver driver = new ChromeDriver(options);
driver.get("https://www.airbnb.co.in/ ");
driver.manage().window().maximize();	
driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
WebDriverWait wait = new WebDriverWait(driver, 30);		
		
//To Handle Cookie
try {
	driver.findElementByXPath("//button[@aria-label='OK']").click();
} catch (Exception e) {
	e.printStackTrace();
}

//2) Type Coorg in location and Select Coorg, Karnataka  
driver.findElementByXPath("//input[@name='query']").sendKeys("Coorg");
driver.findElementByXPath("//div[text()='Coorg, Karnataka']").click();

//3) Select the Start Date as June 1st and End Date as June 5th  
driver.findElementByXPath("(//div[text()='1'])[3]").click();
driver.findElementByXPath("(//div[text()='5'])[3]").click();

//To validate dates
String Dates = driver.findElementByXPath("(//button[@class='_esmga25'])[1]").getText();
System.out.println("Dates selected is : " + Dates);

//4) Select guests as 6 adults, 3 child and Click Search  
driver.findElementByXPath("//div[text()='Add guests']").click();

for (int i = 1; i <= 6 ; i++) {
	driver.findElementByXPath("(//button[@aria-label='increase value'])[1]").click();
}

for (int i = 1; i <= 3 ; i++) {
	driver.findElementByXPath("(//button[@aria-label='increase value'])[2]").click();
}
driver.findElementByXPath("//button[@type='submit']").click();

//5) Click Cancellation flexibility and enable the filter and Save  
driver.findElementByXPath("(//span[text()='Cancellation flexibility'])[1]").click();
driver.findElementByXPath("//button[@id='filterItem-switch-flexible_cancellation-true']").click();
driver.findElementByXPath("//button[text()='Save']").click();

//6) Select Type of Place as Entire Place and Save  
driver.findElementByXPath("(//span[text()='Type of place'])[1]").click();
driver.findElementByXPath("(//span[@class='_167krry'])[1]").click();
driver.findElementByXPath("//button[text()='Save']").click();

//7) Set Min price as 3000 and max price as 5000    Price
driver.findElement(By.xpath("(//span[text()='Price'])[1]")).click();
Thread.sleep(2000);
driver.findElement(By.xpath("//input[@id='price_filter_min']")).sendKeys(Keys.chord(Keys.CONTROL,"a"),"3000",Keys.TAB);
Thread.sleep(4000);
driver.findElement(By.xpath("//input[@id='price_filter_max']")).sendKeys("5000");
driver.findElementByXPath("//button[text()='Save']").click();

//8) Click More Filters and set 3 Bedrooms and 3 Bathrooms  
driver.findElementByXPath("(//span[text()='More filters'])[1]").click();
for (int i = 1; i <= 3 ; i++) {
	driver.findElementByXPath("(//button[@aria-label='increase value'])[2]").click();
}

for (int i = 1; i <= 3 ; i++) {
	driver.findElementByXPath("(//button[@aria-label='increase value'])[3]").click();
}

//9) Check the Amenities with Kitchen  
driver.findElementByXPath("(//span[@class='_167krry'])[1]").click();  
//Facilities with Free parking on premisses
driver.findElementByXPath("(//span[@class='_167krry'])[4]").click();
//Property as House 
driver.findElementByXPath("(//span[@class='_167krry'])[5]").click(); 
//Host Language as English
driver.findElementByXPath("(//span[@class='_167krry'])[10]").click();
//click on Stays only when stays available 
driver.findElementByXPath("//button[text()='Show 1 stay']").click();


		//10) Click Prahari Nivas, the complete house  
		//11) Click on "Show all * amenities"  
		//12) Print all the Not included amenities  
		//13) Verify the Check-in date, Check-out date and Guests  
		//14) Read all the Sleeping arrangements and Print  
		//15) Close all the browsers
	}
}
