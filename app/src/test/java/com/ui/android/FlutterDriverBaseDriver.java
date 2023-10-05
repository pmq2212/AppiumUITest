package com.ui.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.URL;

public class FlutterDriverBaseDriver {
    public AppiumDriver<MobileElement> driver;
    public WebDriverWait wait;
//    private static AppiumDriverLocalService service;

    @BeforeTest
    public void setUp() throws Exception {
//        service = AppiumDriverLocalService.buildDefaultService();
//        service.start();
//
//        if (service == null || !service.isRunning()) {
//            throw new AppiumServerHasNotBeenStartedLocallyException("An appium server node is not started!");
//        }
        String path = "/Users/systena/Documents/Working/demo_app/build/app/outputs/apk/debug/app-debug.apk";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "emulator-5554");
        capabilities.setCapability("appium:platformVersion", "13");
        capabilities.setCapability("appium:automationName", "Flutter");
        capabilities.setCapability("appium:app", path);
//        capabilities.setCapability("appium:noReset", true);
//        capabilities.setCapability("appium:fullReset", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/");
        driver = new IOSDriver<MobileElement>(remoteUrl, capabilities);
        wait = new WebDriverWait(driver, 10);
        waitForFirstFrame();
    }

    @AfterTest
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
//        if (service != null) {
//            service.stop();
//        }
    }

    public void waitForFirstFrame(){
        driver.executeScript("flutter:waitForFirstFrame");
    }
}
