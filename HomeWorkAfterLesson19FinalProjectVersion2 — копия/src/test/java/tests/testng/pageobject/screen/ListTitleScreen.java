package tests.testng.pageobject.screen;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import tests.testng.pageobject.objects.Button;
import tests.testng.pageobject.objects.Element;
import tests.testng.pageobject.utills.ManageDriver;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListTitleScreen extends ManageDriver implements Button, Element {

    public ListTitleScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @Override
    public String getTextOfElement() {
        WebElement questionWindow = waitForElementPresent(By.id("md_text_message"), 5);
        String questionText = questionWindow.getText();
        return questionText;
    }

    @Override
    public List<WebElement> getListTextPunktWindow() {
        scrollUp(20);
        WebElement listTitleMessageButtons = waitForElementPresent(By.xpath("//*[contains(@text,'LIST + TITLE + MESSAGE + BUTTONS')]"), 5);
        listTitleMessageButtons.click();
        WebElement element = waitForElementPresent(By.id("md_recyclerview_content"),5);
        //Получаем кол-во элментов в списке, который всплывает при нажатии на кнопку
        List<WebElement> listElements = element.findElements(By.id("md_title"));
        return listElements;
    }

    @Override
    public void clickButton(){
        scrollUp(20);
        WebElement listTitleMessageButtons = waitForElementPresent(By.xpath("//*[contains(@text,'LIST + TITLE + MESSAGE + BUTTONS')]"), 5);
        listTitleMessageButtons.click();
    }

    @Override
    public Boolean getButton() {
        scrollUp(20);
        WebElement listTitleMessageButtons = waitForElementPresent(By.xpath("//*[contains(@text,'LIST + TITLE + MESSAGE + BUTTONS')]"), 5);
        listTitleMessageButtons.click();
        WebElement disagreeButton = waitForElementPresent(By.id("md_button_negative"), 5);
        Boolean visionButton = disagreeButton.isDisplayed();
        return visionButton;
    }


    //Ищет в файле listText строку по названию кнопки(используем регулярку)
    //берем следующую за ней
    public String findStr(){
        //Считываем содержимое файла listText
        String inputFile = "/src/test/resources/listText";
        String workingDir = System.getProperty("user.dir");
        InputStream inputStream;
        List<String> lines = new ArrayList<String> ();
        try {
            inputStream = new FileInputStream(new File(workingDir.substring(0, workingDir.length()) + inputFile));
            DataInputStream in = new DataInputStream(inputStream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null)   {
                lines.add(strLine);
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Смотрим весь файл lisText
        //и выцепляем строку,которая следует за строкой,
        //которая по регулярке совпала
        int numberStr = 0;
        String findStr = "";
        for (String str : lines){
            numberStr++;
            //Для поиска строки LIST + TITLE + MESSAGE + BUTTONS
            Pattern pattern = Pattern.compile("(\\+*[A-Z])");
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()){
                //Предполагаем,что первая строка это заголовок(по названию кнопки)
                //Предполагаем, что после заголовка только одна нужная строка
                findStr = lines.get(1);
                break;
            }
        }
        return findStr;
    }
}