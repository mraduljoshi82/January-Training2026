package com.orhrm.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;
import java.util.List;

public class one {

  private WebDriver driver;
  private WebDriverWait wait;
  private JavascriptExecutor js;

  @BeforeMethod
  public void setUp() {
    // Make sure the chromedriver executable is on your PATH or set it explicitly:
    // System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
    driver = new ChromeDriver();
    driver.manage().window().setSize(new Dimension(1280, 800));
    wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    js = (JavascriptExecutor) driver;
  }

  @AfterMethod
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

  @Test
  public void searchEmployeeInPIM() {
    // 1) Open login page
    driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

    // 2) Login (Admin / admin123)
    WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.name("username")));
    username.sendKeys("Admin");

    WebElement password = driver.findElement(By.name("password"));
    password.sendKeys("admin123");

    WebElement loginBtn = driver.findElement(By.cssSelector("button[type='submit']"));
    loginBtn.click();

    // 3) Wait for dashboard to load (top bar visible)
    wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.cssSelector("header.oxd-topbar")));

    // 4) Go to PIM
    WebElement pimMenu = wait.until(ExpectedConditions.elementToBeClickable(
        By.xpath("//span[text()='PIM']")));
    pimMenu.click();

    // 5) Wait for Employee Information page
    wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//h5[text()='Employee Information']")));

    // 6) Fill search form:
    // Employee Name (autocomplete)
    WebElement empNameInput = wait.until(ExpectedConditions.elementToBeClickable(
        By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//input")));
    empNameInput.click();
    empNameInput.sendKeys("Rahul  Das");

    // Optionally pick from dropdown (if suggestion appears)
    // Wait for dropdown options and choose the first one if it matches
    try {
      WebElement suggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(
          By.xpath("//div[@role='listbox']//span[contains(@class,'oxd-autocomplete-option')]")));
      suggestion.click();
    } catch (Exception ignore) {
      // If no suggestion appears, proceed with typed text
    }

    // Employee Id
    WebElement empIdInput = driver.findElement(
        By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div//input"));
    empIdInput.clear();
    empIdInput.sendKeys("667789");

    // Employment Status dropdown (open and pick a value if needed)
    WebElement statusDropdown = driver.findElement(
        By.xpath("//label[text()='Employment Status']/parent::div/following-sibling::div//div[contains(@class,'oxd-select-text')]"));
    statusDropdown.click();

    // Example: select "Full-Time Permanent" (change if you need another)
    try {
      WebElement statusOption = wait.until(ExpectedConditions.elementToBeClickable(
          By.xpath("//div[@role='listbox']//span[text()='Full-Time Permanent']")));
      statusOption.click();
    } catch (Exception ignore) {
      // If the option is not available, continue without selecting
      // You can also remove the status selection entirely if not required
    }

    // 7) Click Search
    WebElement searchBtn = driver.findElement(By.xpath("//button[@type='submit' and .=' Search ']"));
    searchBtn.click();

    // 8) Wait for results table to refresh (rows visible or 'No Records Found')
    wait.until(ExpectedConditions.or(
        ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("div.oxd-table-body div.oxd-table-card")),
        ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//span[text()='No Records Found']"))
    ));

    // Optionally assert something:
    // e.g., print first row if exists
    List<WebElement> rows = driver.findElements(By.cssSelector("div.oxd-table-body div.oxd-table-card"));
    if (!rows.isEmpty()) {
      WebElement firstRow = rows.get(0);
      System.out.println("First result row text: " + firstRow.getText());
    } else {
      System.out.println("No Records Found for the given criteria.");
    }
  }
}