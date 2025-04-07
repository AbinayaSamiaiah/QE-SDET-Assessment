package UIValidation;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class REQ001A {

	private static WebDriver driver;
    private static final String CHROME_DRIVER_PATH = "D:/Assessment/chromedriver-win64/chromedriver.exe";
    private static final String BASE_URL = "https://reqres.in/";
    private static final By ENDPOINTS_LIST = By.xpath("//div[contains(@class,'endpoints')]/ul/li");

    @BeforeClass
    public void browsersetup() {
        try {
            System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(BASE_URL);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        } catch (Exception e) {
            System.err.println("Error setting up browser: " + e.getMessage());
            Assert.fail("Browser setup failed: " + e.getMessage());
        }
    }

    public WebDriver getdriver() {
        return driver;
    }

    @Test
    public void verifyDifferentRequestTypesAndEndpoints() {
        List<WebElement> endpoints = driver.findElements(ENDPOINTS_LIST);
        System.out.println("Request-Type\tRequestDescription\tEndpoint");

        String previousIdentifier = "";
        boolean areValuesDifferent = true;

        for (WebElement endpoint : endpoints) {
            String httpMethod = endpoint.getAttribute("data-http").toUpperCase();
            String description = endpoint.getText();
            WebElement linkElement = endpoint.findElement(By.tagName("a"));
            String endpointUrl = linkElement.getAttribute("href");

            System.out.println(httpMethod + "\t" + description + "\t" + endpointUrl);

            String currentIdentifier = httpMethod + description + endpointUrl;
            if (previousIdentifier.equals(currentIdentifier)) {
                areValuesDifferent = false;
            }
            previousIdentifier = currentIdentifier;
        }

        System.out.println("\nResult: " + (areValuesDifferent ? "Different request types and endpoints are present" : "Some request types and endpoints are the same"));
        Assert.assertTrue(areValuesDifferent, "Some request types and endpoints are the same.");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed.");
        }
        System.out.println("Execution Completed");
    }
    
 /*public static void main(String[] args) {
		REQ001A obj = new REQ001A();
		obj.browsersetup();
		obj.verifyDifferentRequestTypesAndEndpoints();
		obj.teardown();
	} */
    
}