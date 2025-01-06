import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.net.MalformedURLException;
import java.net.URL;

public class TesteLogin {

    private static ScreenLogin ScreenLogin;
    private static AppiumDriver driver;

    @BeforeClass
    public static void caps() throws MalformedParameterizedTypeException, MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "13.0");
        capabilities.setCapability("deviceName", "Nexus_5_API_33_2");
        capabilities.setCapability("app", "/Users/ricardo/Downloads/app-debug.apk");
        capabilities.setCapability("autoGrantPermissions", true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        ScreenLogin = new ScreenLogin(driver);
    }

    @Test
    public void testeLogin(){
        ScreenLogin.Logar();
    }
}
