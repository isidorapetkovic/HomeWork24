package HomeWork24;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//Napisati program koji na sajtu:
//http://automationpractice.com
//dodaje najmanje 3 proizvoda u cart, nastavlja do cart-a, izbacuje jedan proizvod,
// a drugom povecava kolicinu za 1.

public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        JavascriptExecutor scroll = (JavascriptExecutor) driver;

        driver.get("http://automationpractice.com/index.php");
        scroll.executeScript("window.scrollBy(0,800)", "");

        Actions hover = new Actions(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        for (int i = 0; i < 3; i++) {
            WebElement product = driver.findElement(By.xpath("//*[@id='homefeatured']/li[" + (i + 1) + "]/div/div[2]"));
            hover.moveToElement(product);
            product.click();
            WebElement addButton = driver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[" + (i + 1) + "]/div/div[2]/div[2]/a[1]/span"));
            addButton.click();

            if (i == 2) {
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a"))).click();
            } else {
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/span/span"))).click();
            }

            Thread.sleep(2000);
        }

        WebElement deleteButton = driver.findElement(By.xpath("//*[contains(@class, 'cart_quantity_delete')]"));
        deleteButton.click();
        Thread.sleep(2000);

        WebElement plusButton = driver.findElements(By.xpath("//*[contains(@class, 'cart_quantity_up')]")).get(1);
        plusButton.click();
        Thread.sleep(5000);

        driver.quit();
    }
}

