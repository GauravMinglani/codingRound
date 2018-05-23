import com.sun.javafx.PlatformUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HotelBookingTest {
	
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


//    WebDriver driver = new ChromeDriver();

//    @FindBy(xpath=".//*[@id='Home']/section/div/aside[1]/nav/ul[1]/li[2]/a[1]")
	//@FindBy(css = "a[href='/hotels']")
 //   WebElement hotelLink;
//	@FindBy(css = "a[title='Find hotels in destinations around the world']")
     
	@FindBy(name= "localsNav")
	WebElement local;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;

    @Test
    public void shouldBeAbleToSearchForHotels() {
//        setDriverPath();

    	//WebElement web=driver.findElement(By.xpath(".//*[@id='Home']/section/div/aside[1]/nav/ul[1]/li[2]/a[1]"));
    	
//    	WebElement web=driver.findElement(By.xpath("/html/body/section[2]/section/div/aside[1]/nav/ul[1]/li[2]/a[1]"));
    	
    	///html/body/section[2]/section/div/aside[1]/nav/ul[1]/li[2]/a[1]
//        driver.get("https://www.cleartrip.com/");
//    	System.out.println("value name"+web.getText());
    	System.out.println("FOund");
        /*hotelLink.click();*/
    	
    	local.click();

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
