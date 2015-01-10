package ua.com.rozetka.tests;

import org.testng.annotations.*;
import ua.com.rozetka.utils.TestData;
import ua.com.rozetka.utils.Browser;
import ua.com.rozetka.utils.Logger;

import java.lang.reflect.Method;

public class BaseTest {
    TestData data;

    @Parameters({"browser", "shortTimeoutSecs", "defaultTimeoutSecs", "app_url"})
    @BeforeTest(alwaysRun = true)
    public void setUpBrowser(String browser, String shortTimeoutSecs, String defaultTimeoutSecs, String appUrl) {
        Logger.logBrowserSetUp(browser, Integer.parseInt(defaultTimeoutSecs), appUrl);
        Browser.getInstance().setBrowserName(browser);
        Browser.getInstance().setShortTimeoutSecs(Integer.parseInt(shortTimeoutSecs));
        Browser.getInstance().setDefaultTimeoutSecs(Integer.parseInt(defaultTimeoutSecs));
        Browser.getInstance().open();
        Browser.getInstance().getDriver().get(appUrl);
    }

    @Parameters({"registeredUserName",
            "registeredUserEmail",
            "registeredUserPassword",
            "phoneNumber",
            "productSearchQuery"})
    @BeforeMethod(alwaysRun = true)
    public void setUpTestData(
            String registeredUserName,
            String registeredUserEmail,
            String registeredUserPassword,
            String phoneNumber,
            String productSearchQuery) {
        data = new TestData();
        data.setUserName(registeredUserName);
        data.setUserEmail(registeredUserEmail);
        data.setUserPassword(registeredUserPassword);
        data.setPhoneNumber(phoneNumber);
        data.setProductSearchQuery(productSearchQuery);

    }

    @BeforeMethod(alwaysRun = true)
    public void logTestStart(Method method) {
        Logger.logTestStart(method.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void finish(Method method) {
        Logger.logTestFinish(method.getName());
    }

    @AfterTest(alwaysRun = true)
    public void closeBrowser() {
        Browser.getInstance().tearDown();
    }

}
