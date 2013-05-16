package tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class MortagagecalculatorSileniumJunitCase {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://www.mortgagecalculator.org/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testMortagagecalculatorSileniumJunitCase() throws Exception {
    driver.get(baseUrl + "/");
//    driver.findElement(By.name("param[homevalue]")).click();
    driver.findElement(By.name("param[homevalue]")).clear();
    driver.findElement(By.name("param[homevalue]")).sendKeys("200");
    new Select(driver.findElement(By.name("param[credit]"))).selectByVisibleText("Fair");
    driver.findElement(By.name("param[principal]")).clear();
    driver.findElement(By.name("param[principal]")).sendKeys("200.000");
    driver.findElement(By.name("param[interest_rate]")).clear();
    driver.findElement(By.name("param[interest_rate]")).sendKeys("10");
    driver.findElement(By.name("param[term]")).clear();
    driver.findElement(By.name("param[term]")).sendKeys("50");
    new Select(driver.findElement(By.name("param[start_month]"))).selectByVisibleText("Jul");
    driver.findElement(By.name("param[property_tax]")).clear();
    driver.findElement(By.name("param[property_tax]")).sendKeys("1.20");
    driver.findElement(By.name("param[pmi]")).clear();
    driver.findElement(By.name("param[pmi]")).sendKeys("0.4");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    try {
      assertEquals("$1.88", driver.findElement(By.cssSelector("td > h3")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("$779.66", driver.findElement(By.xpath("//table[@id='summary']/tbody/tr[5]/td/h3")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("$120.00", driver.findElement(By.xpath("//table[@id='summary']/tbody/tr[7]/td/h3")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("$1,126.93", driver.findElement(By.xpath("//table[@id='summary']/tbody/tr[3]/td[3]/h3")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Jun, 2063", driver.findElement(By.xpath("//table[@id='summary']/tbody/tr[5]/td[3]/h3")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("$27.27", driver.findElement(By.xpath("//table[@id='summary']/tbody/tr[7]/td[3]/h3")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Aug, 2047", driver.findElement(By.xpath("//table[@id='summary']/tbody/tr[9]/td[3]/h3")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
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