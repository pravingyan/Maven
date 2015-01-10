package ua.com.rozetka.utils;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class Checker {

    public static void checkElementContainsText(WebElement element, String text) {
        Assert.assertTrue(StringUtils.contains(element.getText(), text),
                String.format("Text '%s' is not present in element %s", text, element.toString()));
    }

    public static void checkElementContainsText(By by, String text) {
        Assert.assertTrue(StringUtils.contains(Browser.findElement(by).getText(), text),
                String.format("Text '%s' is not present in element %s", text, Browser.findElement(by).toString()));
    }


    public static void checkElementDisplayed(WebElement element) {
        Assert.assertTrue(Browser.waitForElementToBeDisplayed(element),
                String.format("Element not displayed. Element details: %s", element.toString()));

    }

    public static void checkElementNotDisplayed(WebElement element) {
        Assert.assertFalse(Browser.waitForInvisibilityOfElement(element),
                String.format("Element displayed. Element details: %s", element.toString()));
    }

    public static void checkTrue(boolean b) {
        Assert.assertTrue(b);
    }

    public static void checkTrue(boolean b, String errorMessage) {
        Assert.assertTrue(b, errorMessage);
    }

    public static void checkFalse(boolean b) {
        Assert.assertFalse(b);
    }

    public static void checkFalse(boolean b, String errorMessage) {
        Assert.assertFalse(b, errorMessage);
    }

    public static void failTest(String errorMessage) {
        Assert.assertTrue(false, errorMessage);
    }
}
