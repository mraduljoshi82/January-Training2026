package selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_findelement {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		RemoteWebDriver driver=new ChromeDriver();
		driver.get("https://www.amazon.in");
		Thread.sleep(5000);
		List <WebElement> links=driver.findElements(By.tagName("a"));
		System.out.println(links.size());
		for(WebElement link: links) {
			System.out.println(link.getText());
		}

	}

}
