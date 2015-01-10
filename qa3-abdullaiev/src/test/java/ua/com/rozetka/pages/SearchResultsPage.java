package ua.com.rozetka.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ua.com.rozetka.utils.Browser;
import ua.com.rozetka.utils.Checker;
import ua.com.rozetka.utils.Logger;
import ua.com.rozetka.utils.TestData;

public class SearchResultsPage extends BasePage {
    private By searchResultsDivBy = By.xpath("//div[@class='search-result-goods']");
    private By greenBuyButtonsBy = By.xpath("//button[@class='ibutton-css-green']");
    private By blueInCartButtonBy = By.xpath("//button[@class='ibutton-css-link-blue']");
    private String searchResultsForTextXpath = "//span[.='%s']";
    private String productInResultsXpath = "//a[contains(text(), '%s')]";

    public boolean searchBoxContainsSearchQuery(TestData data) {
        Logger.info(String.format("Verifying if search box contains '%s' query", data.getProductSearchQuery()));
        WebElement searchBox = Browser.findElement(searchResultsDivBy);
        if (Browser.waitForTextToBePresentInElement(searchBox, data.getProductSearchQuery())) {
            Logger.info("Text in SearchBox matches search query.");
            return true;
        } else {
            Logger.logError(String.format("Text in SearchBox mismatches search query. " +
                    "SearchBox text: '%s'. Expected text: %s", searchBox.getText(), data.getProductSearchQuery()));
            return false;
        }
    }

    public boolean searchResultsForContainsSearchQuery(TestData data) {
        Logger.info(String.format("Verifying if 'search results for' contains '%s' query", data.getProductSearchQuery()));
        WebElement searchResultsFor = Browser.findElement(By.xpath(String.format(searchResultsForTextXpath, data.getProductSearchQuery())));
        if (searchResultsFor.isDisplayed()) {
            Logger.info("Text in SearchResultsFor matches search query.");
            return true;
        } else {
            Logger.logError(String.format("Text in SearchResultsFor mismatches search query. " +
                    "Expected text: '%s'. Actual text: '%s'.", searchResultsFor.getText(), data.getProductSearchQuery()));
            return false;
        }
    }

    public boolean isProductPresent(TestData data) {
        Logger.info("Verifying whether search results contain following product: " + data.getProductSearchQuery());
        By productBy = By.xpath(String.format(productInResultsXpath, data.getProductSearchQuery()));
        if (Browser.isElementDisplayed(productBy)) {
            return true;
        } else {
            Logger.logError("Search results do not contain the following product: " + data.getProductSearchQuery());
            return false;
        }
    }

    public CartPopUp addToCart(TestData data) {
        Logger.info("Adding to cart following product:" + data.getProductSearchQuery());

        if (!isProductPresent(data)) {
            return null;
        }

        Browser.findElement(searchResultsDivBy).findElement(greenBuyButtonsBy).click();

        if (!Browser.waitForElementToBeDisplayed(new CartPopUp().cartPopUpBy)) {
            if (Browser.isElementDisplayed(blueInCartButtonBy)) {
                Browser.findElement(blueInCartButtonBy).click();
            } else {
                Checker.failTest("Couldn't add to cart product " + data.getProductSearchQuery());
            }
        }
        return new CartPopUp();

    }

}
