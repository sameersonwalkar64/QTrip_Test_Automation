package qtriptest.pages;


import java.util.UUID;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import java.sql.Timestamp;


public class RegisterPage {

    RemoteWebDriver driver;

    public RegisterPage(RemoteWebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    private static String registerPageEndpoint = "/pages/register/";
    
    public String userName = null;

    public String lastGeneratedUsername = "";

    @FindBy(css="h2.formtitle")
    private WebElement registerCardHeader;

    @FindBy(xpath="//input[@name='email']")
    private WebElement emailTextBox;

    @FindBy(xpath="//input[@name='password']")
    private WebElement passwordTextBox;

    @FindBy(xpath="//input[@name='confirmpassword']")
    private WebElement confirmpasswordTextBox;

    @FindBy(xpath="//button[text()='Register Now']")
    private WebElement registerNowButton;

    public boolean isRegisterPageNavigationSucceded() throws InterruptedException
    {
        Thread.sleep(5000);
        return driver.getCurrentUrl().contains(registerPageEndpoint) && 
        registerCardHeader.getText().equals("Register");
    }

    public void registerUser(String userName,String password,String confirmPassword,boolean isDynamicUser)
    {
        Actions actions = new Actions(driver);

        String test_data_username;

        UUID uuid = UUID.randomUUID();

        //From Crio Workspace
        // if (generateRandomUsername = = true)
        // userName = userName+UUID.randomUUID().toString();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        if(isDynamicUser)
        {
            //userName = String.format("testemail_%s@gmail.com",UUID.randomUUID().toString());
            //userName = String.format(userName,UUID.randomUUID().toString());
            //test_data_username = userName + String.valueOf(timestamp.getTime()) +"@gmail.com";
            test_data_username = uuid + userName;
        }
        else{
            test_data_username = userName;
        }

        //this.emailTextBox.click();
        this.lastGeneratedUsername = test_data_username;

        actions.moveToElement(this.emailTextBox).click().
        sendKeys(this.emailTextBox, test_data_username).build().perform();

        actions.moveToElement(this.passwordTextBox).click().
        sendKeys(this.passwordTextBox, password).build().perform();

        actions.moveToElement(this.confirmpasswordTextBox).click().
        sendKeys(this.confirmpasswordTextBox, confirmPassword).build().perform();

        actions.moveToElement(this.registerNowButton).click().build().perform();

        
    }
}
