package seleniumtest;

import org.openqa.selenium.edge.EdgeDriver;

import org.junit.Assert;

import org.junit.Test;
import org.openqa.selenium.*;

public class TestLogIn {
	
	@Test
	public static void loginTest() {
		System.setProperty("webdriver.edge.driver","Driver/msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		
		driver.get("http://localhost:1122/MyPlaces/index.jsp");
		driver.findElement(By.xpath("//*[@id=\"username\"]"));
		driver.findElement(By.xpath("//*[@id=\"password\"]"));
		
		WebElement username = driver.findElement(By.xpath("//*[@id=\\\"username\\\"]"));
		WebElement password = driver.findElement(By.xpath("//*[@id=\\\"password\\\"]"));
		
		
		driver.findElement(By.xpath("//*[@id=\"login\"]/form/input"));
		WebElement login = driver.findElement(By.xpath("//*[@id=\\\"login\\\"]/form/input"));
		
		username.sendKeys("doc"); 
		password.sendKeys("doc"); 
		login.click();
		
		String actualUrl = "http://localhost:1122/MyPlaces/utente.jsp?lang=it";
		String expectedUrl = driver.getCurrentUrl();
		
		Assert.assertEquals(expectedUrl,actualUrl);
	}
	
}
