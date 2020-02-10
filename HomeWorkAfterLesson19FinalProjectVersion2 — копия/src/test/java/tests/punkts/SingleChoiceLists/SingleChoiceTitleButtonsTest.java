package tests.punkts.SingleChoiceLists;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.testng.pageobject.screen.ListTitleScreen;
import tests.testng.pageobject.screen.SingleChoiceLists;

import java.net.MalformedURLException;
import java.net.URL;

@Epic(value = "раздел SingleChoiceLists")
@Feature(value = "кнопка SINGLE CHOICE + TITLE + BUTTONS")
public class SingleChoiceTitleButtonsTest {
    private AppiumDriver<MobileElement> driver;

    //ListTitleScreen listTitleScreen = new ListTitleScreen(driver);
    //SingleChoiceLists singleChoiceLists = new SingleChoiceLists(driver);

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
    @Description(value = "Проверяем название заголовка во сплывающем окне при нажатии на кнопку")
    public void singleChoiceTitleButtonsTest() {
        //ListTitleScreen listTitleScreen = new ListTitleScreen(driver);
        //listTitleScreen.getTextOfElement();
        SingleChoiceLists singleChoiceLists = new SingleChoiceLists(driver);
        singleChoiceLists.clickButton();
    }
}
