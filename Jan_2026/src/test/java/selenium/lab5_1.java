package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
public class lab5_1 {
   public static void main(String[] args) {
	   WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
       driver.manage().window().maximize();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       // ---------- Part 1: Launch Application ----------
       driver.get("http://demo.opencart.com/");
       // Verify Title
       String title = driver.getTitle();
       System.out.println("Page Title: " + title);
       // My Account → Register
       driver.findElement(By.xpath("//span[text()='My Account']")).click();
       driver.findElement(By.linkText("Register")).click();
       // Verify Register Account Heading
       String heading = driver.findElement(By.xpath("//h1")).getText();
       System.out.println("Heading: " + heading);
       // Click Continue without filling (verify warning)
       driver.findElement(By.xpath("//input[@value='Continue']")).click();
       String warning = driver.findElement(By.cssSelector(".alert-danger")).getText();
       System.out.println("Warning Message: " + warning);
       // ---------- Part 2: Your Personal Details ----------
       driver.findElement(By.id("input-firstname"))
               .sendKeys("ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFG"); // 33 chars
       driver.findElement(By.id("input-lastname"))
               .sendKeys("Tester");
       driver.findElement(By.id("input-email"))
               .sendKeys("test" + System.currentTimeMillis() + "@mail.com");
       driver.findElement(By.id("input-telephone"))
               .sendKeys("9876543210");
       // ---------- Part 3: Your Address ----------
       driver.findElement(By.id("input-address-1"))
               .sendKeys("Test Address Line");
       driver.findElement(By.id("input-city"))
               .sendKeys("Mumbai");
       driver.findElement(By.id("input-postcode"))
               .sendKeys("400001");
       Select country = new Select(driver.findElement(By.id("input-country")));
       country.selectByVisibleText("India");
       Select state = new Select(driver.findElement(By.id("input-zone")));
       state.selectByIndex(1);
       // ---------- Part 4: Password ----------
       driver.findElement(By.id("input-password"))
               .sendKeys("test1234");
       driver.findElement(By.id("input-confirm"))
               .sendKeys("test1234");
       // ---------- Part 5: Newsletter ----------
       driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']")).click();
       // Privacy Policy
       driver.findElement(By.name("agree")).click();
       // Continue
       driver.findElement(By.xpath("//input[@value='Continue']")).click();
       // Verify Account Created
       String successMsg = driver.findElement(By.xpath("//h1")).getText();
       System.out.println("Success Message: " + successMsg);
       // Continue
       driver.findElement(By.linkText("Continue")).click();
       // View Order History
       driver.findElement(By.linkText("View your order history")).click();
       System.out.println("Test Completed Successfully!");
       driver.quit();
   }
}
