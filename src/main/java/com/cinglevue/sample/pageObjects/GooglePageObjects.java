package com.cinglevue.sample.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Manoujitha.K
 * 
 */
public class GooglePageObjects {

	WebElement searchBox;
	WebElement searchButton;
	WebElement resultStat;

	/**
	 * @param driver
	 *            Method to Enter the search text "TEST" in the search box
	 */
	public void enterSearchText(WebDriver driver) {
		searchBox = driver.findElement(By.name("q"));

		if (searchBox.isDisplayed()) {
			System.out.println("The searchbox is found");
			searchBox.click();
			searchBox.sendKeys("TEST");
		} else {
			System.out.println("The search Box is not found");
		}

	}

	/**
	 * @param driver
	 *            Method to click search button
	 */
	public void clickSearchButton(WebDriver driver) {
		searchButton = driver.findElement(By.cssSelector(".lsb"));

		if (searchButton.isDisplayed()) {
			searchButton.click();
		}
	}

	/**
	 * @param driver
	 * @return
	 * @throws Exception
	 *             Method to retrieve the result count from the page
	 */
	public long getResultsStats(WebDriver driver) throws Exception {
		Thread.sleep(5000);
		String resultStats = driver.findElement(By.cssSelector("#resultStats"))
				.getText();
		System.out.println("Results :  " + resultStats);

		return Long.parseLong(getTheResultCount(resultStats));

	}

	/**
	 * @param resultStat
	 *            Method to get only the result count from the String
	 * @return
	 */
	private String getTheResultCount(String resultStat) {
		String number1 = resultStat.substring(0, 26);
		number1 = number1.replaceAll("\\D+", "");
		System.out.println("Final Result Integer Only : " + number1);

		return number1;

	}

}
