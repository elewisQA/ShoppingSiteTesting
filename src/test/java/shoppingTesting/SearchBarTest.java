package shoppingTesting;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchBarTest {

	private ChromeDriver driver;
	WebDriverWait wait;
	private String url = "http://automationpractice.com/index.php";
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		// Don't show the browser
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(true);
		this.driver = new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1366, 768));
	}
	
	@Test
	public void searchBarTest() throws InterruptedException {
		driver.get(url);
		
		// Search for 'dress'
		WebElement searchBar = driver.findElement(By.id("search_query_top"));
		searchBar.sendKeys("dress");
		searchBar.submit();
		
		wait = new WebDriverWait(driver, 5);
		
		// Check some product(s) found in the search
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='product-container']"));
		
		// Assert the list size is greater than 0 as that means some products were returned in the search
		assertTrue(products.size() > 0);
	}
	
	@After
	public void tearDown() {
		//driver.close();
		driver.quit();
	}
}
