
package selenium_90days;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC011_SnapDeal {

public static void main(String[] args) throws InterruptedException {

 //1) Go to https://www.snapdeal.com/  
 System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
 ChromeOptions options = new ChromeOptions();
 options.addArguments("--disable-notifications");
 ChromeDriver driver = new ChromeDriver(options);
 driver.get("https://www.snapdeal.com/ ");
 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
 driver.manage().window().maximize();
 WebDriverWait wait = new WebDriverWait(driver, 30);
 
//‎2) Mouse over on Toys, Kids' Fashion & more and click on Toys  
 ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='catText'])[8]"));
 driver.findElement(By.xpath("(//span[@class='catText'])[8]")).click();
// WebElement toys = driver.findElementByXPath("//div[@id='leftNavMenuRevamp']/div[1]/div[2]/ul[1]/li[9]/a[1]/span[1]");
//Actions builder = new Actions(driver);
//builder.moveToElement(toys).perform();
 
 //3) Click Educational Toys in Toys & Games 
 Thread.sleep(2000);
 driver.findElementByXPath("//span[text()='Educational Toys']").click();
 
 //5) Click the offer as 40-50
 Thread.sleep(2000);
 driver.findElementByXPath("//label[@for='discount-40 - 50']").click();
 
 //‎4) Click the Customer Rating 4 star and Up  
 Thread.sleep(4000);
 driver.findElementByXPath("//label[@for='avgRating-4.0']").click();
 
 //6) Check the availability for the pincode
driver.findElementByXPath("(//input[@class='sd-input'])[2]").sendKeys("600053");	
driver.findElementByXPath("//button[@class='pincode-check']").click();

//7) Click the View of the first product   
ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//a[@data-position='0;35'])[1]"));
driver.findElementByXPath("(//div[contains(@class,'center quick-view-bar')])[1]").click();

//8) Click on View Details
driver.findElementByXPath("//a[@class=' btn btn-theme-secondary prodDetailBtn']").click();

//9) Capture the Price of the Product and Delivery Charge  
String pricetoy = driver.findElementByXPath("//span[@itemprop='price']").getText().replaceAll("\\D", "");
System.out.println("Product Price is : " +pricetoy);
String delchargetoy = driver.findElementByXPath("(//span[@class='availCharges'])[2]").getText().replaceAll("\\D", "");
System.out.println("Delivary charge : " +delchargetoy);

//10) Validate the You Pay amount matches the sum of (price+deliver charge)  
int ToatalPayToy = (Integer.parseInt(pricetoy) + Integer.parseInt(delchargetoy));
System.out.println("Product price inlcuding delivary charge is : " + ToatalPayToy);

//Add to cart
driver.findElementByXPath("//div[@id='add-cart-button-id']").click();

//11) Search for Sanitizer
driver.findElementByXPath("//input[@id='inputValEnter']").sendKeys("Sanitizer",Keys.ENTER);

//12) Click on Product "BioAyurveda Neem Power Hand Sanitizer" 
WebElement product2 = driver.findElementByXPath("(//p[text()='BioAyurveda Neem Power  Hand Sanitizer 500 mL Pack of 1'])[1]");
Actions builder3 = new Actions(driver);
builder3.moveToElement(product2).perform();
driver.findElementByXPath("(//div[contains(@class,'center quick-view-bar')])[1]").click();

Thread.sleep(2000);
driver.findElementByXPath("//a[@class=' btn btn-theme-secondary prodDetailBtn']").click();

//13) Capture the Price and Delivery Charge
String pricesani = driver.findElementByXPath("//span[@itemprop='price']").getText().replaceAll("\\D", "");
System.out.println("Product Price is : " +pricesani);
String delchargesani = driver.findElementByXPath("(//span[@class='availCharges'])[2]").getText().replaceAll("\\D", "");
System.out.println("Delivary charge : " +delchargesani);

int ToatalPaySani = (Integer.parseInt(pricesani) + Integer.parseInt(delchargesani));
System.out.println("Product price inlcuding delivary charge is : " + ToatalPaySani);

//14) Click on Add to Cart  
Thread.sleep(2000);
driver.findElementByXPath("(//div[@id='add-cart-button-id'])[1]").click();

//15) Click on Cart  
Thread.sleep(2000);
driver.findElementByXPath("//div[@class='cartInner']").click();

//16) Validate the Proceed to Pay matches the total amount of both the products  
int Total = ToatalPaySani + ToatalPayToy;
System.out.println("Sum of both Toy and Sanitizer is : " + Total);

Thread.sleep(3000);
String SumTotal = driver.findElementByXPath("//input[@class='btn btn-xl rippleWhite cart-button']").getAttribute("value");
System.out.println("Product Price is : " +SumTotal.substring(15));

String reTotal = SumTotal.replaceAll("[^0-9]", "");
int CartTotal = (Integer.parseInt(reTotal));

if(Total==CartTotal)
{
 System.out.println("Product value is matched");	
}
else	
{
 System.out.println("Product value is not matched");
}

//17) Close all the windows*/
driver.close();  	

	}

	private static By ByXPath(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
