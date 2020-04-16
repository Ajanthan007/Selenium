package selenium_90days;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class TC002_Nykaa {

	public static void main(String[] args) throws InterruptedException {
	
		//1) Go to https://www.nykaa.com/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.nykaa.com/");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//2) Mouseover on Brands and Mouseover on Popular.
		WebElement eleBrand = driver.findElementByXPath("(//li[@class='menu-dropdown-icon']//a)[1]");
		Actions builder = new Actions(driver);
		builder.moveToElement(eleBrand).perform();
		
		Thread.sleep(3000);
		WebElement elePopular = driver.findElementByXPath("//a[text()='Popular']");
		Actions builder2 = new Actions(driver);
		builder2.moveToElement(elePopular).perform();
		
		//3) Click L'Oreal Paris
		driver.findElementByXPath("(//li[@class='brand-logo menu-links']//img)[5]").click();
		
		//4) Go to the newly opened window and check the title contains L'Oreal Paris
		Set<String> winSet = driver.getWindowHandles();
		List<String> winLis = new ArrayList<String>(winSet);
		driver.switchTo().window(winLis.get(1));
		String title = driver.getTitle();
		System.out.println("New Window Title is :" +title);
		
		if(title.contains("L'Oreal Paris"))
			System.out.println("Window is validated");
		else 
			System.out.println("Window is wrong");
		
		//5) Click sort By and select customer top rated
		Thread.sleep(4000);
		driver.findElementByXPath("//i[@class='fa fa-angle-down']").click();
		driver.findElementByXPath("//span[text()='customer top rated']").click();
		
		//6)Click Category and click Shampoo
		Thread.sleep(3000);
		driver.findElementByXPath("//div[text()='Category']").click();
		driver.findElementByXPath("//span[text()='Shampoo (21)']").click();
		
		//7) check whether the Filter is applied with Shampoo
		String text = driver.findElementByXPath("//ul[@class='pull-left applied-filter-lists']//li[1]").getText();
		System.out.println(text);
		
		if(text.contains("Shampoo"))
			System.out.println("Filter is applied");
		else
			System.out.println("Filter is not applied");
		
		//8) Click on L'Oreal Paris Colour Protect Shampoo  
		Thread.sleep(3000);
		driver.findElementByXPath("(//div[@class='m-content__product-list__title'])[4]").click();
		
		//9) GO to the new window and select size as 175ml
		Set<String> winSet1 = driver.getWindowHandles();
		List<String> winLis1 = new ArrayList<String>(winSet1);
		driver.switchTo().window(winLis1.get(2));  
		Thread.sleep(3000);
		driver.findElementByXPath("//span[text()='175ml']").click(); 
		
		//10) Print the MRP of the product
		String textMRP = driver.findElementByXPath("(//div[@class='price-info'])[1]").getText();
		int MRP = Integer.parseInt(textMRP.replaceAll("\\D", ""));
		System.out.println("MRP of Product is :" + MRP);
		
		//11) Click on ADD to BAG
		driver.findElementByXPath("//*[@id='app']/div/div/div[1]/div[2]/div[2]/div/div[2]/div[2]/div[2]/div/div[3]/div/div[1]/div[1]/div/div/div/div/button").click();
		
		//12) Go to Shopping Bag
		driver.findElementByClassName("AddBagIcon").click();
		
		//13) Print the Grand Total amount
		String textTotal = driver.findElementByXPath("//div[@class='first-col']").getText();
		int Total = Integer.parseInt(textTotal.replaceAll("\\D", ""));
		System.out.println("Grand Total amount is :" + Total);
		
		//14) Click Proceed
		driver.findElementByClassName("second-col").click();  
	
		//15) Click on Continue as Guest
		driver.findElementByXPath("//*[@id=\'app\']/div/div/div[1]/div/div[2]/div/div/div/div[2]/div/div[2]/div/div/div/div/div[4]/button").click();  
		
		//16) Print the warning message (delay in shipment) 
		String warning = driver.findElementByClassName("message").getText();
		System.out.println("Warning message displayed is : " +warning);
		
		// 17) Close all windows
		driver.quit();
	}

}
