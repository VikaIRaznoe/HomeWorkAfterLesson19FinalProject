package tests.testng.pageobject.utills;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ManageDriver {
    private AppiumDriver<MobileElement> driver;

    public ManageDriver(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    //Метод ожидает и находит элмент
    protected WebElement waitForElementPresent(By by, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    //SCROLL по всему экрану вниз
    protected void scrollUp(int swipeTime) {
        //TouchAction - позволяет описать действие
        TouchAction action = new TouchAction(driver);
        //Получаем размер всего экрана
        Dimension size = driver.manage().window().getSize();
        //Определяем размер области,в котрой будет производиться scroll
        int x = size.width / 2;
        //Задаем начальную и конечную точку по "x"
        int start_y = (int) (size.height * 0.9);
        int finish_y = (int) (size.height * 0.2);
        action
                //Имитируем нажатие
                .press(PointOption.point(x, start_y))
                //Ждем какое-то время
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(swipeTime)))
                //определяем конечную точку движения
                .moveTo(PointOption.point(x, finish_y))
                //отправляем все это драйверу
                .release()
                //исполняем
                .perform();
    }

    //SCROLL по всему экрану ввверх
    private void scrollDown(int swipeTime) {
        //TouchAction - позволяет описать действие
        TouchAction action = new TouchAction(driver);
        //Получаем размер всего экрана
        Dimension size = driver.manage().window().getSize();
        //Определяем размер области,в котрой будет производиться scroll
        int x = size.width / 2;
        //Задаем начальную и конечную точку по "x"
        int start_y = (int) (size.height * 0.9);
        int finish_y = (int) (size.height * 0.2);
        action
                //Имитируем нажатие
                .press(PointOption.point(x, finish_y))
                //Ждем какое-то время
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(swipeTime)))
                //определяем конечную точку движения
                .moveTo(PointOption.point(x, start_y))
                //отправляем все это драйверу
                .release()
                //исполняем
                .perform();
    }

    protected void scrollUpQuik() {
        scrollUp(500);
    }
}
