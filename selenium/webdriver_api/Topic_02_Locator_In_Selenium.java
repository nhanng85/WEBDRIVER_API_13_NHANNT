package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Locator_In_Selenium {
	WebDriver driver;

	//Open FF and maximize it
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	        // input
			// type="email"
			// title ="Email Address"
			// class = "input-text required-entry validate-email"
			// id ="email"
			// value=""
			// name="login[username]"
			// spellcheck="false"
			// autocorrect="off"
			// autocapitalize="off"
			
			// tagname [atttribute= 'value']
			// input[id='email']
			
			//thẻ của nó là gì
			// thuộc tính của nó co được selenium support hay ko?
			// cái giá trị của thuộc tính đó có duy nhất trong page hay ko?
			// ID | CLASSNAME | NAME | LINKTEXT \ PARTIAL LINKTEXT | TAGNAME | XPATH | CSS SELECTOR
			// Thao tác với field email address
	@Test
	//Open live guru web
	public void TC_01_() {
		driver.get("http://live.demoguru99.com");
		driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
		driver.findElement(By.xpath("//a[@title='Log In']")).click();
		//((WebElement) driver.findElements(By.id("email"))).sendKeys("hehe");
		//driver.findElement(By.id("email")).sendKeys("hehe");
		//driver.findElement(By.name("login[username]")).sendKeys("hehe");
		
		
	}

//	@Test
//	public void TC_02_() {
//		driver.get("http://live.demoguru99.com");
//		driver.findElement(By.cssSelector("a[class='skip-link skip-account']")).click();
//		driver.findElement(By.cssSelector("a[href ='http://live.demoguru99.com/index.php/tv.html'")).click();
//	}

	@Test
	public void TC_03_() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}