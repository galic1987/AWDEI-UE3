package tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.*;
import org.openqa.selenium.support.ui.Select;

import com.opera.core.systems.OperaDriver;

public class IvoTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	  // works ok
	  // driver = new FirefoxDriver(); 
	  
	  // opera seems to be fastest one
	   driver = new OperaDriver(); 

	  
	  //not ok
    /// driver = new SafariDriver(); 
	  
	  
	 //driver = new ChromeDriver(); not
	  
	  
	  
    baseUrl = "http://derstandard.at/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testMyTestCase() throws Exception {
    driver.get("http://derstandard.at/");
    driver.findElement(By.linkText("Bildung")).click();
    driver.findElement(By.linkText("Schule & Politik")).click();
    driver.findElement(By.linkText("Kriterienkatalog soll Direktorenbestellung objektiver machen")).click();
    // Warning: assertTextPresent may require manual changes
    assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
    driver.findElement(By.cssSelector("div.copytext > p")).click();
    driver.findElement(By.linkText("Lifestyle")).click();
    driver.findElement(By.linkText("Superfeine Schäfchen zählen")).click();
    // Warning: assertTextPresent may require manual changes
    //assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
    driver.findElement(By.linkText("Grün & Zeug")).click();
    // Warning: assertTextPresent may require manual changes
    assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
    driver.findElement(By.linkText("Ein Beet als Blütenwand")).click();
    assertTrue(isElementPresent(By.xpath("//*[@id=\"o4_1363708986160\"]")));
    assertTrue(driver.findElement(By.xpath("//*[@id=\"siteSearch\"]")).isDisplayed());
    assertThat("", is(not(driver.getTitle())));
    driver.findElement(By.linkText("Web")).click();
    driver.findElement(By.linkText("3D-Drucker-Waffe durch Sicherheitskontrollen geschmuggelt")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
    driver.findElement(By.id("siteSearchQuery")).clear();
    driver.findElement(By.id("siteSearchQuery")).sendKeys("finale");
    driver.findElement(By.id("siteSearchButton")).click();
    driver.findElement(By.linkText("Stimmung am Zentralfriedhof")).click();
    driver.findElement(By.cssSelector("h1")).click();
    // Warning: assertTextPresent may require manual changes
    assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
