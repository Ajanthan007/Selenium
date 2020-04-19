package selenium_90days;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TC004_HP {

	public static void main(String[] args) throws InterruptedException {

 //1) Go to https://store.hp.com/in-en/
 System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
 ChromeOptions options = new ChromeOptions();
 options.addArguments("--disable-notifications");
 ChromeDriver driver = new ChromeDriver(options);
 driver.get("https://store.hp.com/in-en/");
 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
 driver.manage().window().maximize();		
 
 //To handle Pop-up
 
 //2) Mouse over on Laptops menu and click on Pavilion
 WebElement eleLap = driver.findElementByLinkText("Laptops");
 Actions builder = new Actions(driver);
 builder.moveToElement(eleLap).perform();
 driver.findElementByXPath("(//span[text()='Pavilion'])[1]").click();
 
 //To Handle promotions
		/*
		 * try { driver.
		 * findElementByXPath("//div[@class='inside_closeButton fonticon icon-hclose']")
		 * .click(); } catch (Exception e) { e.printStackTrace(); }
		 */
 
//To handle cookies
		
		  try 
		  {
			  driver.findElementByXPath("//button[@class='optanon-alert-box-close banner-close-button']" ).click(); 
		  } 
		  catch (Exception e) 
		  {
			  e.printStackTrace();
		  }
		 
 
 //3)Under SHOPPING OPTIONS -->Processor -->Select Intel Core i7  
 driver.findElementByXPath("(//span[text()='Processor'])[2]").click();
 driver.findElementByXPath("//span[text()='Intel Core i7']").click();
 Thread.sleep(3000);
 
 // 4) Hard Drive Capacity -->More than 1TB  
 driver.findElementByXPath("//span[text()='More than 1 TB']").click();
 
 //5) Select Sort By: Price: Low to High 
 Thread.sleep(2000);
 WebElement elesort = driver.findElementByXPath("//select[@id='sorter']");
 Select dd = new Select(elesort);
 dd.selectByVisibleText("Price : Low to High");
 
 //6) Print the First resulting Product Name and Price 
 String proName = driver.findElementByXPath("(//a[@class='product-item-link'])[1]").getText();  
 String proRate = driver.findElementByXPath("(//span[@class='price-wrapper '])[1]").getText().replaceAll("[^0-9]", "");
 System.out.println("Select product is " + proName + " and price is " + proRate);
 
 Thread.sleep(4000);
 try {
	driver.findElementByXPath("//div[@class='inside_closeButton fonticon icon-hclose']").click();
	 } 
 catch (Exception e) 
 {
	 e.printStackTrace(); 
 }
 
 //7) Click on Add to Cart  
 driver.findElementByXPath("(//button[@title='Add To Cart'])[1]").click();
 Thread.sleep(3000);
 
// 8) Click on Shopping Cart icon --> Click on View and Edit Cart
 driver.findElementByXPath("//a[@class='action showcart']").click();  
 driver.findElementByXPath("//span[text()='View and edit cart']").click();
 
 //9) Check the Shipping Option --> Check availability at Pincode
 driver.findElementByXPath("//input[@name='pincode']").sendKeys("600053");
 driver.findElementByXPath("//button[text()='check']").click();
 
 boolean available = driver.findElementByXPath("//span[@class='available']").isDisplayed();
 if(available){
	 String deleivery = driver.findElementByXPath("//div[@class='estimate']").getText();
	 String pincode = driver.findElementByXPath("//span[@class='available']//following-sibling::span").getText().replaceAll("^[0-9]", ""); 
	 System.out.println(deleivery+" "+pincode);
	          }
 else
 {
	 System.out.println("Deleivery not available");
 }
 
 //10) Verify the order Total against the product price
 String proTotal = driver.findElementByXPath("(//td[@class='amount'])[3]").getText().replaceAll("[^0-9]", "");
 System.out.println("Order Total is " + proTotal);
 
 //11) Proceed to Checkout if Order Total and Product Price matches
 if(proTotal.equals(proRate)) {
	 System.out.println("Order Total and Initial Price Matched");
	 Thread.sleep(3000);
	 driver.findElementByXPath("(//span[text()='Proceed to Checkout'])[1]").click();	   
 }
 else {
	 System.out.println("Order Total and Initial Price is not Matched");	 
 }
	 
//12) Click on Place Order 
 Thread.sleep(3000);
 driver.findElementByXPath("(//span[text()='Place Order'])[4]").click();

 // 13) Capture the Error message and Print
 String errorMessage = driver.findElementByXPath("//div[@class='message notice']").getText();
 System.out.println("Error message: "+errorMessage);
	
//	14) Close Browser
	driver.close();


	}

}
