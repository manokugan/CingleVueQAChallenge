package com.cinglevue.sample.tests.googleTests;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.cinglevue.sample.Helper.ScreenshotHelper;
import com.cinglevue.sample.pageObjects.GooglePageObjects;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author Manoujitha.K
 * 
 */
public class GoogleSearchTest extends GooglePageObjects {

	public WebDriver driver;
	Properties urlProperty;

	@Before
	public void OpenBrowser() {
		driver = new FirefoxDriver();
		urlProperty = readPropertyFile();
		driver.get(urlProperty.getProperty("url"));

	}

	@Given("^Google Page is Loaded$")
	public void Google_Page_is_Loaded() throws Throwable {
		/* Verify the loaded page is google home page */
		Assert.assertEquals(
				"The page title should equal Google at the start of the test.",
				"Google", driver.getTitle());

	}

	@When("^Search String Test$")
	public void Search_String_Test() throws Throwable {
		enterSearchText(driver);
		clickSearchButton(driver);

	}

	@Then("^returned result is more than (\\d+)$")
	public void returned_result_is_more_than(long expectedResult)
			throws Throwable {
		Long originalResult = getResultsStats(driver);
		/* Verify whether the Search Result has more than 1,000,000,000 */
		// Pattern digitPattern = Pattern.compile("\\d{10}");
		// Assert.assertTrue(digitPattern.matcher(originalResult).matches());
		System.out.println("ExpectedResult :" + expectedResult + "\noriginal :"
				+ originalResult);

		int test = originalResult.compareTo(expectedResult);
		boolean answer;
		if (test > 0)
			answer = true;
		else
			answer = false;

		Assert.assertTrue("Search Result is more than 1 000,000,000", answer);
		
	}

	@After
	public void clearSession() throws IOException {
		ScreenshotHelper.saveScreenshot("screenshot.png", driver);
		driver.close();
	}

	/**
	 * @return Method to read data from the properties file
	 */
	private Properties readPropertyFile() {

		Properties prop = new Properties();
		InputStream input = null;
		try {

			input = new FileInputStream(
					"src/main/resources/properties/config.properties");

			// load a properties file
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		return prop;
	}

}
