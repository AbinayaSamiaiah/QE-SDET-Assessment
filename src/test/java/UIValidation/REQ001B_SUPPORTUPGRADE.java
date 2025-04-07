package UIValidation;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class REQ001B_SUPPORTUPGRADE {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final String CHROME_DRIVER_PATH = "D:/Assessment/chromedriver-win64/chromedriver.exe";
    private static final String BASE_URL = "https://reqres.in/";
    private static final int DEFAULT_TIMEOUT_SECONDS = 10;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
        driver.get(BASE_URL);
    }

    @Test
    public void testSupportButton() {
        By supportButtonLocator = By.xpath("//a[contains(text(),'Support')]");
        supportButtonTest("support", supportButtonLocator);
    }

    @Test
    public void testSupportOptions() {
        By supportOptionsLocator = By.xpath("//form[(@class='breathe')]/div/label");
        supportOptions(supportOptionsLocator);
    }

    @Test
    public void testUpgrade() {
        upgrade("Ace. Pro features coming real soon. Stick your email in here to be notified and help build our pro tier.", "abiallanashmi@gmail.com");
    }

    private void supportButtonTest(String keywordInUrl, By locator) {
        try {
            WebElement supportButton = wait.until(ExpectedConditions.elementToBeClickable(locator));
            supportButton.click();
            wait.until(ExpectedConditions.urlContains(keywordInUrl));

            String supportUrl = driver.getCurrentUrl();
            System.out.println("Current URL is: " + supportUrl);

            String pageTitle = driver.getTitle();
            System.out.println("Page Title is: " + pageTitle);

            boolean isSupportButtonAvailable = supportUrl.contains(keywordInUrl);
            Assert.assertTrue(isSupportButtonAvailable, "Support page is not loaded correctly");
            System.out.println("Support page is loaded");

        } catch (Exception e) {
            System.err.println("Error in supportButtonTest: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage()); // Fail the test
        }
    }

    private void supportOptions(By locator) {
        try {
            List<WebElement> supportOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            System.out.println("\nRadio options:");

            for (WebElement option : supportOptions) {
                String optionText = option.getText();
                if (!optionText.isEmpty()) {
                    System.out.println(optionText);
                } else {
                    System.out.println("Options are not available");
                }
            }
        } catch (Exception e) {
            System.err.println("Error in supportOptions: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage()); // Fail the test
        }
    }

    private void upgrade(String expectedMessage, String emailId) {
        try {
            By upgradeLocator = By.xpath("//section/button[(@id='trigger-pro')]");
            WebElement upgradeButton = wait.until(ExpectedConditions.elementToBeClickable(upgradeLocator));
            Assert.assertTrue(upgradeButton.isDisplayed(), upgradeButton.getText() + " option is not available");
            System.out.println("\n" + upgradeButton.getText() + " option is available");

            upgradeButton.click();
            Thread.sleep(1000);

            WebElement upgradeDetailElement = upgradeButton.findElement(By.xpath("//section/div/h3"));
            String actualMessage = upgradeDetailElement.getText();

            Assert.assertEquals(actualMessage, expectedMessage, "Incorrect upgrade message displayed");
            System.out.println("Actual Message: " + actualMessage + "\nExpected Message: " + expectedMessage + "\nCorrect upgrade message is displayed");

            WebElement emailLocator = upgradeButton.findElement(By.xpath("//input[contains(@id,'mce-EMAIL')]"));
            emailLocator.sendKeys(emailId);

            WebElement subscribeLocator = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@id,'mc-embedded-subscribe')]")));
            subscribeLocator.click();

            // Navigate to new tab
            Set<String> windowHandles = driver.getWindowHandles();
            Iterator<String> iterator = windowHandles.iterator();
            iterator.next(); // Original window
            String newTab = iterator.next();
            driver.switchTo().window(newTab);

            boolean isSubscribed = wait.until(ExpectedConditions.urlContains("benhowdle"));
            Assert.assertTrue(isSubscribed, "Subscription link not sent to email");
            System.out.println("Subscription link sent to email id");

        } catch (Exception e) {
            System.err.println("Error in upgrade: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage()); // Fail the test
        }
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed.");
        }
        System.out.println("Execution is completed");
    }
    
    /*public static void main(String[] args) {
    	REQ001B_SUPPORTUPGRADE obj = new REQ001B_SUPPORTUPGRADE();
    	obj.setup();
    	obj.testSupportButton();
    	obj.testSupportOptions();
    	obj.testUpgrade();
    	obj.teardown();
	} */
}
