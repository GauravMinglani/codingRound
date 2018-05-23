import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SignInTest {
	
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

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

//        setDriverPath();

//        driver.get("https://www.cleartrip.com/");
//        waitFor(2000);

        driver.findElement(By.linkText("Your trips")).click();
        driver.findElement(By.id("SignIn")).click();
        
//        driver.switchTo().frame("modal_window");
        driver.switchTo().frame(driver.findElement(By.id("modal_window")));
        
        waitFor(5000);
        driver.findElement(By.id("signInButton")).click();

        String errors1 = driver.findElement(By.id("errors1")).getText();
        driver.switchTo().defaultContent();
        driver.findElement(By.id("close")).click();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
//        driver.quit();
    }

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
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
