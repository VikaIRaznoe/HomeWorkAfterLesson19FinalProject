package tests.testng.pageobject.screen;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import tests.testng.pageobject.objects.Button;
import tests.testng.pageobject.objects.Element;
import tests.testng.pageobject.utills.ManageDriver;

import java.util.List;

public class SingleChoiceLists extends ManageDriver implements Button, Element {

    public SingleChoiceLists(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Override
    public String getTextOfElement() {
        WebElement questionWindow = waitForElementPresent(By.id("md_text_title"), 5);
        String questionText = questionWindow.getText();
        return questionText;
    }

    @Override
    public Boolean getButton() {
        return null;
    }

    @Override
    public void clickButton(){
        scrollUpQuik();
        WebElement element = waitForElementPresent(By.id("single_choice_buttons_titled"),5);
        element.click();
    }

    @Override
    public List<WebElement> getListTextPunktWindow() {
        return null;
    }
}
