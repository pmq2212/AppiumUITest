package com.ui.android;

import com.utils.LogTest;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class BaseAndroidTest {
    protected static AndroidDriver<AndroidElement> driver;
    protected static LogTest log = new LogTest();

    @BeforeSuite
    public void beforeSuit() {
    }

    @AfterSuite
    public void afterSuite(ITestContext context) {
        log.writeLog("--------------------------------------------------------------------");
        log.writeLog(context.getSuite().getName());
        String out = String.format("|  Results:  (%d tests, %d successes, %d failures, %d skipped)  |",
                context.getAllTestMethods().length
                , context.getPassedTests().getAllResults().size()
                , context.getFailedTests().getAllResults().size()
                , context.getSkippedTests().getAllResults().size());

        log.writeLog(out);
        log.writeLog("--------------------------------------------------------------------");

        log.closeLog();
    }

    @BeforeClass
    public void beforeClass() {
        Path currentRelativePath = Paths.get("");
        String path = currentRelativePath.toAbsolutePath().toString();
        path += "/src/test/application/app-release.apk";

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "emulator-5554");
        desiredCapabilities.setCapability("appium:platformVersion", "13");
        desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appium:app", path);

        try {
            URL remoteUrl = new URL("http://127.0.0.1:4723/");
            driver = new AndroidDriver<AndroidElement>(remoteUrl, desiredCapabilities);
        } catch (Exception e) {
            System.out.println("[Error]screenshot " + e.getMessage());
            e.printStackTrace();
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        screenshot(method.getName());
    }

    @AfterMethod
    public void afterMethod(Method method, ITestResult rs) throws InterruptedException {
        String out = this.getClass().getName() + '.' + method.getName() + " execution is: ";
        switch (rs.getStatus()) {
            case ITestResult.SUCCESS:
                out += "SUCCESS";
                break;
            case ITestResult.FAILURE:
                out += "FAILURE";
                break;
            case ITestResult.SKIP:
                out += "SKIP";
                break;
            default:
                break;
        }

        System.out.println(out);
        log.writeLog(out);

        screenshot(method.getName());
        Thread.sleep(1000);
    }

    protected void screenshot(String methodName) {
        try {
            File srcFile = driver.getScreenshotAs(OutputType.FILE);

            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String filename = this.getClass().getName() + '.' + methodName + formatter.format(date);

            File targetFile = new File(log.pathLog + filename + ".jpg");
            FileUtils.copyFile(srcFile, targetFile);
        } catch (Exception e) {
            System.out.println("[Error]screenshot " + e.getMessage());
            e.printStackTrace();
        }
    }
}
