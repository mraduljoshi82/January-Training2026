package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_is_select {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		RemoteWebDriver driver=new ChromeDriver();
		driver.get("https://tutorialsninja.com/demo/index.php?");
		WebElement myaccount=	driver.findElement(By.partialLinkText("Account"));
		 myaccount.click();
		driver.findElement(By.partialLinkText("Register")).click();
		boolean subs=driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/form/fieldset[3]/div[1]/div[1]/label/input[@value='1']")).isSelected();
		if(subs==true)
		{
			System.out.println("yes is selected");
		}
		else
		{
			System.out.println("yes is not selected");
		}
		boolean subsno=driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/form/fieldset[3]/div[1]/div[1]/label/input[@value='0']")).isSelected();
		if(subsno==true)
		{
			System.out.println("no is selected");
		}
		else
		{
			System.out.println("no is not selected");
		}
			
		
	}
 
}
