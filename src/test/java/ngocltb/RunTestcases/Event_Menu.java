package ngocltb.RunTestcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class Event_Menu {
    WebDriver driver;

    @BeforeMethod
    public void createDriver(){
        // Thiết lập WebDriver (ChromeDriver)
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }
    //@AfterClass
//    public void tearDown() {
//        // Close the browser after all tests are executed
//        driver.quit();
//    }
    @Test(priority=1)
    public void validData () throws InterruptedException {
        driver.get("https://rise.fairsketch.com/signin");
        // Nhập thông tin đăng nhập hợp lệ
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
        sleep(2000);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("");
        sleep(2000);
        driver.findElement(By.xpath("//button[normalize-space()='Sign in']")).click();
        sleep(5000);

        driver.findElement(By.xpath("//a[@href='https://rise.fairsketch.com/events']")).click();
        driver.findElement(By.xpath("//a[@class='btn btn-default add-btn']")).click();

        // Fill information in the title fields
        driver.findElement(By.xpath("//input[@id='title']")).sendKeys("Annual meeting");
        sleep(5000);
        // Fill information in the description field
//       driver.findElement(By.xpath("//div[@role='textbox']//p")).sendKeys("Annual salary increase");
//        Thread.sleep(5000);
        //Xủ lý khi gặp date picker
        // Select start and end dates from the date picker
        driver.findElement(By.xpath("//input[@id='start_date']")).sendKeys("10-10-2024");
        driver.findElement(By.xpath("//input[@id='start_time']")).clear();
        driver.findElement(By.xpath("//input[@id='start_time']")).sendKeys("9:00 AM");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@id='end_date']")).sendKeys("10-10-2024");
        driver.findElement(By.xpath("//input[@id='end_time']")).clear();
        driver.findElement(By.xpath("//input[@id='end_time']")).sendKeys("12:00 AM");
        Thread.sleep(3000);
        //Fill information in the location field
        driver.findElement(By.xpath("//input[@id='location']")).sendKeys("Room 1");
        // driver.findElement(By.xpath("//div[@id='s2id_event_labels']//ul[@class='select2-choices']")).sendKeys("Everyone");

//        //Xử lý khi gặp trường là dropdown
//        //Select a object in Client field
//        WebElement clientDropdown = driver.findElement(By.xpath("//div[@id='select2-drop-mask']"));
//        List<WebElement> options = clientDropdown.findElements(By.xpath("//div[@id='s2id_event_labels']//ul[@class='select2-choices']"));
//        // Kiểm tra số lượng các lựa chọn có trong dropdown
//        int numberOfOptions = options.size();
//        Assert.assertEquals(numberOfOptions, 9, "There are 9 objects");
//        // Chọn một giá trị trong dropdown (ở đây chọn giá trị thứ 2)
//        options.get(1).click();
//        // Kiểm tra xem giá trị đã được chọn đúng không
//        String selectedValue = options.get(1).getText();
//        Assert.assertEquals(selectedValue, "Demo Client", "The selected value is correct");
//        driver.quit();

//        driver.findElement(By.xpath("//input[@id='s2id_autogen2_search']]")).click();
//        //Search giá trị cần chọn
//      //  sleep(1);
//        driver.findElement(By.xpath("//div[@id='select2-drop']")).sendKeys("Demo Client");
//
//        //Click chọn Text đã search (cần chọn)
//      Thread.sleep(2000);
//        driver.findElement(By.xpath("//div[@id='select2-result-label-757']")).click();
////        Actions action = new Actions(driver);
////        action.sendKeys(Keys.ENTER).perform(); //Cách ENTER dùng cho TH chỉ hiển thị 1 giá trị lựa chọn
//
//        // Kiểm tra số lượng các lựa chọn có trong dropdown

        driver.quit();
}
}
