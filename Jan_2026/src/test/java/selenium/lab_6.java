package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
		
public class lab_6 {
	public static void main(String[] args) {
		
		        WebDriver driver = new ChromeDriver();
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		        driver.manage().window().maximize();
		        driver.get("http://demo.opencart.com/");
		
		        driver.findElement(By.xpath("//span[text()='My Account']")).click();
		        driver.findElement(By.linkText("Login")).click();
		
		        driver.findElement(By.id("input-email"))
		                .sendKeys("your_email@example.com");
		        driver.findElement(By.id("input-password"))
		                .sendKeys("your_password");
		        driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		        driver.findElement(By.linkText("Components")).click();
		        driver.findElement(By.linkText("Monitors (2)")).click();
		
		        Select showDropdown = new Select(driver.findElement(By.id("input-limit")));
		        showDropdown.selectByVisibleText("25");
		
		        driver.findElement(By.xpath("(//button[@data-original-title='Add to Cart'])[1]")).click();
		
		        driver.findElement(By.linkText("Specification")).click();
		
		        WebElement specTable = driver.findElement(By.xpath("//table[@class='table table-bordered']"));
		        if (specTable.isDisplayed()) {
		            System.out.println("Specifications are present");
		        }
		
		        driver.findElement(By.xpath("//button[@data-original-title='Add to Wish List']")).click();
		
		        String wishMsg = wait.until(
		                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success"))
		        ).getText();
		
		        System.out.println(wishMsg);
		
		        driver.findElement(By.name("search")).sendKeys("Mobile");
		        driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		        driver.findElement(By.name("description")).click();
		
		        driver.findElement(By.linkText("HTC Touch HD")).click();
		
		    
		        WebElement qty = driver.findElement(By.id("input-quantity"));
		        qty.clear();
		        qty.sendKeys("3");
		
		        driver.findElement(By.id("button-cart")).click();
		
		        String cartMsg = wait.until(
		                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success"))
		        ).getText();
		
		        System.out.println(cartMsg);
		
		        driver.findElement(By.id("cart-total")).click();
		        driver.findElement(By.linkText("View Cart")).click();
		
		        WebElement cartProduct = driver.findElement(By.linkText("HTC Touch HD"));
		        if (cartProduct.isDisplayed()) {
		            System.out.println("Mobile added to cart successfully");
		        }
		
		        driver.findElement(By.linkText("Checkout")).click();
		
		        driver.findElement(By.xpath("//span[text()='My Account']")).click();
		        driver.findElement(By.linkText("Logout")).click();
		
		        WebElement logoutHeading = driver.findElement(By.xpath("//h1[text()='Account Logout']"));
		        if (logoutHeading.isDisplayed()) {
		            System.out.println("Logout successful");
		        }
		
		        driver.findElement(By.linkText("Continue")).click();
		
		        driver.quit();
	}
 
}
 
