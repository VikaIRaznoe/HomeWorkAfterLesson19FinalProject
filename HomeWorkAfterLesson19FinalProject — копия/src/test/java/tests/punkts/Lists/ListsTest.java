package tests.punkts.Lists;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;
import tests.testng.pageobject.screen.ListsScreen;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;


@Epic(value = "раздел Lists")
@Feature(value = "кнопка LIST + TITLE + MESSAGE + BUTTONS")
public class ListsTest {
    private AppiumDriver<MobileElement> driver;

    ListsScreen listTitleScreen = new ListsScreen(driver);

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

    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Description(value = "Проверяем кликабельность кнопки")
    public void listTitleMessageButtonsTest() throws InterruptedException {
        ListsScreen listTitleScreen = new ListsScreen(driver);
        listTitleScreen.clickButton();
    }

    @Severity(SeverityLevel.NORMAL)
    @Parameters("question")
    @Test
    @Description(value = "Проверяем наличие вопроса после клика на кнопку LIST + TITLE + MESSAGE + BUTTONS")
    public void listTitleMessageButtonsQuestionTest() throws InterruptedException {
        ListsScreen listTitleScreen = new ListsScreen(driver);
        listTitleScreen.clickButton();
        Assert.assertEquals(listTitleScreen.getTextOfElement(),"Use Google's Location Services?");
    }

    @Severity(SeverityLevel.NORMAL)
    @Test
    @Description(value = "Проверяем наличие,НЕ РАБОТУ, кнопки Disagree после клика на кнопку LIST + TITLE + MESSAGE + BUTTONS")
    public void searchButtonTest(){
        ListsScreen listTitleScreen = new ListsScreen(driver);
        Assert.assertTrue(listTitleScreen.getButton(),"А где кнопка?");
    }

    @Severity(SeverityLevel.BLOCKER)
    @Test (dataProviderClass = tests.testng.pageobject.utills.DataProviders.class, dataProvider = "dataProvider")
    @Description(value = "После нажатия на кнопку LIST + TITLE + MESSAGE + BUTTONS,появляется окно со списком,проверяем список на наличие нужных слов")
    @Step("Сравниваем эталонный список {data} из файла listText со списком,который появляется после нажатия на кнопку LIST + TITLE + MESSAGE + BUTTONS")
    public void findStrRefactTest(String data) {

        //Данные из файла listText
        String str = data;
        List<String> stringList = Arrays.asList(str.split(","));

        //После нажатия на кнопку появляется окно со списком.Нам нужно сравнить его со списком из файла listText
        ListsScreen listTitleScreen = new ListsScreen(driver);
        List<WebElement> listElements = listTitleScreen.getListTextPunktWindow();
        for(int i = 0; i< listElements.size(); i++) {
            Assert.assertEquals(listElements.get(i).getText(),stringList.get(i));
        }
    }
}
