import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sun.javafx.PlatformUtil;

public class HotelBookingTest {

	WebDriver driver;

	@BeforeSuite(alwaysRun = true)
	public void setupSuite() {
		driver = new ChromeDriver();
		 PageFactory.initElements(driver, this);
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

    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;

    @Test
    public void shouldBeAbleToSearchForHotels() {
    	
//        setDriverPath();

//        driver.get("https://www.cleartrip.com/");
        hotelLink.click();

        localityTextBox.sendKeys("Indiranagar, Bangalore");

        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
        searchButton.click();

//        driver.quit();

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
