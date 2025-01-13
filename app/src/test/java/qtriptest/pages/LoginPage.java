package qtriptest.pages;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage {

    RemoteWebDriver driver;
    RegisterPage registerPage;

    public LoginPage(RemoteWebDriver driver,RegisterPage registerPage)
    {
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
        this.registerPage=registerPage;
    }

    private static String loginPageEndpoint = "/pages/login";
    
    @FindBy(css="h2.formtitle")
    private WebElement loginCardHeader;

    @FindBy(xpath="//input[@name='email']")
    private WebElement emailTextBox;

    @FindBy(xpath="//input[@name='password']")
    private WebElement passwordTextBox;

    @FindBy(xpath="//button[text()='Login to QTrip']")
    private WebElement loginToQTripButton;

    public boolean isLoginPageNavigationSucceded()
    {
        return driver.getCurrentUrl().contains(loginPageEndpoint) &&
        loginCardHeader.getText().equals("Login");
    }

    public void performLogin(String email,String password) throws InterruptedException
    {
            System.out.println("Into Login");
            
            Thread.sleep(5000);
            
            //email=registerPage.lastGeneratedUsername;
            
            Actions actions=new Actions(driver);

            actions.moveToElement(this.emailTextBox).click().
            sendKeys(this.emailTextBox,email).build().perform();

            actions.moveToElement(this.passwordTextBox).click().
            sendKeys(this.passwordTextBox, password).build().perform();

            actions.moveToElement(this.loginToQTripButton).click().
            build().perform();
            Thread.sleep(5000);

    }
}
