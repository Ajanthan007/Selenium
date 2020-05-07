package selenium_90days;

import java.awt.RenderingHints.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC016_Ajio {
	
public static void main(String[] args) throws InterruptedException {	

//1)Go to https://www.ajio.com/  
System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
ChromeOptions options = new ChromeOptions();
options.addArguments("--disable-notifications");
ChromeDriver driver = new ChromeDriver(options);
driver.get("https://www.ajio.com/shop/sale");
driver.manage().window().maximize();	
driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
WebDriverWait wait = new WebDriverWait(driver, 30);		

//To Handle Popup
try {
	driver.findElementByXPath("//div[@class='ic-close-quickview']").click();
} catch (Exception e) {
	e.printStackTrace();
}

//2) Enter Bags in the Search field and Select Bags in Women Handbags
driver.findElementByXPath("//input[@name='searchVal']").sendKeys("Bags");
driver.findElementByXPath("//span[text()='Handbags in ']").click();

//3) Click on five grid and Select SORT BY as "What's New"
driver.findElementByXPath("//div[@class='five-grid']").click();
WebElement sort = driver.findElementByXPath("//div[@class='filter-dropdown']//select[1]");
Select dd = new Select(sort);
dd.selectByVisibleText("What's New");

//4) Enter Price Range Min as 2000 and Max as 5000
driver.findElementByXPath("//span[text()='price']").click();
driver.findElementById("minPrice").sendKeys("2000");
driver.findElementById("maxPrice").sendKeys("5000",Keys.ENTER);

//5) Click on the product "Puma Ferrari LS Shoulder Bag"
driver.findElementByXPath("//img[contains(@alt,'Ferrari LS Shoulder Bag')]").click();

//Move to nxt window
Set<String> winSet = driver.getWindowHandles();
List<String> winList = new ArrayList<String>(winSet);
driver.switchTo().window(winList.get(1));
Thread.sleep(3000);

//6) Verify the Coupon code for the price above 2690 is applicable for your
//product, if applicable the get the Coupon Code and Calculate the discount
//price for the coupon
String pricechk = driver.findElementByXPath("//div[@class='prod-sp']").getText().replaceAll("\\D", "");
int price = Integer.parseInt(pricechk);
System.out.println("Price of the product is : " + price);
int offer = 2690;

//Checking Offer is applicable
if(offer <= price)
{
  System.out.println("Offer is Applicable");	
}
else
{
  System.out.println("Offer is not Applicable");	
}

//Calculating Discount
String dischk = driver.findElementByXPath("//div[@class='promo-desc-block']//div[1]").getText().replaceAll("\\D", "");
int discount = Integer.parseInt(dischk);
System.out.println("Price of the product is : " + discount);

int disPrice = price - discount;
System.out.println("Discount Price should be : " + disPrice);

//7) Check the availability of the product for pincode 560043, print the expected delivery date if it is available
driver.findElementByXPath("//div[@class='edd-pincode-msg-container']").click();
driver.findElementByXPath("//input[@class='edd-pincode-modal-text']").sendKeys("682001");
driver.findElementByXPath("//button[text()='CONFIRM PINCODE']").click();

String delivery = driver.findElementByXPath("//ul[@class='edd-message-success-details']//span[1]").getText();
System.out.println("Expected Delivery date is : "+ delivery);

//8) Click on Other Informations under Product Details and Print the Customer Care address, phone and email
driver.findElementByXPath("//div[text()='Other information']").click();
String CareDetails = driver.findElementByXPath("(//span[@class='other-info'])[6]").getText();
System.out.println("Customer Care details is : " + CareDetails);

//9) Click on ADD TO BAG and then GO TO BAG
driver.findElementByXPath("//div[@class='btn-gold']").click();
Thread.sleep(5000);
driver.findElementByXPath("//div[@class='btn-cart']").click();

// 10) Check the Order Total before apply coupon
String spbc = driver.findElementByXPath("//span[@class='price-value bold-font']").getText().replaceAll("\\D", "");
double pbc = Double.parseDouble(spbc.replaceAll("\\D", ""));
System.out.println("Price before applying coupon is : " + pbc);

//Converting the previous int to double 
double priceDouble = (double)price; 
if (pbc == priceDouble)
{ 
 System.out.println("Order Total is correct.");
}
else
{ 
 System.out.println("Incorrect Order Total.");
 
//11) Enter Coupon Code and Click Apply 
driver.findElementById("EPIC").click(); 
driver.findElementByXPath("//button[text()='Apply']").click(); 
Thread.sleep(3000); 

//12) Verify the Coupon Savings amount(round off if it in decimal) under Order Summary and the
//matches the amount calculated in Product details 
//Verifying the savings in the checkout page 
String savingsAmountStr = driver.findElementByXPath("//span[@class='cart-total-saving-text']").getText();
savingsAmountStr = savingsAmountStr.replaceAll("[^0-9.]", ""); 
		
String[] amountSplit = savingsAmountStr.split(".", 2); 
		
double savingsDouble = Double.parseDouble(amountSplit[1]); 
		
// Rounding off the Savings Double and converting to int 
int roundOffSavings = (int)Math.round(savingsDouble); 
System.out.println(roundOffSavings);
		
System.out.println("Savings from the checkout page: " + savingsDouble);
		 
// Comparing the two Integer savings 
if (roundOffSavings == disPrice) 
{ 
System.out.println("Savings Amount matches.");
} else 
{ 
System.out.println("Savings Amount not matches."); 
}

//13) Click on Delete and Delete the item from Bag 
driver.findElementByClassName("delete-btn").click(); 
driver.findElementByXPath("//div[text()='DELETE']").click(); 
Thread.sleep(3000); 

//14) Close all the browsers
driver.quit();
}
}
}