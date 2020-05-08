package selenium_90days;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC017_MSAzure {

public static void main(String[] args) throws InterruptedException {

//1) Go to https://azure.microsoft.com/en-in/  
System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
ChromeOptions options = new ChromeOptions();
options.addArguments("--disable-notifications");
ChromeDriver driver = new ChromeDriver(options);
driver.get("https://azure.microsoft.com/en-in/");
driver.manage().window().maximize();	
driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
WebDriverWait wait = new WebDriverWait(driver, 30);		
Actions action = new Actions(driver);

DesiredCapabilities cap = new DesiredCapabilities();
cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
options.merge(cap);

JavascriptExecutor js = (JavascriptExecutor) driver;

//2) Click on Pricing  
driver.findElementByXPath("//a[@id='navigation-pricing']").click();

//3) Click on Pricing Calculator  
driver.findElementByXPath("(//a[@data-event='global-navigation-secondarynav-clicked-topmenu'])[2]").click();

//4) Click on Containers  
driver.findElementByXPath("//button[@value='containers']").click();

//5) Select Container Instances  ""
driver.findElementByXPath("(//span[text()='Container Instances'])[3]").click();

//6) Click on Container Instance Added View 
wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='new-module-loc']")));
driver.findElementByXPath("//a[@id='new-module-loc']").click();

//7) Select Region as "South India"  
WebElement region = driver.findElementByXPath("(//select[@name='region'])[1]");
Select dd = new Select(region);
dd.selectByVisibleText("South India");

//8) Set the Duration as 180000 seconds 
Thread.sleep(4000);
driver.findElement(By.xpath("(//input[@name='seconds'])[1]")).
sendKeys(Keys.chord(Keys.CONTROL, "a"), "180000",Keys.TAB);

//9) Select the Memory as 4GB  
WebElement memory = driver.findElementByXPath("(//select[@name='memory'])[1]");
Select dd2 = new Select(memory);
dd2.selectByVisibleText("4 GB");

//10) Enable SHOW DEV/TEST PRICING  
driver.findElementByXPath("//button[@id='devtest-toggler']").click();
Thread.sleep(3000);

//11) Select Indian Rupee as currency  
WebElement currency = driver.findElementByXPath("//select[@class='select currency-dropdown']");
Select dd3 = new Select(currency);
dd3.selectByValue("INR");

//12) Print the Estimated monthly price  
String price = driver.findElementByXPath("//section[@id='azure-calculator']/div[1]/div[2]/div[2]/div[1]/div[1]/"
		+ "section[1]/div[6]/div[1]/div[2]/div[2]/span[1]/span[1]").getText();
System.out.println("Estimated monthly price :" + price);

//13) Click on Export to download the estimate as excel  
driver.findElementByXPath("//button[@class='calculator-button button-transparent export-button']").click();
Thread.sleep(8000);

//14) Verify the downloded file in the local folder
File file = new File("C:\\Users\\sherin pc\\Downloads\\ExportedEstimate.xlsx");
if (file.exists()) {
	System.out.println("File downloaded successfully");
} else {
	System.out.println("File not found");
}
js.executeScript("window.scrollBy(700,0)");

//15) Navigate to Example Scenarios and Select CI/CD for Containers 
action.moveToElement(driver.findElementByXPath("//a[text()='Example Scenarios']")).click().build().perform();
driver.findElement(By.xpath("//button[@title='CI/CD for Containers']")).click();

//16) Click Add to Estimate 
driver.findElementByXPath("//button[text()='Add to estimate']").click();
Thread.sleep(5000);

//17) Change the Currency as Indian Rupee 
WebElement currency2 = driver.findElementByXPath("//select[@class='select currency-dropdown']");
Select dd4 = new Select(currency2);
dd4.selectByValue("INR");

//18) Enable SHOW DEV/TEST PRICING
driver.findElementByXPath("//button[@id='devtest-toggler']").click();
Thread.sleep(3000);

//19) Export the Estimate  
driver.findElementByXPath("//button[@class='calculator-button button-transparent export-button']").click();
Thread.sleep(10000);

//20) Verify the downloded file in the local folder   
File file1 = new File("C:\\Users\\sherin pc\\Downloads\\ExportedEstimate (1).xlsx");
if (file1.exists()) {
	System.out.println("File downloaded successfully");
} else {
	System.out.println("File not found");
}

}

}
