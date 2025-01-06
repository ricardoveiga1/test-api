import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;

public class ScreenLogin{

    public ScreenLogin(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.example.qazando.espressotests:id/login_username")
    private RemoteWebElement campoEmail;

    @AndroidFindBy(id = "com.example.qazando.espressotests:id/login_password")
    private RemoteWebElement campoSenha;

    @AndroidFindBy(id = "com.example.qazando.espressotests:id/login_button")
    private RemoteWebElement botaoLogin;

    public void Logar(){
        campoEmail.sendKeys("qazando@gmail.com");
        campoSenha.sendKeys("1234");
        botaoLogin.click();
    }

}
