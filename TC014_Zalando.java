package selenium_90days;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC014_Zalando {

public static void main(String[] args) throws InterruptedException {

	 //1) Go to https://www.zalando.com/
	 System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	 ChromeOptions options = new ChromeOptions();
	 options.addArguments("--disable-notifications");
	 ChromeDriver driver = new ChromeDriver(options);
	 driver.get("https://www.zalando.com/");
	 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	 WebDriverWait wait = new WebDriverWait(driver, 30);
	 //WebDriverWait wait = new WebDriverWait(driver, 15);
	 
	 //2) Get the Alert text and print it 
	 Alert alertzalando = driver.switchTo().alert();
	 String textalert = alertzalando.getText();
	 System.out.println("Alert text is : " + textalert);
	  
	 //3) Close the Alert box and click on Zalando.uk 
	 Thread.sleep(3000);
	 alertzalando.accept();
	 
	 driver.manage().window().maximize();	
	 driver.findElementByXPath("//a[@class='nav_link nav_link-gb']").click();
	 
	 //To handle pop-up
	 try {
		driver.findElementByXPath("//button[@class='uc-btn uc-btn-primary']").click();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	 //4) Click Women--> Clothing and click Coat
	 driver.findElementByXPath("(//span[text()='Women'])[1]").click();
	 WebElement clothing = driver.findElementByXPath("//span[text()='Clothing']");
	 Actions builder = new Actions(driver);
	 builder.moveToElement(clothing).perform();
	 driver.findElementByXPath("//span[text()='Coats']").click();

	 //5) Choose Material as cotton (100%) and Length as thigh-length
	 driver.findElementByXPath("//span[text()='Material']").click();
	 
	 Thread.sleep(5000);
	 
	 //To handle pop-up
	driver.findElementByXPath("//button[@class='uc-btn uc-btn-primary']").click();
	
	 driver.findElementByXPath("//span[text()='cotton (100%)']").click();
	 driver.findElementByXPath("//button[text()='Save']").click();
	 Thread.sleep(3000);
	 driver.findElementByXPath("//span[text()='Length']").click();
	 driver.findElementByXPath("//span[text()='thigh-length']").click();
	 driver.findElementByXPath("//button[text()='Save']").click();
	 Thread.sleep(3000);
	 
	 //6) Click on Q/S designed by MANTEL - Parka coat 
	 driver.findElementByXPath("//div[text()='MANTEL - Parka - navy']").click();
     
	 //7) Check the availability for Color as Olive and Size as 'M'  
	 driver.findElementByXPath("(//img[@alt='olive'])[2]").click();
	 driver.findElementByXPath("//button[@id='picker-trigger']").click();
	 driver.findElementByXPath("//span[text()='M']").click();
	 
	 //8) If the previous preference is not available, check availability for Color Navy and Size 'M'
	 if(driver.findElementByXPath("//h2[text()='Out of stock']").isDisplayed())
	 {
		System.out.println("Olive is Out of Stock"); 
		driver.findElementByXPath("(//img[@alt='navy'])[2]").click();
		driver.findElementByXPath("//button[@id='picker-trigger']").click();
		driver.findElementByXPath("//span[text()='M']").click(); 
	 }
	 else
	 {
		 System.out.println("Olive is not Out of Stock proceed further");
	 }
	 
	 //9) Add to bag only if Standard Delivery is free
	 if(driver.findElementByXPath("//span[text()='Free']").isDisplayed())
	 {
		System.out.println("Standard Delivery is free so Add to Bag"); 
		driver.findElementByXPath("//span[text()='Add to bag']").click();
	 }
	 else
	 {
		 System.out.println("Standard Delivery charge is applied");
	 }
	 
	 //10) Mouse over on Your Bag and Click on "Go to Bag"
	 WebElement bag = driver.findElementByXPath("//span[text()='Your bag']");
	 Actions builder2 = new Actions(driver);
	 builder2.moveToElement(bag).perform();   
	 driver.findElementByXPath("//div[text()='Go to bag']").click();
	
	 //11) Capture the Estimated Deliver Date and print 
	 String deliverydate = driver.findElementByXPath("//div[@data-id='delivery-estimation']").getText();
	 System.out.println("Delivery date is : " + deliverydate);
	 
	 //12) Mouse over on FREE DELIVERY & RETURNS*, get the tool tip text and print  
	 String returns = driver.findElementByXPath("(//span[@class='z-navicat-header-uspBar_message-split_styled'])[2]").getAttribute("title");
	 System.out.println("Delivery date is : " + returns);
	
	// Click on FREE DElIVERY & RETURNS*
	driver.findElement(By.xpath("//a[text()='Free delivery & returns*']")).click();
	Thread.sleep(5000);
	 
	 //13) Click on Start chat in the Start chat and go to the new window 
	driver.findElement(By.xpath("(//button[@class='faq-dx-button'])[1]")).click();
	Set<String> winSet = driver.getWindowHandles();
	List<String> winList = new ArrayList<String>(winSet);
	driver.switchTo().window(winList.get(1));
	Thread.sleep(3000);
	  
	//14) Enter you first name and a dummy email and click Start Chat 
	driver.findElement(By.id("prechat_customer_name_id")).sendKeys("Rocky");
	driver.findElement(By.id("prechat_customer_email_id")).sendKeys("mymail@mail.com");
	driver.findElement(By.id("prechat_submit")).click();
	Thread.sleep(5000);	
	
	// Type Hi, click Send and print thr reply message and close the chat window.
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("liveAgentChatTextArea"))).sendKeys("Hello");
			driver.findElement(By.xpath("//button[text()='Send']")).click();
			Thread.sleep(3000);
			System.out.println(driver.findElement(By.xpath("(//span[@class='messageText'])[last()]")).getText());
			//driver.findElement(By.xpath("//button[text()='End Chat']")).click();
			driver.close();
			driver.quit();
		

	}
}
