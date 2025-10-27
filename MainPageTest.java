package com.example.zzz;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPageTest {
    MainPage mainPage = new MainPage();

@BeforeAll    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

@BeforeEach    public void setUp() {
        open("https://www.jetbrains.com/");
    }

    @Test
    public void search() {
        executeJavaScript("arguments[0].click();", $("[aria-label='close']"));
        mainPage.searchButton.click();
       $("[data-test-id='search-input']").sendKeys("Selenium");
       $("button[data-test='full-search-button']").click();
       $("input[data-test-id='search-input']").shouldHave(attribute("value", "Selenium"));
       $("[class='_link_1f70rtz_126 wt-link']").click();
    }

    public String getTextromField(){
        String text;
        text = $("input[data-test='search-input']").attr("value");
        System.out.println("Значение: " + text);
        return text;
    }


    @Test
    public void toolsMenu() {
        mainPage.toolsMenu.click();

        $("div[data-test='main-submenu']").shouldBe(visible);
    }

    @Test
    public void navigationToAllTools() {
        mainPage.seeDeveloperToolsButton.click();
        mainPage.findYourToolsButton.click();

        $("#products-page").shouldBe(visible);

assertEquals("All Developer Tools and Products by JetBrains", Selenide.title());    }
}

