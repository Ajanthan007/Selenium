package selenium_90days;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TC008_Pepperfry {

	public static void main(String[] args) throws InterruptedException, IOException {

  //1)  Go to https://www.pepperfry.com/
  System.setProperty("webdriver.chrome.driver", "D:\\TEST LEAF\\MavenProjrct\\chromedriver.exe");
  ChromeOptions options = new ChromeOptions();
  ChromeDriver driver = new ChromeDriver(options);
  driver.get("https://www.pepperfry.com/");
  driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
  driver.manage().window().maximize();	
  options.addArguments("--disable-notifications");
  WebDriverWait wait = new WebDriverWait(driver, 30);
  
  //To close Popup
  try {
	driver.findElementByXPath("//div[@class='popup-box gb-modal reg_modal']//a").click();
} catch (Exception e) {
	e.printStackTrace();
}
  
  //)2) Mouseover on Furniture and click Office Chairs under Chairs 
  WebElement furni = driver.findElementByXPath("//a[text()='Furniture']");
  Actions builder = new Actions(driver);
  builder.moveToElement(furni).perform();
  driver.findElementByXPath("//a[text()='Office Chairs']").click();
  
  //3) click Executive Chairs 
  driver.findElementByXPath("//h5[text()='Executive Chairs']").click();

  //4) Change the minimum Height as 50 in under Dimensions 
  Thread.sleep(3000);
  WebElement height = driver.findElementByXPath("(//input[@class='clipFilterDimensionHeightValue'])[1]");
  height.clear();
  height.sendKeys("50",Keys.ENTER);
  
  //5) Add "Poise Executive Chair in Black Colour" chair to Wishlist 
  Thread.sleep(3000);
  driver.findElementByXPath("//a[@data-productname='Poise Executive Chair in Black Colour']").click();
  
  //6) Mouseover on * Homeware and Click Pressure Cookers under Cookware
  WebElement homeware = driver.findElementByXPath("(//a[text()='Homeware'])[1]");
  Actions builder2 = new Actions(driver);
  builder2.moveToElement(homeware).perform();
  driver.findElementByXPath("//a[text()='Pressure Cookers']").click();
  
  //7) Select Prestige as Brand
  driver.findElementByXPath("//li[@data-search='Prestige']").click();
  
  // 8) Select Capacity as 1-3 Ltr   
  Thread.sleep(3000);
  driver.findElementByXPath("//li[@data-search='1 Ltr - 3 Ltr']").click();

  //9) Add "Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr" to Wishlist
  Thread.sleep(2000);
  driver.findElementByXPath("//a[@data-productname='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr']").click();

  //10) Verify the number of items in Wishlist
  String wishlist = driver.findElementByXPath("(//span[@class='count_alert'])[2]").getText();
  System.out.println("No of items in Wishlist is : " +wishlist);
   
  //11) Navigate to Wishlist
  driver.findElementByXPath("//a[@data-tooltip='Wishlist']").click();
  
  //12) Move PressureCooker only to Cart from Wishlist
  Thread.sleep(2000);
 driver.findElementByXPath("//a[@class='gridview pf-icon pf-icon-grid-view']").click();
 Thread.sleep(2000);
 driver.findElementByXPath("(//i[@class='addtocart_icon pf-icon pf-icon-cart1'])[2]").click();
	 
  // 13) Check for the availability for Pincode 600128
  driver.findElementByXPath("//input[@class='srvc_pin_text']").sendKeys("600053", Keys.ENTER);
  
  //14) Click Proceed to Pay Securely
  Thread.sleep(3000);
  driver.findElementByXPath("//div[@class='minicart_footer']").click();
  
  //15 Click Proceed to Pay 
  driver.findElementByXPath("(//a[text()='PLACE ORDER'])[1]").click();
  
  // 16) Capture the screenshot of the item under Order Item
  driver.findElementByXPath("//span[text()='ORDER SUMMARY']").click();  
  File src = driver.findElementByXPath("//div[@class='slick-list draggable']//li").getScreenshotAs(OutputType.FILE); 
  File dest = new File("./screens/PepperfryItem.jpg"); 
  FileUtils.copyFile(src, dest);
	
  //17) Close the browser
  //driver.quit();		 
	}

}
