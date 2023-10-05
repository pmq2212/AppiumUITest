package com.ui.android;

import org.testng.Assert;
import org.testng.annotations.Test;

import static java.time.Duration.ofMillis;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;

import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static io.appium.java_client.touch.WaitOptions.waitOptions;

public class AndroidUITest extends BaseAndroidTest {

    @Test
    public void tapTab() {
        try {
            AndroidElement tabElement = driver.
                    findElement(MobileBy.cssSelector("[content-desc='タイズ']"));

            // Tap into find element
            TouchAction touch = new TouchAction(driver);
            touch.tap(tapOptions().withElement(element(tabElement))).release().perform();
            Thread.sleep(1000);

            // Demo screen shot
            screenshot("tapTab");
            Thread.sleep(1000);

            // Tap into find element
            tabElement = driver.
                    findElement(MobileBy.cssSelector("[content-desc='スポーツ']"));
            touch = new TouchAction(driver);
            touch.tap(tapOptions().withElement(element(tabElement))).release().perform();
            Thread.sleep(1000);

            // Find Caption content view is change
            AndroidElement caption = driver.
                    findElement(MobileBy.cssSelector("[content-desc='DEMO 4']"));

            Thread.sleep(1000);
            Assert.assertNotNull(caption);
        } catch (Exception e) {
            System.out.println("[Error]tapTab " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void swipeRight() {
        try {
            // swipe L -> R
            TouchAction touch = new TouchAction(driver);

            touch.press(point(146, 379))
                    .waitAction(waitOptions(ofMillis(100)))
                    .moveTo(point(998, 379))
                    .release()
                    .perform();
            Thread.sleep(300);
        } catch (Exception e) {
            System.out.println("[Error]swipeRight " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void swipeLeft() {
        try {
            // swipe R -> L
            TouchAction touch = new TouchAction(driver);

            touch.press(point(998, 379))
                    .waitAction(waitOptions(ofMillis(100)))
                    .moveTo(point(146, 379))
                    .release()
                    .perform();
            Thread.sleep(300);
        } catch (Exception e) {
            System.out.println("[Error]swipeLeft " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void scrollBar() {
        try {
            TouchAction touch = new TouchAction(driver);

            touch
                    .press(PointOption.point(120, 800))
                    .waitAction(waitOptions(ofMillis(1000)))
                    .moveTo(PointOption.point(800, 800))
                    .release()
                    .perform();
            Thread.sleep(1500);

            // Demo screen shot
            screenshot("scrollBar");
            Thread.sleep(1000);

            touch
                    .press(PointOption.point(800, 800))
                    .waitAction(waitOptions(ofMillis(1000)))
                    .moveTo(PointOption.point(120, 800))
                    .release()
                    .perform();
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("[Error]scrollBar " + e.getMessage());
            Assert.fail();
        }
    }
}
