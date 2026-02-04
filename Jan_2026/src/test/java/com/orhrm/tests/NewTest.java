package com.orhrm.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.apache.commons.io.FileUtils;

public class NewTest {

  private WebDriver driver;
  private WebDriverWait wait;
  private JavascriptExecutor js;

  // ===== ExtentReports fields (using your snippet) =====
  private static ExtentReports extent;
  private static ExtentTest test;
  private static String projectpath;

  @BeforeSuite
  public void initReport() {
    projectpath = System.getProperty("user.dir");

    // Your snippet: create extent + spark reporter
    extent = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter(projectpath + "\\jan28th_Report.html");
    extent.attachReporter(spark);

    // Ensure Screenshots folder exists
    File shotsDir = new File(projectpath + File.separator + "Screenshots");
    if (!shotsDir.exists()) {
      shotsDir.mkdirs();
    }
  }

  @AfterSuite(alwaysRun = true)
  public void flushReport() {
    if (extent != null) {
      extent.flush();
      System.out.println("Extent report generated at: " + projectpath + "\\jan28th_Report.html");
    }
  }

  @BeforeMethod
  public void setUp(Method method) {
    driver = new ChromeDriver();
    driver.manage().window().setSize(new Dimension(1280, 800));
    wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    js = (JavascriptExecutor) driver;

    // Your snippet: create test in the report (title exactly as you asked)
    test = extent.createTest("Verify the login").assignCategory("PIM");
    // If you prefer dynamic names per @Test method, use:
    // test = extent.createTest(method.getName()).assignCategory("PIM");
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown(ITestResult result) {
    try {
      if (result.getStatus() == ITestResult.FAILURE) {
        // ===== Your screenshot snippet (slightly adapted to save under ./Screenshots) =====
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String dest = "." + File.separator + "Screenshots" + File.separator + "Screenshot_" + timestamp + ".png";
        File destfile = new File(dest);
        FileUtils.copyFile(src, destfile);

        test.fail("Test failed: " + result.getThrowable());
        test.addScreenCaptureFromPath(dest, "Failure Screenshot");
      } else if (result.getStatus() == ITestResult.SUCCESS) {
        test.pass("Test passed");
      } else if (result.getStatus() == ITestResult.SKIP) {
        test.skip("Test skipped: " + result.getName());
      }
    } catch (Exception e) {
      test.warning("Failed during @AfterMethod reporting: " + e.getMessage());
    } finally {
      if (driver != null) {
        driver.quit();
      }
    }
  }

  @Test
  public void searchEmployeeInPIM() {
    // 1) Open login page
    test.info("Navigating to login page");
    driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

    // 2) Login (Admin / admin123)
    WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.name("username")));
    username.sendKeys("Admin");

    WebElement password = driver.findElement(By.name("password"));
    password.sendKeys("admin123");

    WebElement loginBtn = driver.findElement(By.cssSelector("button[type='submit']"));
    loginBtn.click();
    test.info("Login submitted");

    // 3) Wait for dashboard to load (top bar visible)
    wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.cssSelector("header.oxd-topbar")));
    test.pass("Dashboard loaded");

    // 4) Go to PIM
    WebElement pimMenu = wait.until(ExpectedConditions.elementToBeClickable(
        By.xpath("//span[text()='PIM']")));
    pimMenu.click();
    test.info("Clicked PIM menu");

    // 5) Wait for Employee Information page
    wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//h5[text()='Employee Information']")));
    test.pass("Employee Information page visible");

    // 6) Fill search form:
    // Employee Name (autocomplete)
    WebElement empNameInput = wait.until(ExpectedConditions.elementToBeClickable(
        By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//input")));
    empNameInput.click();
    empNameInput.sendKeys("Rahul  Das");
    test.info("Entered Employee Name");

    // Optionally pick from dropdown (if suggestion appears)
    try {
      WebElement suggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(
          By.xpath("//div[@role='listbox']//span[contains(@class,'oxd-autocomplete-option')]")));
      suggestion.click();
      test.info("Selected autocomplete suggestion");
    } catch (Exception ignore) {
      test.warning("No autocomplete suggestion; proceeding with typed name");
    }

    // Employee Id
    WebElement empIdInput = driver.findElement(
        By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div//input"));
    empIdInput.clear();
    empIdInput.sendKeys("667789");
    test.info("Entered Employee ID");

    // Employment Status dropdown (open and pick a value if needed)
    WebElement statusDropdown = driver.findElement(
        By.xpath("//label[text()='Employment Status']/parent::div/following-sibling::div//div[contains(@class,'oxd-select-text')]"));
    statusDropdown.click();
    test.info("Opened Employment Status dropdown");

    try {
      WebElement statusOption = wait.until(ExpectedConditions.elementToBeClickable(
          By.xpath("//div[@role='listbox']//span[text()='Full-Time Permanent']")));
      statusOption.click();
      test.info("Selected Employment Status: Full-Time Permanent");
    } catch (Exception ignore) {
      test.warning("Employment Status option not available; continuing");
    }

    // 7) Click Search
    WebElement searchBtn = driver.findElement(By.xpath("//button[@type='submit' and .=' Search ']"));
    searchBtn.click();
    test.info("Clicked Search");

    // 8) Wait for results table to refresh (rows visible or 'No Records Found')
    wait.until(ExpectedConditions.or(
        ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("div.oxd-table-body div.oxd-table-card")),
        ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//span[text()='No Records Found']"))
    ));

    // Print first row if exists
    List<WebElement> rows = driver.findElements(By.cssSelector("div.oxd-table-body div.oxd-table-card"));
    if (!rows.isEmpty()) {
      WebElement firstRow = rows.get(0);
      test.pass("Results found. First row:\n" + firstRow.getText());
      System.out.println("First result row text: " + firstRow.getText());
    } else {
      test.info("No Records Found for the given criteria.");
      System.out.println("No Records Found for the given criteria.");
    }
  }
}