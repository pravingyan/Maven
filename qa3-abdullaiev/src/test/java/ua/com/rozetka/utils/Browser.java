package ua.com.rozetka.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Browser {
    private static Browser browser;
    private String browserName;
    private int shortTimeoutSecs;
    private int defaultTimeoutSecs;
    private WebDriver driver;

    private Browser () {

    }

    public static Browser getInstance() {
        if(browser == null) {
            browser = new Browser();
        }
        return browser;
    }

    public void open() {
        if (browserName.equalsIgnoreCase("firefox")) 
        {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("chrome")) 
        {
            System.setProperty("webdriver.chrome.driver", "soft/chromedriver.exe");
            driver = new ChromeDriver();
        } else 
        {
            Logger.logError("Requested browser: " + browser + ". No such browser is available at the moment, starting FF.");
            driver = new FirefoxDriver();
        }

		driver.manage().window().setPosition(new Point(-10, 0));
		driver.manage().window().setSize(new Dimension(1400,570));
        startUsingDefaultTimeout();
    }

    public void tearDown() {
        getInstance().getDriver().quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public int getShortTimeoutSecs() {
        return getInstance().shortTimeoutSecs;
    }

    public void setShortTimeoutSecs(int shortTimeoutSecs) {
        getInstance().shortTimeoutSecs = shortTimeoutSecs;
    }

    public int getDefaultTimeoutSecs() {
        return defaultTimeoutSecs;
    }

    public void setDefaultTimeoutSecs(int defaultTimeoutSecs) {
        getInstance().defaultTimeoutSecs = defaultTimeoutSecs;
    }

    public String getBrowserName() {
        return getInstance().browserName;
    }

    public void setBrowserName(String browserName) {
        getInstance().browserName = browserName;
    }

    public static WebElement findElement(By by) {
        Logger.logBrowserInfo(String.format("findElement(%s)", by));
        WebElement element;
        try {
            element = getInstance().getDriver().findElement(by);
        } catch (StaleElementReferenceException e) {
            Logger.logException(String.format("StaleElementReference Exception has been caught in findElement('%s')", by));
            element = getInstance().getDriver().findElement(by);
        }
        Logger.logElementPresent(by.toString());
        return element;
    }

    public static List<WebElement> findElements(By by) {
        Logger.logBrowserInfo(String.format("findElements(%s)", by));
        List<WebElement> elements;
        try {
            elements = getInstance().getDriver().findElements(by);
        } catch(StaleElementReferenceException e) {
            Logger.logException(String.format("StaleElementReference Exception has been caught in findElement('%s')", by));
            elements = getInstance().getDriver().findElements(by);
        }
        return elements;
    }

    public static boolean isElementDisplayed(WebElement element) {
        Logger.logBrowserInfo(String.format("isElementDisplayed(WebElement element)"));
        startUsingShortTimeout();
        boolean isDisplayed = false;

        try {
            isDisplayed = element.isDisplayed();
        } catch (Exception ex) {
            Logger.logBrowserInfo(String.format("Exception has been caught, isDisplayed() returns false"));
        }

        startUsingDefaultTimeout();

        if (isDisplayed) {
            Logger.logElementPresent(element.getTagName());
        } else {
            Logger.logElementNotPresent(element.getTagName());
        }

        return isDisplayed;
    }

    public static boolean isElementDisplayed(By by) {
        Logger.logBrowserInfo(String.format("Invoking isElementDisplayed(%s)", by));
        startUsingShortTimeout();
        boolean isDisplayed = false;

        try {
            isDisplayed = findElement(by).isDisplayed();
        } catch (Exception ex) {
            Logger.logBrowserInfo(String.format("Exception has been caught, isDisplayed() returns false"));
        }

        startUsingDefaultTimeout();

        if (isDisplayed) {
            Logger.logElementPresent(by.toString());
        } else {
            Logger.logElementNotPresent(by.toString());
        }

        return isDisplayed;
    }

    public static boolean waitForElementToBeDisplayed(By by) {
        Logger.logBrowserInfo(String.format("Invoking waitForElementToBeDisplayed(%s)", by));
        int timeOfTries = getInstance().getDefaultTimeoutSecs();
        for (int i = 0; i < timeOfTries; i++) {
            Logger.logBrowserInfo(String.format("Iteration %s of %s", i, timeOfTries));
            if (isElementDisplayed(by)) {
                return true;
            }
        }
        Logger.logBrowserInfo(String.format("waitForElementToBeDisplayed(%s) returns false", by));
        return false;
    }

    public static boolean waitForElementToBeDisplayed(WebElement element) {
        Logger.logBrowserInfo(String.format("Invoking waitForElementToBeDisplayed(%s)", element.toString()));
        int timeOfTries = getInstance().getDefaultTimeoutSecs();
        for (int i = 0; i < timeOfTries; i++) {
            Logger.logBrowserInfo(String.format("Iteration %s of %s", i, timeOfTries));
            if (isElementDisplayed(element)) {
                Logger.logBrowserInfo(String.format("waitForElementToBeDisplayed() returns true"));
                return true;
            }
        }
        Logger.logBrowserInfo(String.format("waitForElementToBeDisplayed() returns false"));
        return false;
    }

    public static boolean waitForTextToBePresentInElement(WebElement element, String text) {
        Logger.logBrowserInfo(String.format("Waiting for '%s' text to be present in element %s", text, element.toString()));
        return (new WebDriverWait(getInstance().getDriver(), getInstance().getDefaultTimeoutSecs()))
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public static void moveToAndClickElement(WebElement element) {
        Logger.logBrowserInfo("Invoking moveToAndClickElement for " + element.toString());
        Actions builder = new Actions(getInstance().getDriver());
        builder.moveToElement(element).click(element).build().perform();
    }

    public static boolean waitForInvisibilityOfElement(By by) {
        Logger.logBrowserInfo(String.format("Invoking waitForInvisibilityOfElement(%s)", by));
        int timeOfTries = getInstance().getDefaultTimeoutSecs();
        for (int i = 0; i < timeOfTries; i++) {
            Logger.logBrowserInfo(String.format("Iteration %s of %s", i, timeOfTries));
            if (!isElementDisplayed(by)) {
                Logger.logBrowserInfo("Element is not displayed, returning true.");
                return true;
            }
        }
        Logger.logBrowserInfo("Element is still visible, returning false.");
        return false;
    }

    public static boolean waitForInvisibilityOfElement(WebElement element) {
        Logger.logBrowserInfo(String.format("Invoking waitForInvisibilityOfElement(%s)", element.toString()));
        int timeOfTries = getInstance().getDefaultTimeoutSecs();
        for (int i = 0; i < timeOfTries; i++) {
            Logger.logBrowserInfo(String.format("Iteration %s of %s", i, timeOfTries));
            if (!isElementDisplayed(element)) {
                Logger.logBrowserInfo("Element is not displayed, returning true.");
                return true;
            }
        }
        Logger.logBrowserInfo("Element is still visible, returning false.");
        return false;
    }

    public static WebElement waitForElementToBeClickable(WebElement element) {
        Logger.logBrowserInfo(String.format("Invoking waitForElementToBeClickable(%s)", element.toString()));
        return new WebDriverWait(getInstance().getDriver(), getInstance().getDefaultTimeoutSecs())
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void startUsingShortTimeout() {
        setTimeout(getInstance().shortTimeoutSecs);

    }

    public static void startUsingDefaultTimeout() {
        setTimeout(getInstance().defaultTimeoutSecs);
    }

    private static void setTimeout(int seconds) {
        getInstance().getDriver().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
        Logger.logBrowserInfo(String.format("Timeout: %s seconds", seconds));
    }

}
