package ua.com.rozetka.tests;

import ua.com.rozetka.utils.Checker;
import ua.com.rozetka.pages.HomePage;
import org.testng.annotations.Test;

public class Authorization extends BaseTest {

    @Test
    public void logInTest() {
        HomePage home = new HomePage();
        home.login(data);
        boolean isLoggedIn = home.isUserLoggedIn(data.getUserName());
        Checker.checkTrue(isLoggedIn, "User is not logged in.");
        home.logout();
    }

    @Test
    public void logOutTest() {
        HomePage home = new HomePage();
        home.login(data);
        home.logout();
        boolean isLoggedOut = home.isUserLoggedOut(data.getUserName());
        Checker.checkTrue(isLoggedOut, "User is not logged out.");
    }

}
