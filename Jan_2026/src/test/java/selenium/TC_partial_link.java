package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_partial_link {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		RemoteWebDriver driver=new ChromeDriver();
		driver.get("https://tutorialsninja.com/demo/index.php?");
		WebElement myaacount=	driver.findElement(By.partialLinkText("Account"));
		if(myaacount.isDisplayed())
		{
			System.out.println("My account is displayed");
			myaacount.click();
		}
		else
		{
			System.out.println("My account is not displayed");
		}
	
	}
 
}