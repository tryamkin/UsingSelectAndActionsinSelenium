
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;


import java.util.stream.Collectors;

    /*
     * Please ignore the `BaseTest` part
     */
    public class SelectTest {

        WebDriver driver = new ChromeDriver();

        void goToSelectPage() {
            driver.get("https://www.selenium.dev/selenium/web/selectPage.html");
            driver.manage().window().fullscreen();
          //  driver.findElement(By.linkText("selectPage.html")).click();
        }

        @Test
        public void testSimpleDropDown() throws Exception {
            goToSelectPage();
            final WebElement selectWithoutMultiple = driver.findElement(By.id("selectWithoutMultiple"));
            Select simpleDropDown = new Select(selectWithoutMultiple);
            simpleDropDown.selectByValue("two");

            String newValue = selectWithoutMultiple.getAttribute("value");
            System.out.println(newValue);
            Assert.assertEquals("two", newValue);

            Thread.sleep(5000);
        }

        @Test
        public void testMultipleSelect() throws Exception {
            goToSelectPage();
            WebElement selectElement = driver.findElement(By.id("selectWithMultipleEqualsMultiple"));
            Select multiSelect = new Select(selectElement);
            // First Option is selected already via HTML.
            multiSelect.deselectByIndex(0);
            multiSelect.selectByIndex(2);
            multiSelect.selectByVisibleText("Cheddar");

            System.out.println(selectElement.getAttribute("value"));
            System.out.println(multiSelect.getAllSelectedOptions().stream().map(WebElement::getText).collect(Collectors.toList()));
            Thread.sleep(5000);
        }


        @Test
        void testLongList() throws Exception {
            goToSelectPage();
            WebElement selectElement = driver.findElement(By.id("selectWithMultipleLongList"));
            Select select = new Select(selectElement);
            select.selectByVisibleText("five");
            select.selectByVisibleText("six");

            System.out.println(selectElement.getAttribute("value"));
            System.out.println(select.getAllSelectedOptions().stream().map(WebElement::getText).collect(Collectors.toList()));
            Thread.sleep(5000);
        }

        @AfterTest
        public void close(){
            driver.quit();
        }

    }

