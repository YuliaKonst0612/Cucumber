package hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Hooks {


    @BeforeClass
    public static void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));

    }

    @Before
    public void option() {
        Configuration.timeout = 40000;
        Configuration.browser = "chrome";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;

        //драйвер из системной переменной
        System.setProperty("webdriver.Chrome.driver",
                System.getenv("CHROMEDRIVER"));

//
//        System.setProperty("webdriver.Chrome.driver",
//                "C:\\Users\\Public\\webDriver\\chromedriver-win64\\chromedriver.exe");
//        WebDriver driver;
//        driver = new ChromeDriver(options);
//        setWebDriver(driver);
//    }

    }
}

