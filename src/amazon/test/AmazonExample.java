package amazon.test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonExample {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		WebElement searchElement = driver.findElement(By.id("twotabsearchtextbox"));
		searchElement.sendKeys("iPhone12");
		searchElement.sendKeys(Keys.ENTER);

		// scroll to the search result view
		WebElement searchResultsElmnt = driver.findElement(By.xpath("//*[text()='Did you mean ']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView();", searchResultsElmnt);

		// Find iphone search results
		List<WebElement> resultsTextElements = driver.findElements(
				By.xpath("//div[@data-component-type='s-search-result']//span[contains(text(),'Apple iPhone 12')]"));
		List<WebElement> resultsPriceElements = driver.findElements(By.xpath(
				"//div[@data-component-type='s-search-result']//span[contains(text(),'Apple iPhone 12')]//following::span[@class='a-price-whole']"));

		System.out.println(resultsTextElements.size());
		for (int i = 0; i < resultsTextElements.size(); i++) {
			System.out.println(resultsTextElements.get(i).getText() + ":" + resultsPriceElements.get(i).getText());
		}

	}

}
