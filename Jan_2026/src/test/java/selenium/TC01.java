package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		RemoteWebDriver driver=new ChromeDriver();
		driver.get("https://www.google.com");
		
		WebElement search=driver.findElement(By.id("APjFqb"));
		search.sendKeys("Tst methods");
		search.submit();
		
		System.out.println("Title is:"+driver.getTitle());
		driver.close();

	}

}
