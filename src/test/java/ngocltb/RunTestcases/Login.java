package ngocltb.RunTestcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Login {
    WebDriver driver;

    @BeforeMethod
    public void createDriver() {

        // Thiết lập WebDriver (ChromeDriver)
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    @Test(priority = 1)
    public void validLogin() throws InterruptedException {
        driver.get("https://rise.fairsketch.com/signin");
        // Nhập thông tin đăng nhập hợp lệ
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[normalize-space()='Sign in']")).click();
        //kiểm tra xem đăng nhập thành công hay không
        //Cách 1: Kiểm tra sự xuất hiện của phần tử sau khi đăng nhập
        //isDisplayed: kiểm tra xem phần tử có xuất hiện trên trang web hay không
        //Assert.assertTrue(): câu lệnh này kiểm tra điều kiện trong ngoặc đúng hay sai
        driver.findElement(By.xpath("//img[@src='https://rise.fairsketch.com/files/system/default-stie-logo.png']"));
        Assert.assertTrue(driver.findElement(By.xpath("//img[@src='https://rise.fairsketch.com/files/system/default-stie-logo.png']")).isDisplayed(), "Login is successful.");
        Thread.sleep(2000);
        //Cách 2: kiểm tra đường dẫn URL
        //So sánh 2 đường dẫn có khớp nhau hay không
//        String expectedURL = "https://rise.fairsketch.com/dashboard/view";
//        String currentURL = driver.getCurrentUrl();
//        Assert.assertEquals(expectedURL, currentURL, "Login is successful");
//        Thread.sleep(2000);
        driver.quit();
    }

    @Test(priority = 2)
    public void unValid() throws InterruptedException {
        driver.get("https://rise.fairsketch.com/signin");
        // Nhập thông tin đăng nhập không hợp lệ
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("abc");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("abc");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[normalize-space()='Sign in']")).click();
        WebElement ErrorLogin = (driver.findElement(By.xpath("//div[@role='alert']")));
        Assert.assertEquals(ErrorLogin.getText(), "Authentication failed!");//getText thuộc kiểu dữ liệu của WebElement
        driver.quit();
    }

    @Test(priority = 3)
    public void EmptyLogin() throws InterruptedException {
        driver.get("https://rise.fairsketch.com/signin");
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='password']")).clear();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[normalize-space()='Sign in']")).click();
        WebElement MessageLeaveLogin = driver.findElement(By.id("email-error"));
        Assert.assertTrue(MessageLeaveLogin.isDisplayed());
        Thread.sleep(5000);
        Assert.assertEquals(MessageLeaveLogin.getText(), "This field is required.");
        Thread.sleep(5000);
        driver.quit();
    }

    @Test(priority = 4)
    public void ForgetPassword() throws InterruptedException {
        driver.get("https://rise.fairsketch.com/signin");
        // Kiểm tra Linktext "Quên mật khẩu" có hoạt động không
        driver.findElement(By.xpath("//a[normalize-space()='Forgot password?']")).click();
        Thread.sleep(5000);
        // Kiểm tra tiêu đề của Forgot Password page
        String expectedURL = "https://rise.fairsketch.com/signin/request_reset_password";
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(expectedURL, currentURL, "//Forgot password");
        Thread.sleep(2000);
//            //Assert.assertEquals(driver.getTitle(), "Sign in | RISE - Ultimate Project Manager and CRM");
//            //Thread.sleep(3000);
        driver.quit();
    }

//        @AfterClass
//        public void tearDown() {
//            // Close the browser after all tests are executed
//            driver.quit();
//        }

}
