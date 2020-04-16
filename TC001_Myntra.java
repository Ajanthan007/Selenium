package selenium_90days;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class TC001_Myntra {

	public static void main(String[] args) throws InterruptedException {
	
		//1) Open https://www.myntra.com/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.myntra.com/");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//2) Mouse over on WOMEN (Actions -> moveToElement
		Thread.sleep(3000);
		WebElement elementHover = driver.findElementByXPath("//a[text()='Women']");
	    Actions builder = new Actions(driver);	
	    builder.moveToElement(elementHover).perform();
	    
		//3) Click Jackets & Coats
	    Thread.sleep(3000);
	    driver.findElementByLinkText("Jackets & Coats").click();
	    
		//4) Find the total count of item (top) -> getText -> String
		String items = driver.findElementByXPath("//span[@class='title-count']").getText();
		int itemcount = Integer.parseInt(items.replaceAll("\\D", ""));
		
		//5 Validate the sum of categories count matches
		String jacketCnt = driver.findElementByXPath("(//span[@class='categories-num'])[1]").getText();
		String coatCnt = driver.findElementByXPath("(//span[@class='categories-num'])[2]").getText();
		jacketCnt =  jacketCnt.replaceAll("\\D", "");
		coatCnt =  coatCnt.replaceAll("\\D", "");
		int totalCnt = (Integer.parseInt(jacketCnt) + Integer.parseInt(coatCnt));
		if(itemcount == totalCnt)
			System.out.println("The total count matches");
		else
			System.out.println("The total count does not match");	
		
		//6) Check Coats
	     driver.findElementByClassName("common-checkboxIndicator").click();
	     
		//7) Click + More option under BRAND
	     driver.findElementByClassName("brand-more").click();
	     
		//8) Type MANGO and click checkbox
	     driver.findElementByClassName("FilterDirectory-searchInput").sendKeys("Mango");
	     driver.findElementByXPath("//label[@class=' common-customCheckbox']//div[1]").click();
	     
		//9) Close the pop-up x
	     driver.findElementByXPath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']").click();
	     Thread.sleep(3000);
	     
		//10) Confirm all the Coats are of brand MANGO
	     List<WebElement> coatList = driver.findElementsByXPath("//h3[@class='product-brand']");
			int count = 0;
			System.out.println("The number of coats are "+coatList.size());
			for (WebElement webElement : coatList) {
				if(!webElement.getText().equalsIgnoreCase("MANGO"))
					count++;
			}
				if(count > 0)
					System.out.println("The are brands other than MANGO in the List");
				else
					System.out.println("All the Coats belongs to Brand MANGO");
			   /* findElements (brand) -> List<WebElement> 
			    foreach -> getText of each brand 
			    compare > if(condition)*/
				
		//11) Sort by Better Discount
				WebElement sortBy = driver.findElementByClassName("sort-sortBy");
				System.out.println(sortBy.getText());
				Actions sortByOptns = new Actions(driver);
				sortByOptns.moveToElement(sortBy).perform();
				driver.findElementByXPath("//label[text()='Better Discount']").click();
				Thread.sleep(2000);	
				
		//12) Find the price of first displayed item
		/*
		 * findElements (price) -> List<WebElement> get(0) -> WebElement 	-> getText ->
		 * String -> int
		 */
				List<WebElement> priceList = driver.findElementsByXPath("//span[@class='product-discountedPrice']");
				String fistItemPrice = priceList.get(0).getText().replaceAll("\\D", "");
				System.out.println("The Price of the first displayed itm is:" +fistItemPrice);
				
		//13) Mouse over on size of the first item
				builder.moveToElement(driver.findElementByXPath("(//div[@class='product-productMetaInfo'])[1]")).perform();		
				
		//14) Click on WishList Now
				driver.findElementByXPath("//span[text()='wishlist now']").click();
				Thread.sleep(4000);
				
		//15) Close Browser	
				driver.close();
		
		
		
		
		
	}

}
