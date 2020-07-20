package stepdefinitions;



import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

import java.util.concurrent.TimeUnit;

import org.apache.http.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Loginpage {
	ArrayList<String> properties = new ArrayList<String>(10);
	ArrayList<String> prod_properties = new ArrayList<String>(10);

	@Given("^import all elements$")
	public void import_all_elements() throws Throwable {
		// Import Name elements
		Nameprop = new Properties();
		FileInputStream fisName = new FileInputStream(new File("name.properties").getAbsolutePath());
		System.out.println(fisName);
		Nameprop.load(fisName);
		/// Import xPath elements
		Xpathprop = new Properties();
		FileInputStream fisXpath = new FileInputStream(new File("xpath.properties").getAbsolutePath());
		Xpathprop.load(fisXpath);
		/// Import cssSelector elements
		Cssprop = new Properties();
		FileInputStream fisCss = new FileInputStream(new File("cssSelector.properties").getAbsolutePath());

		Cssprop.load(fisCss);
		/// Import id elements
		Idprop = new Properties();
		FileInputStream fisId = new FileInputStream(new File("id.properties").getAbsolutePath());
		Idprop.load(fisId);
		/// Constants
		Constantsprop = new Properties();
		FileInputStream fisConstant = new FileInputStream(new File("constants.properties").getAbsolutePath());
		Constantsprop.load(fisConstant);
	}

	@Given("^open browser with \"([^\"]*)\"$")
	public void open_browser_with_option(String browserOption) throws Throwable {

		String driverPath = SystemUtils.IS_OS_WINDOWS ? "drivers/chromedriver.exe" : "drivers/chromedriver";
		System.setProperty("webdriver.chrome.driver", driverPath);
		System.out.println(driverPath);
		ChromeOptions options = new ChromeOptions();
//		options.setAcceptInsecureCerts(true);
//		options.setHeadless(true);
//		options.addArguments("--no-sandbox");
//		options.addArguments("--disable-dev-shm-usage");
//		options.addArguments("window-size=1200,1100");

		driver = new EventFiringWebDriver(new ChromeDriver(options));

	}

	@Given("^navigate page \"([^\"]*)\"$")
	public void navigate_page_something(String pageUrl) throws Throwable {
		driver.navigate().to(Constantsprop.getProperty(pageUrl));
	}

	@Given("^click element$")
	public void click_element() throws Throwable {
		Thread.sleep(500);
		webElement.click();
	}

	@Given("^write text element \"([^\"]*)\"$")
	public void write_text_element_something(String text) throws Throwable {

		Thread.sleep(500);
		webElement.sendKeys(text);

	}

	@Given("^wait until the \"([^\"]*)\"$")
	public void wait_until_the_something(String option) throws Throwable {
		if (option == "visibility") {
			wait.until(ExpectedConditions.visibilityOf(webElement));
		} else if (option == "click") {
			wait.until(ExpectedConditions.elementToBeClickable(webElement));
		} else if (option == "seconds") {
			Thread.sleep(1000);
		}

	}

	@Given("^find element \"([^\"]*)\"$")
	public void find_element_something(String element) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 100);

		if (Nameprop.getProperty(element) != null) {
			webElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.name(Nameprop.getProperty(element))));
			System.out.println(By.name(Nameprop.getProperty(element)));
			webElement = driver.findElement(By.name(Nameprop.getProperty(element)));
		} else if (Cssprop.getProperty(element) != null) {
			webElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Cssprop.getProperty(element))));
			webElement = driver.findElement(By.cssSelector(Cssprop.getProperty(element)));
		} else if (Idprop.getProperty(element) != null) {
//						webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Idprop.getProperty(element))));
			webElement = driver.findElement(By.id(Idprop.getProperty(element)));

		} else if (Xpathprop.getProperty(element) != null) {
			webElement = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpathprop.getProperty(element))));
			webElement = driver.findElement(By.xpath(Xpathprop.getProperty(element)));
		}
	}

	@When("^assert element \"([^\"]*)\"$")
	public void assert_element_something(String message) throws Throwable {
		String control = webElement.getText();
		Assert.assertEquals(control, message);
	}

	@And("^assert pageContains \"([^\"]*)\"$")
	public void assert_pagecontains_something(String message) throws Throwable {

		Assert.assertTrue(driver.getPageSource().contains(message));

	}


	@Then("^browser close$")
	public void browser_close() throws Throwable {

		driver.close();
	}

	

}
