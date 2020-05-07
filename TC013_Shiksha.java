package selenium_90days;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC013_Shiksha {

	public static void main(String[] args) throws InterruptedException {
		
 //1) Go to https://studyabroad.shiksha.com/
 System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
 ChromeOptions options = new ChromeOptions();
 options.addArguments("--disable-notifications");
 ChromeDriver driver = new ChromeDriver(options);
 driver.get("https://studyabroad.shiksha.com/");
 driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
 driver.manage().window().maximize();	
 WebDriverWait wait = new WebDriverWait(driver, 15);
 
//2) Mouse over on Colleges and click MS in Computer Science &Engg under MS Colleges 
wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//label[@for='sm3'])[1]")));
driver.findElement(By.xpath("(//label[@for='sm3'])[1]")).click();
driver.findElementByXPath("//a[text()='MS in Computer Science &Engg']").click();

//To Handle Cookie
try {
	driver.findElementByXPath("//a[@class='cookAgr-btn']").click();
} catch (Exception e) {
	e.printStackTrace();
}

//3)Select GRE under Exam Accepted and Score 300 & Below
driver.findElementByXPath("//label[@for='exam-0']").click();
Thread.sleep(3000);
WebElement ddGRE = driver.findElementByXPath("(//select[@class='score-select-field'])[1]");
Select scoreGRE = new Select(ddGRE);
scoreGRE.selectByVisibleText("300 & below");

//4) Max 10 Lakhs under 1st year Total fees, USA under countries 
Thread.sleep(3000);
driver.findElementByXPath("//p[text()='Max 10 Lakhs']").click();
Thread.sleep(3000);
driver.findElementByXPath("//div[@id='locationFilterScrollbar']/div[2]/div[1]/ul[1]/li[4]/label[1]/span[1]").click();

// 5) Select Sort By: Low to high 1st year total fees
Thread.sleep(3000);
WebElement ddsort = driver.findElementByXPath("//select[@id='categorySorter']");
Select sort = new Select(ddsort);
sort.selectByVisibleText("Low to high 1st year total fees");

//6) Click Add to compare of the College having least fees with Public University, Scholarship and Accomadation facilities
//getting list of colleges
List<WebElement> college = driver.findElementsByXPath("//div[@class='tuple-detail']");
List<Double> feesList = new ArrayList<Double>();

//To check Public University, Scholarship and Accomadation facilities for all colleges listed using for loop
for(int i=1; i<=college.size(); i++)
{
	String univChck = driver.findElementByXPath("(//p[text()='Public university']//span)["+i+"]").getAttribute("class");
	String schlrChck = driver.findElementByXPath("(//p[text()='Scholarship']//span)["+i+"]").getAttribute("class");  
	String accmdChck = driver.findElementByXPath("(//p[text()='Accommodation']//span)["+i+"]").getAttribute("class");
	
	if(univChck.equalsIgnoreCase("tick-mark") && schlrChck.equalsIgnoreCase("tick-mark") && accmdChck.equalsIgnoreCase("tick-mark"))
	{
		String feesall = driver.findElementByXPath("(//strong[text()=' 1st Year Total Fees']//following-sibling::p)["+i+"]").getText();
		System.out.println(feesall);
		double fees = Double.parseDouble(feesall.replaceAll("[\\s+a-zA-Z ]", ""));
		feesList.add(fees);
	}			
}
	
Thread.sleep(3000);	
Collections.sort(feesList);	

//Fetching the least fee and selecting that college 
Double leastFee = feesList.get(0);
System.out.println("College with least fees is : " + leastFee);
driver.findElementByXPath("//p[contains(text(),'"+leastFee+"')]/ancestor::div[@class='tuple-box']//span[@class='common-sprite']").click();

//7) Select the first college under Compare with similar colleges
Thread.sleep(3000);
driver.findElementByXPath("(//ul[@class='sticky-suggestion-list']//a)[1]").click();

// 8) Click on Compare College>
driver.findElementByXPath("//strong[text()='Compare Colleges >']").click();

//9) Select When to Study as 2021
driver.findElementByXPath("//label[@for='year1']//span").click();

// 10) Select Preferred Countries as USA
driver.findElementByXPath("//div[@class='sp-frm selectCountryField signup-fld invalid ']").click();
Thread.sleep(2000);
driver.findElementByXPath("//strong[text()='Top countries']/parent::li/following-sibling::li//label[contains(@for,'USA')]").click();
driver.findElementByXPath("//a[@class='ok-btn']").click();

//11) Select Level of Study as Masters
driver.findElementByXPath("//label//strong[text()='Masters']//preceding::span[@class='common-sprite'][1]").click();
Thread.sleep(5000);

//12) Select Preferred Course as MS
driver.findElementByXPath("//div[text()='Preferred Course']").click();
Thread.sleep(2000);
driver.findElementByXPath("//li[text()='MS']").click();

//13) Select Specialization as "Computer Science & Engineering"  
driver.findElementByXPath("//div[text()='Preferred Specialisations']").click();
Thread.sleep(2000);
driver.findElementByXPath("//li[text()='Computer Science & Engineering']").click();

//14) Click on Sign Up
driver.findElementByXPath("//a[@id='signup']").click();

//15) Print all the warning messages displayed on the screen for missed mandatory fields
List<WebElement> errorElement = driver.findElementsByXPath("//div[contains(@id,'error')]//div[contains(text(),'Please')]");

System.out.println("\nError messages displayed: ");
for (WebElement eachElement : errorElement) 
{
	String error = eachElement.getText(); 
	if(error.length()>0) 
	{
		System.out.println(error);
    } 
}
}
}
