package selenium_90days;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC003_makemytrip {

	public static void main(String[] args) throws InterruptedException {
	//1) Go to https://www.makemytrip.com/
	System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--disable-notifications");
	ChromeDriver driver = new ChromeDriver(options);
	driver.get("https://www.makemytrip.com/");
	driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	driver.manage().window().maximize();	
	
	//2) Click Hotels
	driver.findElementByXPath("//a[@href='https://www.makemytrip.com/hotels/']//span[1]").click();
	
	//3) Enter city as Goa, and choose Goa, India  
	driver.findElementByXPath("//input[@id='city']").click();
	Thread.sleep(3000);
	driver.findElementByXPath("//input[@class='react-autosuggest__input react-autosuggest__input--open']").sendKeys("Goa");
	Thread.sleep(2000);
	driver.findElementByXPath("//p[contains(text(),'Goa, India')]").click();
	
	//4) Enter Check in date as Next month 15th (May 15) and Check out as start date+5
	driver.findElementById("checkin").click();
	driver.findElementByXPath("(//div[text()='15'])[2]").click();
	driver.findElementById("checkout").click();
	driver.findElementByXPath("(//div[text()='20'])[2]").click();
	
	//5) Click on ROOMS & GUESTS and click 2 Adults and one Children(age 12). Click Apply Button.
	driver.findElementById("guest").click();
	driver.findElementByXPath("(//li[text()='2'])[1]").click();
	driver.findElementByXPath("//li[@data-cy='children-1']").click();
	WebElement ele = driver.findElementByClassName("ageSelectBox");
	Select dd= new Select(ele);
	dd.selectByVisibleText("12");
	driver.findElementByXPath("//button[@data-cy='submitGuest']").click();
	
	// 6) Click Search button
	driver.findElementById("hsw_search_button").click();
	
	//7) Select locality as Baga
	driver.findElementByXPath("//div[@class='mmBackdrop wholeBlack']").click();
	driver.findElementByXPath("//label[text()='Baga']").click();
	
	// 8) Select 5 start in Star Category under Select Filters
	WebDriverWait wait = new WebDriverWait(driver,30);
	WebElement ele5Star = driver.findElementByXPath("//label[text()='5 Star']"); 
	Thread.sleep(1000);
	wait.until(ExpectedConditions.elementToBeClickable(ele5Star)); 
	ele5Star.click();
	
	//9) Click on the first resulting hotel and go to the new window
	driver.findElementByXPath("(//div[@class='makeFlex flexOne padding20 relative'])[1]").click();
	Set<String> winSet = driver.getWindowHandles();
	List<String> winLis = new ArrayList<String>(winSet);
	driver.switchTo().window(winLis.get(1));
	
	// 10) Print the Hotel Name
	String textHotel = driver.findElementByXPath("//h1[@class='txtHeading font22 latoBlack blackText']").getText();
	System.out.println("Hotel selected is :" + textHotel);
	
	// 11) Click MORE OPTIONS link and Select 3Months plan and close
	driver.findElementByXPath("//span[text()='MORE OPTIONS']").click();
	driver.findElementByXPath("(//span[text()='SELECT'])[1]").click();
	driver.findElementByXPath("//span[@class='close']").click();
	
	//12) Click on BOOK THIS NOW
		
		
	//13) Print the Total Payable amount
	System.out.println("Total Payable amount: " + driver.findElementById("revpg_total_payable_amt").getText());
	
	//14) Close the browser
	driver.quit();
	}

}
