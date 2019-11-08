package webdriver_api;

import java.util.Random;
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
	String firstName = "Selenium";
	String lastName = "Advanced";
	String validEmail = "automation" + randomNumber() + "@gmail.com";
	String validPassword = "123123";

	// add comment to check slack subscribe
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
	public void TC_05_CreateNewAccount() {
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(validEmail);
		System.out.println(validEmail);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(validPassword);
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(validPassword);
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
		String msgSuccess = driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText();
		Assert.assertEquals(msgSuccess, "MY DASHBOARD");

		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Hello, " + firstName + " " + lastName + "!']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(text(),'" + firstName + " " + lastName + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(.,'" + validEmail + "')]")).isDisplayed());
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();

	}

	@Test
	public void TC_06_LoginSuccess() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(validEmail);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(validPassword);
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String msgSuccess = driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText();
		Assert.assertEquals(msgSuccess, "MY DASHBOARD");
		// String msgHello = driver.findElement(By.xpath("//p[@class='hello']/strong")).getText();
		// System.out.println(msgHello);
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Hello, " + firstName + " " + lastName + "!']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(text(),'" + firstName + " " + lastName + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(.,'" + validEmail + "')]")).isDisplayed());
		// String msgName = driver.findElement(By.xpath("//div[@class='box-content']/p[contains(text(),'Testing')]")).getText();
		// System.out.println(msgName);
		// Assert.assertTrue(true, msgName);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(100000);
	}

}