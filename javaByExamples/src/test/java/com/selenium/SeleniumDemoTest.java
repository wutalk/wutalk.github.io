package com.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SeleniumDemoTest {

    boolean slowPace = true;

    @Test
    public void testHello() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Users/owen/bin/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            driver.get("https://cn.bing.com");
            showPace();
//            driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER);
            driver.findElement(By.id("sb_form_q")).click();
            showPace();
            driver.findElement(By.id("sb_form_q")).sendKeys("hello");
            showPace();

            driver.findElement(By.cssSelector("#search_icon > svg")).click();
//            WebElement firstResult = wait.until(presenceOfElementLocated(By.cssSelector("h3")));

//            System.out.println(firstResult.getAttribute("textContent"));
            showPace();
        } finally {
            TimeUnit.SECONDS.sleep(2);
            driver.quit();
        }
    }

    private void showPace() throws InterruptedException {
        if (slowPace) {
            TimeUnit.SECONDS.sleep(2);
        }
    }
}
