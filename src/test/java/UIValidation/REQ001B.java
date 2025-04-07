package UIValidation;

import java.time.Duration;
import java.util.List;
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

public class REQ001B{

    private WebDriver driver;
    private static final String CHROME_DRIVER_PATH = "D:/Assessment/chromedriver-win64/chromedriver.exe";
    private static final String BASE_URL = "https://reqres.in/";
    private static final By ENDPOINTS_LIST = By.xpath("//div[contains(@class,'endpoints')]/ul/li");
    private static final By REQUEST_LINK = By.xpath("//a[contains(@data-key,'request-output-link')]");
    private static final By RESPONSE_CODE = By.xpath("//span[contains(@data-key,'response-code')]");
    private static final By RESPONSE_BODY = By.xpath("//pre[contains(@data-key,'output-response')]");
    private static final int DEFAULT_TIMEOUT_SECONDS = 100;

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
    public void verifyEndpointDetails() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
        List<WebElement> endpoints = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ENDPOINTS_LIST));

        for (WebElement endpoint : endpoints) {
            String httpMethod = endpoint.getAttribute("data-http").toUpperCase();
            String endpointText = endpoint.getText();
            String fullText = httpMethod + " " + endpointText;
            System.out.println(fullText);

            Assert.assertTrue(wait.until(ExpectedConditions.elementToBeClickable(endpoint)) != null, fullText + " is not clickable");
            processEndpoint(endpoint, fullText, wait);
        }
    }

    private void processEndpoint(WebElement endpoint, String fullText, WebDriverWait wait) {
        try {
            endpoint.click();
            long sleepDuration = fullText.contains("DELAYED") ? 5000 : 2000;
            Thread.sleep(sleepDuration);

            WebElement requestElement = wait.until(ExpectedConditions.visibilityOfElementLocated(REQUEST_LINK));
            String requestLink = requestElement.getAttribute("href");

            WebElement responseCodeElement = driver.findElement(RESPONSE_CODE);
            String responseCodeValue = responseCodeElement.getText();

            WebElement responseBodyElement = wait.until(ExpectedConditions.presenceOfElementLocated(RESPONSE_BODY));
            String responseBodyJson = responseBodyElement.getText();

            System.out.println("Request: " + requestLink + "\n" +
                               "Response code: " + responseCodeValue + "\n" +
                               "Response Body(JSON): " + responseBodyJson + "\n");

        } catch (Exception e) {
            System.err.println("Error processing endpoint '" + fullText + "': " + e.getMessage());
            Assert.fail("Error processing endpoint '" + fullText + "': " + e.getMessage());
        }
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed.");
        }
        System.out.println("Execution Completed");
    }
    
    /*
    public static void main(String[] args) {
    	REQ001B obj = new REQ001B();
    	obj.browsersetup();
    	obj.verifyEndpointDetails();
    	obj.teardown();
	}  */
}
