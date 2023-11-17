import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;


/*
 * Ignore the `BaseTest` part
 */
public class ActionTest {

     WebDriver driver = new ChromeDriver();


    private Actions actions;

    private Actions getActions() {
        if (actions == null) {
            actions = new Actions(driver);
        }
        return actions;
    }



    void goToWebForm() {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
       // driver.findElement(By.linkText("web-form.html")).click();
    }

    void goToDragAndDrop() {
      driver.get("https://www.selenium.dev/selenium/web/dragAndDropTest.html");
      //  driver.findElement(By.linkText("dragAndDropTest.html")).click();
    }

    @Test
    void testSliderArrowKeys() throws Exception {
        goToWebForm();
        WebElement slider =  driver.findElement(By.name("my-range"));
        slider.sendKeys(Keys.LEFT, Keys.LEFT, Keys.LEFT, Keys.LEFT);
        Assert.assertEquals("1", slider.getAttribute("value"));
        Thread.sleep(5000);
    }

    @Test
    void testSliderAction() throws Exception {
        goToWebForm();
        WebElement slider =  driver.findElement(By.xpath("//input[@name='my-range']"));
        getActions().clickAndHold(slider)
                .moveByOffset(-200, 0)
                .release()
                .perform();
        System.out.println(slider.getAttribute("value"));
        Assert.assertEquals("0", slider.getAttribute("value"));
        Thread.sleep(5000);
    }

    @Test
    void testSliderClick() throws Exception {
        goToWebForm();
        WebElement slider =  driver.findElement(By.name("my-range"));
        final Dimension size = slider.getSize();
        int sliderWidth = size.getWidth();
        getActions().moveToElement(slider)
                .moveByOffset(sliderWidth * 2 / 5, 0)
                .click()
                .perform();
        Thread.sleep(5000);

    }
    @Test
    void testKeyActions() throws Exception {
        goToWebForm();
        WebElement textArea =  driver.findElement(By.name("my-textarea"));
        getActions().click(textArea)
                .keyDown(Keys.SHIFT)
                .sendKeys("abCDe")
                .keyUp(Keys.SHIFT)
                .perform();

        Assert.assertEquals("ABCDE", textArea.getAttribute("value"));
        Thread.sleep(5000);
    }

    @Test
    void testDragAndDrop() throws Exception {
        goToDragAndDrop();
        WebElement test1 =  driver.findElement(By.id("test1"));
        getActions().clickAndHold(test1)
                .moveByOffset(100, 50)
                .pause(1000)
                .moveByOffset(100, 50)
                .release()
                .perform();
        Thread.sleep(5000);
    }

    @Test
    void testDragAndDropBy() throws Exception {
        goToDragAndDrop();
        WebElement test1 =  driver.findElement(By.id("test1"));
        getActions()
                .dragAndDropBy(test1, 200, 400)
                .perform();
        Thread.sleep(2000);
    }

    @Test
    void testDragAndDropToElement() throws Exception {
        goToDragAndDrop();
        WebElement test1 =  driver.findElement(By.id("test1"));
        System.out.println(test1.getLocation());
        WebElement test4 =  driver.findElement(By.id("test4"));
        getActions()
                .dragAndDrop(test1, test4)
                .perform();
        System.out.println(test1.getLocation());
        Assert.assertEquals(test4.getLocation(), test1.getLocation());
        Thread.sleep(2000);
    }

    @Test
    void testColor() throws Exception {
        goToWebForm();
        final WebElement colorPicker =  driver.findElement(By.name("my-colors"));
        System.out.println(colorPicker.getAttribute("value"));
        colorPicker.click();
        getActions()
                .sendKeys(Keys.TAB, Keys.TAB, Keys.TAB)
                .sendKeys("255")
                .sendKeys(Keys.TAB)
                .pause(500)
                .sendKeys("0")
                .sendKeys(Keys.TAB)
                .pause(500)
                .sendKeys("0")
                .pause(500)
                .sendKeys(Keys.ENTER)
                .perform();

        final String color = colorPicker.getAttribute("value");
        System.out.println(color);
        Assert.assertEquals("#ff0000", color);

        Thread.sleep(2000);

    }
    @AfterTest
    public void close(){
        driver.quit();
    }
}