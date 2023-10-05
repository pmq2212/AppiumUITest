package com.ui.android;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import appium_flutter.FlutterFinder;
import appium_flutter.finder.FlutterElement;

import io.appium.java_client.MobileElement;

public class FlutterDriverTestFlutterDriver extends FlutterDriverBaseDriver {
    protected FlutterFinder find;

    @BeforeTest
    public void setUp() throws Exception {
        super.setUp();
        find = new FlutterFinder(driver);
    }

    @Test
    public void basicTest () throws InterruptedException {
        System.out.println("[VAO] 1");
        FlutterElement finder = find.text("スポーツ");
        System.out.println("[VAO] 2");
        Thread.sleep(5000);
        finder.click();

        System.out.println("[VAO] 3");
        Thread.sleep(5000);
        System.out.println("[VAO] 4");
    }

    private void clickToElement(String locator){
        MobileElement el = waitFor(locator);
        el.click();
    }

    private MobileElement waitFor(String locator){
        return (MobileElement) driver.executeScript("flutter:waitFor", find.byValueKey(locator), 30);
    }

}
