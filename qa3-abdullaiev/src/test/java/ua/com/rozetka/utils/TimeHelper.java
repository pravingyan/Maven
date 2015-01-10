package ua.com.rozetka.utils;

import ua.com.rozetka.utils.Logger;

public class TimeHelper {
    public static void delay(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ex) {
            Logger.logException("InterruptedException occurred.");
        }
    }
}
