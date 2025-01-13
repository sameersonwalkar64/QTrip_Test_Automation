package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage {

    RemoteWebDriver driver;
    //AdventurePage adventurePage;
    private static final int pageLoadTimeout=20;

    //String url = "https://qtripdynamic-qa-frontend.vercel.app/";

    public HomePage(RemoteWebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    @FindBy(xpath="//a[text()='Register']")
    private WebElement registerButton;
    
    @FindBy(xpath="//a[text()='Login Here']")
    private WebElement loginHereButton;

    @FindBy(xpath="//div[text()='Logout']")
    private WebElement logoutButton;

    // public void naviagteToHomePage()
    // {
    //     if (!this.driver.getCurrentUrl().equals(this.url)) 
    //     {
    //         this.driver.get(this.url);
    //     }
    // }
    public void gotoHomePage() throws InterruptedException {
        driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
        driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public boolean isRegisterButtonVisible() throws InterruptedException
    {
        Thread.sleep(5000);
        return registerButton.isDisplayed() && registerButton.isEnabled();
    }

    public void navigateToRegisterPage()
    {
        System.out.println("Navigating to register page");
        //registerButton.click();
        SeleniumWrapper.click(registerButton, driver);
        
        // Actions actions=new Actions(driver);
        // actions.moveToElement(this.registerButton).click().build.perform();
    }

    public boolean isSpecifiedMenubarOptionVisible() throws InterruptedException
    {
        Thread.sleep(5000);

        // Actions actions=new Actions(driver);
        // actions.moveToElement(this.logoutButton).click().build.perform();

        System.out.println(logoutButton.getText());
        return logoutButton.getText().equals("Logout");
    }

    @FindBy(id="autocomplete")
    private WebElement searchTextBox;

    @FindBy(xpath="//h5[text()='No City found']")
    private WebElement onNoCityFound;

    @FindBy(xpath="//ul[@id='results']//li")
    private WebElement onCityFound;

    //Actions actions=new Actions(driver);
    
    public void searchForCity(String city) throws InterruptedException
    {
        
        //Actions actions=new Actions(driver);
        // actions.moveToElement(this.searchTextBox).click().
        // sendKeys(this.searchTextBox,city).build().perform();
        Thread.sleep(2000);
        // searchTextBox.click();
        searchTextBox.clear();
        Thread.sleep(2000);
        //searchTextBox.sendKeys(city);
        SeleniumWrapper.sendKeys(searchTextBox, city);
        Thread.sleep(2000);
        

    }

    public boolean onNoCityFound()
    {
        boolean status = false;
        try
        {
            status = onNoCityFound.isDisplayed();
            return status;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return status;
        }
    }

    public boolean onCityFound()
    {
        boolean status = false;
        try
        {
            status = onCityFound.isDisplayed();
            Thread.sleep(2000);
            //onCityFound.click();
            SeleniumWrapper.click(onCityFound, driver);
            //Thread.sleep(5000);
            return status;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return status;
        }
    }

    // public boolean no_City_Found(String string) {
    //     return false;
    // }

    public void performLogout()
    {
        //logoutButton.click();
        SeleniumWrapper.click(logoutButton, driver);
    }

}
