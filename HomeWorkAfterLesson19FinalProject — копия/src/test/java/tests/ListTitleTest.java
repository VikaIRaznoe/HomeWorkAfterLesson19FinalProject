package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;
import tests.testng.pageobject.screen.ListTitleScreen;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;


@Epic(value = "раздел Lists")
public class ListTitleTest {
    private AppiumDriver<MobileElement> driver;

    ListTitleScreen listTitleScreen = new ListTitleScreen(driver);

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
    public void listTitleMessageButtonsTest() throws InterruptedException {
        ListTitleScreen listTitleScreen = new ListTitleScreen(driver);
        listTitleScreen.clickButton();
        //listTitleScreen.getTextOfElement();
    }

    @Parameters("question")
    @Test
    @Description(value = "Проверяем наличие вопроса после клика на кнопку LIST + TITLE + MESSAGE + BUTTONS")
    public void listTitleMessageButtonsQuestionTest() throws InterruptedException {
        ListTitleScreen listTitleScreen = new ListTitleScreen(driver);
        listTitleScreen.clickButton();
        Assert.assertEquals(listTitleScreen.getTextOfElement(),"Use Google's Location Services?");
    }

    @Test
    @Description(value = "Проверяем наличие,НЕ РАБОТУ, кнопки Disagree после клика на кнопку LIST + TITLE + MESSAGE + BUTTONS")
    public void searchButtonTest(){
        ListTitleScreen listTitleScreen = new ListTitleScreen(driver);
        Assert.assertTrue(listTitleScreen.getButton(),"А где кнопка?");
    }

    @Test (dataProviderClass = tests.testng.pageobject.utills.DataProviders.class, dataProvider = "dataProvider")
    @Description(value = "После нажатия на кнопку LIST + TITLE + MESSAGE + BUTTONS,появляется окно со списком,проверяем список на наличие нужных слов")
    public void findStrRefactTest(String data) {

        //Данные из файла listText
        String str = data;
        List<String> stringList = Arrays.asList(str.split(","));

        //После нажатия на кнопку появляется окно со списком.Нам нужно сравнить его со списком из файла listText
        ListTitleScreen listTitleScreen = new ListTitleScreen(driver);
        List<WebElement> listElements = listTitleScreen.getListTextPunktWindow();
        for(int i = 0; i< listElements.size(); i++) {
            Assert.assertEquals(listElements.get(i).getText(),stringList.get(i));
        }
    }
}
