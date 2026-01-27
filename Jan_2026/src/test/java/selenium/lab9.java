package selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
public class lab9{
    WebDriver driver;
    JavascriptExecutor js;
    @Parameters("browser")
    @BeforeMethod
    public void setup(String browser)
    {
        if(browser.equalsIgnoreCase("chrome"))
        {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if(browser.equalsIgnoreCase("ie"))
        {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        }
        else
        {
            throw new IllegalArgumentException("Unsupported browser: " + browser + " (use chrome | ie)");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
    @Test
    public void test1() throws InterruptedException {
        System.out.println("Test1 - Lab8_i flow on " + ((driver instanceof ChromeDriver) ? "Chrome" : "IE"));
        driver.get("https://tutorialsninja.com/demo/index.php?");
        driver.manage().window().setSize(new Dimension(550, 672));
        if (!driver.findElements(By.cssSelector(".fa-bars")).isEmpty()) {
            driver.findElement(By.cssSelector(".fa-bars")).click();
        }
        driver.findElement(By.linkText("Desktops")).click();
        boolean exists = !driver.findElements(By.linkText("Mac (1)")).isEmpty();
        System.out.println(exists ? "Present" : "Absent");
        Assert.assertEquals(exists, true, "'Mac (1)' link should be present under Desktops");
        driver.findElement(By.linkText("Mac (1)")).click();
        Select sort = new Select(driver.findElement(By.id("input-sort")));
        sort.selectByVisibleText("Name (A - Z)");
        driver.findElement(By.xpath("(//button[contains(@onclick,'cart.add')])[1]")).click();

        js.executeScript("window.scrollTo(0,577)");
        WebElement headerSearch = driver.findElement(By.name("search"));
        headerSearch.click();
        driver.findElement(By.cssSelector(".input-group-btn > .btn")).click();
        WebElement inputSearch = driver.findElement(By.id("input-search"));
        inputSearch.clear();
        inputSearch.sendKeys("Monitor");
        driver.findElement(By.id("description")).click();
        driver.findElement(By.id("button-search")).click();
    }
    @Test
    public void test2() {
        System.out.println("Test2 - Lab_8 flow on " + ((driver instanceof ChromeDriver) ? "Chrome" : "IE"));
        driver.get("https://tutorialsninja.com/demo/index.php?");
        driver.manage().window().setSize(new Dimension(1138, 672));
        driver.findElement(By.linkText("Desktops")).click();
        driver.findElement(By.linkText("Mac (1)")).click();
        Select sort = new Select(driver.findElement(By.id("input-sort")));
        sort.selectByVisibleText("Name (A - Z)");
        driver.findElement(By.xpath("(//button[contains(@onclick,'cart.add')])[1]")).click();
        js.executeScript("window.scrollTo(0,432)");
    }
    @Test
    public void test3() {
        System.out.println("Test3 - Simple search validation on " + ((driver instanceof ChromeDriver) ? "Chrome" : "IE"));
        driver.get("https://tutorialsninja.com/demo/index.php?");
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("your store"),
                "Home page title should contain 'Your Store'");
        WebElement headerSearch = driver.findElement(By.name("search"));
        headerSearch.clear();
        headerSearch.sendKeys("iPhone");
        driver.findElement(By.cssSelector(".input-group-btn > .btn")).click();
        WebElement searchHeader = driver.findElement(By.cssSelector("#content h1"));
        Assert.assertEquals(searchHeader.getText().trim(), "Search - iPhone", "Search header text mismatch");
    }
}
