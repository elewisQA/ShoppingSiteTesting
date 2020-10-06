package shoppingTesting;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class purchaseTest {

	private ChromeDriver driver;
	WebDriverWait wait;
	private String url = "http://automationpractice.com/index.php";
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		// Don't show the browser
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(false);
		this.driver = new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1366, 768));
	}
	
	@Test
	public void checkoutTest() throws InterruptedException {
		driver.get(url);
		// Find Sign-in button and click
		driver.findElement(By.className("login")).click();
		
		// WAIT for email box to load
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		
		// Populate login fields
		driver.findElement(By.id("email")).sendKeys("elewis@academytrainee.com"); // My Email
		driver.findElement(By.id("passwd")).sendKeys("guest");
		driver.findElement(By.id("SubmitLogin")).click();
		
		
		// Search for 'dress'
		WebElement searchBar = driver.findElement(By.id("search_query_top"));
		searchBar.sendKeys("dress");
		searchBar.submit();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product-container")));
		
		// Find "add to cart" buttons of each product
		List<WebElement> products = driver.findElements(By.className("product-container"));
		System.out.println(products.size());
		products.get(0).click(); // Click first product
		
		// On product page, add to cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add_to_cart")));
		driver.findElement(By.id("add_to_cart")).click();
		
		// Proceed To Checkout button
		driver.findElement(By.xpath("//a[@href='http://automationpractice.com/index.php?controller=order\']")).click();
		
		// Checkout in Cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'button btn')]")));
		driver.findElement(By.xpath("//a[contains(@class,'button btn')]")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class,'button btn')]")));
		driver.findElement(By.xpath("//button[contains(@class,'button btn')]")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class,'button btn')]")));
		// Tick Agree
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		driver.findElement(By.xpath("//button[contains(@class,'button btn')]")).click();
		Thread.sleep(5500);
	}
	
	@After
	public void tearDown() {
		//driver.close();
		driver.quit();
	}
}
