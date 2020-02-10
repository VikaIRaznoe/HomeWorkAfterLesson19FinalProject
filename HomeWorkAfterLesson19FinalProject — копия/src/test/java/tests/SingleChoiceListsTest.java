package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.testng.pageobject.screen.SingleChoiceListsScreen;

import java.net.MalformedURLException;
import java.net.URL;

public class SingleChoiceListsTest {
    private AppiumDriver<MobileElement> driver;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName","Android");
        caps.setCapability("platformVersion","8.0");
        caps.setCapability("deviceName","emulator-5554");
        caps.setCapability("appPackage","com.afollestad.materialdialogssample");
        caps.setCapability("appActivity","com.afollestad.materialdialogssample.MainActivity");
        caps.setCapability("app","/Users/viktoria/IdeaProjects/Java/HomeWorkAfterLesson18FinalProject/src/test/resources/Apps/sample.apk");
        //caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,80);

        URL appiumURL = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AppiumDriver<MobileElement>(appiumURL,caps);

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    @Description(value = "Проверяем кликабельность кнопки")
    public void singleChoiceListsButtonsTest() throws InterruptedException {
        SingleChoiceListsScreen singleChoiceListsScreen = new SingleChoiceListsScreen(driver);
        singleChoiceListsScreen.clickButton();
    }
}
