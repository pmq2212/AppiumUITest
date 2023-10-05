package android;

import static java.time.Duration.ofMillis;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;

public class AndroidUITest extends BaseAndroidTest {

    protected static AndroidDriver<AndroidElement> driver;

    @BeforeClass
    public void setUp() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "emulator-5554");
        desiredCapabilities.setCapability("appium:platformVersion", "13");
        desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appium:app",
                "/Users/systena/Documents/Working/demo_app/build/app/outputs/flutter-apk/app-release.apk"
        );

        try {
            URL remoteUrl = new URL("http://127.0.0.1:4723/");
            driver = new AndroidDriver<AndroidElement>(remoteUrl, desiredCapabilities);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void tapTab() {
        try {
            AndroidElement tabElement = driver.findElement(MobileBy.cssSelector("[content-desc='スポーツ']"));

            // Tap into find element
            TouchAction touch = new TouchAction(driver);
            touch.tap(tapOptions().withElement(element(tabElement))).release().perform();
            Thread.sleep(1000);

            // Find Caption content view is change
            AndroidElement caption = driver.findElement(MobileBy.cssSelector("[content-desc='DEMO 4']"));

            Thread.sleep(1000);
            Assert.assertNotNull(caption);
        } catch (Exception e) {
            System.out.println("[Error]tapTab " + e.getMessage());
            Assert.assertFalse(true);
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
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("[Error]swipeRight " + e.getMessage());
            Assert.assertFalse(true);
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
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("[Error]swipeLeft " + e.getMessage());
            Assert.assertFalse(true);
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

            touch
                    .press(PointOption.point(800, 800))
                    .waitAction(waitOptions(ofMillis(1000)))
                    .moveTo(PointOption.point(120, 800))
                    .release()
                    .perform();
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("[Error]scrollBar " + e.getMessage());
            Assert.assertFalse(true);
        }
    }
}
