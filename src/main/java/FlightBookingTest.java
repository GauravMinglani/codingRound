import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sun.javafx.PlatformUtil;

public class FlightBookingTest {

	WebDriver driver;
	WebElement element;
	WebDriverWait wait;

	@BeforeSuite(alwaysRun = true)
	public void setupSuite() {
		driver = new ChromeDriver();
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

	@BeforeMethod(description = "maximize and open the window")
	public void open() {
		driver.manage().window().maximize();
		driver.get("https://www.cleartrip.com/");
	}

	@Test
	public void testThatResultsAppearForAOneWayJourney() {
		
		// setDriverPath();
		wait = new WebDriverWait(driver, 20);
		//waitFor(2000);
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("OneWay")));
		System.out.println(element);
		//driver.findElement(By.id("OneWay")).click();
		element.click();

		driver.findElement(By.id("FromTag")).clear();
		driver.findElement(By.id("FromTag")).sendKeys("Chennai");

		// wait for the auto complete options to appear for the origin

		//waitFor(2000);
		//element = wait.until(ExpectedConditions.visibilityOfAllElements(By.id("ui-id-1").id("li")));
		
        List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
//        originOptions.get(0).click();
		for (WebElement webElement : originOptions) {
			if (webElement.getText().equals("Chennai, IN - Chennai Airport (MAA)")) {
				element = webElement;
				break;
			}
		}
		if (element != null) {
			element.click();
		}

		driver.findElement(By.id("ToTag")).clear();
		driver.findElement(By.id("ToTag")).sendKeys("Delhi");

		// wait for the auto complete options to appear for the destination

		//waitFor(2000);
		// select the first item from the destination auto complete list
        List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
//        destinationOptions.get(0).click();
		for (WebElement webElement : destinationOptions) {
			if (webElement.getText().equals("New Delhi, IN - Indira Gandhi Airport (DEL)")) {
				element = webElement;
				break;
			}
		}
		if (element != null) {
			element.click();
		}

		driver.findElement(By.cssSelector("input[id='DepartDate']")).click();
		driver.findElement(By.cssSelector("a[class='ui-state-default ui-state-highlight ui-state-active ']")).click();
//		driver.findElement(By.cssSelector("a[class='ui-state-default ui-state-highlight ui-state-active ']")).click();

		// all fields filled in. Now click on search
		driver.findElement(By.cssSelector("input[id='SearchBtn']")).click();

		waitFor(5000);
		// verify that result appears for the provided journey search
		
		Assert.assertTrue(isElementPresent(By.className("searchSummary")));

		// close the browser
		// driver.quit();

	}

	private void waitFor(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	@BeforeTest(description = "set type of driver which needs to be intialized")
	private void setDriverPath() {
		if (PlatformUtil.isMac()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver");
		}
		if (PlatformUtil.isWindows()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		}
		if (PlatformUtil.isLinux()) {
			System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
		}
	}

}
