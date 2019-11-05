package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_03_Xpath_Part_I_Ex {
	WebDriver driver;
	String username = "mngr231005";
	String password = "duvabyq";

	//add comment to check slack subscribe
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@BeforeMethod(description = "chạy trước cho mỗi test bên dưới")
	public void beforeMethod() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']/div[4]/ul/li[@class='first']/a[@title='My Account']")).click();
	}
	@Test
	public void TC_01_LoginEmpty() {
		System.out.println("Login với thông tin empty");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String emailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(emailErrorMsg, "This is a required field.");
		String emailErrorPw = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(emailErrorMsg, "This is a required field.");
	}

	@Test
	public void TC_02_LoginInvalidEmailFormat() {
		System.out.println("Login với email format sai");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@1234.1234");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		String emailWrong = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(emailWrong, "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_03_PasswordLessThanSix() {
		System.out.println("Login với password dưới 6 ký tự");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		String passwordLess6 = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(passwordLess6, "Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_04_PasswordIncorrect() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		String passwordWrong = driver.findElement(By.xpath("//li[@class='error-msg']/ul/li/span")).getText();
		Assert.assertEquals(passwordWrong, "Invalid login or password.");
	}
	
	@Test
	public void TC_05_LoginSuccess() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation_13@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String msgSuccess = driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText();
		Assert.assertEquals(msgSuccess, "MY DASHBOARD");
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}