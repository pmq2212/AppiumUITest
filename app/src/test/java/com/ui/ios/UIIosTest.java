//package com.ui.ios;
//
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterSuite;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.Test;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//
//import io.appium.java_client.ios.IOSDriver;
//import io.appium.java_client.ios.IOSElement;
//import io.appium.java_client.remote.MobileCapabilityType;
//
//class UIIosTest {
//
//    public static URL url;
//    public static DesiredCapabilities capabilities;
//    public static IOSDriver<IOSElement> driver;
//
//    @BeforeClass
//    public void setupAppium() throws MalformedURLException {
//        final String URL_STRING = "http://127.0.0.1:4723/";
//        url = new URL(URL_STRING);
//
//        capabilities = new DesiredCapabilities();
//        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
//        capabilities.setCapability("useNewWDA", false);
//        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
//        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Simulator");
//        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
//        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.4");
//        // MobileCapabilityType.APP is set to the IOS app bundle file path that was created in Step 1. Replace it with your app bundle file path.
//        capabilities
//                .setCapability(MobileCapabilityType.APP,
//                        "/Users/anonymous/Library/Developer/Xcode/DerivedData/AppiumUITestsExampleApp-ctzpwtembojftqdlmhpsholpwycs/Build/Products/Debug-iphonesimulator/AppiumUITestsExampleApp.app");
//
//        driver = new IOSDriver<IOSElement>(url, capabilities);
//    }
//
//    @AfterClass
//    public void afterSuite() {
//        driver.closeApp();
//    }
//
//    @Test
//    public void basicTest() throws InterruptedException {
//        driver.resetApp();
//    }
//}