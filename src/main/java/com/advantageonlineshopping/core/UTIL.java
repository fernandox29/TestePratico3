package com.advantageonlineshopping.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UTIL {
    private WebDriver driver;

    public UTIL(WebDriver driver) {
        this.driver = driver;
    }

    /************************ Esperar Elemento ************************/

    public void esperarElemento(By by, int time) {
        new WebDriverWait(driver, Duration.ofSeconds(time)).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void esperarElementoVisivel(By by, int time) {
        WebDriverWait wait;
        wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void esperarElementoClicavel(By by, int time) {
        WebDriverWait wait;
        wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    /************************ TextField e TextArea ************************/

    public void escrever(By by, String texto) {
        this.esperarElemento(by, 5);
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(texto);
    }

    /************************ Botao ************************/

    public void clicarBotao(By by) {
        this.esperarElemento(by, 10);
        driver.findElement(by).click();
    }

    /************************ Link ************************/

    public void clicarTextoLink(String link) {
        this.esperarElemento(By.linkText(link), 5);
        driver.findElement(By.linkText(link)).click();
    }

    /************************ Textos ************************/

    public String obterTexto(By by) {
        return driver.findElement(by).getText();
    }

    public String obterTitleElemento(By by) {
        return driver.findElement(by).getAttribute("title");
    }
}

