package ua.com.rozetka.tests;

import ua.com.rozetka.pages.HomePage;
import ua.com.rozetka.pages.SearchResultsPage;
import org.testng.annotations.Test;
import ua.com.rozetka.utils.Checker;

public class Search extends BaseTest {

    @Test
    public void searchQueryRemainsInSearchBoxTest() {
        SearchResultsPage searchResultsPage = new HomePage().search(data);
        boolean isQueryPresent = searchResultsPage.searchBoxContainsSearchQuery(data);
        Checker.checkTrue(isQueryPresent);
    }

    @Test
    public void searchResultsForContainsSearchQuery() {
        SearchResultsPage searchResultsPage = new HomePage().search(data);
        boolean isQueryPresent = searchResultsPage.searchResultsForContainsSearchQuery(data);
        Checker.checkTrue(isQueryPresent);
    }

    @Test
    public void searchResultsContainSearchProductTest() {
        SearchResultsPage searchResultsPage = new HomePage().search(data);
        boolean isProductPresent = searchResultsPage.isProductPresent(data);
        Checker.checkTrue(isProductPresent);
    }

}