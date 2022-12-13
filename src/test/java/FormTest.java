import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;


public class FormTest {
    private WebDriver driver;


        @BeforeAll
        static void setupClass() {
            WebDriverManager.chromedriver().setup();
        }


        @BeforeEach
        void setUp(){
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(true);

            driver = new ChromeDriver(options);
        }



        @AfterEach
        void tearsDown() {
            driver.quit();
            driver = null;
        }

        @Test
        void shouldSentData() {
            driver.get("file:///Users/daryabuldakova/Downloads/qa-test.html");
            driver.findElement(By.id("loginEmail")).sendKeys("test@protei.ru");
            driver.findElement(By.id("loginPassword")).sendKeys("test");
            driver.findElement(By.id("authButton")).click();
            driver.findElement(By.id("dataAlertsHolder"));
            driver.findElement(By.id("dataEmail")).sendKeys("test@protei.ru");
            driver.findElement(By.id("dataName")).sendKeys("Ivan");
            driver.findElement(By.id("dataGender")).click();
            WebElement dataGender = driver.findElement(By.id("dataGender"));
            Select select1 = new Select(dataGender);
            select1.selectByVisibleText("Женский");
            driver.findElement(By.id("dataCheck11")).click();
            driver.findElement(By.id("dataCheck12")).click();
            driver.findElement(By.id("dataSelect21")).click();
            driver.findElement(By.id("dataSend")).click();
            String expected = "E-Mail Имя Пол Выбор 1 Выбор 2\n" +
                    "test@protei.ru Ivan Женский 1.1, 1.2 2.1";
            String actual = driver.findElement(By.id("dataTable")).getText().trim();
            Assertions.assertEquals(expected, actual);
        }
    }
