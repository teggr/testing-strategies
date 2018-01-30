package com.robintegg.blog.e2e;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.base.Function;

import io.github.bonigarcia.wdm.WebDriverManager;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("mocky-temperature")
public class TestingStrategiesE2ETest {

	@Autowired
	private WebDriver webDriver;

	@LocalServerPort
	private int port;

	@BeforeClass
	public static void setupTest() {
		WebDriverManager.chromedriver().setup();
	}

	@Test
	public void shouldReturnServicesWith200Ok() {

		// given

		// when
		webDriver.navigate().to("http://localhost:" + port + "/index.html");

		// then
		FluentWait<WebDriver> wait = new FluentWait<>(webDriver).ignoring(NoSuchElementException.class)
				.pollingEvery(1, TimeUnit.SECONDS).withTimeout(5, TimeUnit.SECONDS);

		WebElement getButton = wait.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver wd) {
				WebElement findElement = wd.findElement(By.className("get"));

				return findElement;
			}
		});

		assertEquals("Get me the weather...", getButton.getText());

		getButton.click();

		WebElement summaryTable = wait.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver wd) {
				WebElement findElement = wd.findElement(By.className("summary"));
				if (!findElement.isDisplayed()) {
					throw new NoSuchElementException("Not visible " + toString());
				}
				return findElement;
			}
		});

		assertTrue(summaryTable.getText().contains("SUNNY"));

	}

	@org.springframework.boot.test.context.TestConfiguration
	public static class TestConfiguration {

		@Bean
		public WebDriver webDriver() {
			return new ChromeDriver();
		}

	}

}
