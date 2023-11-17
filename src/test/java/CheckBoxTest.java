
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class CheckBoxTest {
    WebDriver driver = new ChromeDriver();

    @Test
    public void CheckTest() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        WebElement checkBox1 = driver.findElement(By.xpath("//input[@id='my-check-1']"));
        WebElement checKBox2 = driver.findElement(By.xpath("//input[@id='my-check-2']"));
        checkBox1.click();
        checKBox2.click();
        Thread.sleep(1000);
        Assert.assertFalse(checkBox1.isSelected());
        checkBox1.click();
        checKBox2.click();
        Thread.sleep(1000);
        Assert.assertTrue(checkBox1.isSelected());
        checkBox1.click();
        checKBox2.click();
        Thread.sleep(1000);
        checkBox1.click();
        checKBox2.click();
        Thread.sleep(1000);
        driver.quit();
    }
}
