package qtriptest.pages;


import qtriptest.SeleniumWrapper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AdventureDetailsPage {

    RemoteWebDriver driver;

    public AdventureDetailsPage(RemoteWebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    @FindBy(xpath="//input[@name='name']")
    private WebElement nameTextBox;

    @FindBy(xpath="//input[@name='date']")
    private WebElement pickDateWebElement;

    @FindBy(xpath="//input[@type='number']")
    private WebElement totalCountOfPersonsTextBox;

    @FindBy(xpath="//button[text()='Reserve']")
    private WebElement reserveButton;

    @FindBy(xpath = "//div[contains(text() , 'Greetings! Reservation for this adventure is successful.')]")
    private WebElement successAlertElement ;

    //@FindBy(xpath="//strong[text()='here']")
    @FindBy(xpath="//a[text()='Reservations']")
    private WebElement reservationsButton;

    public void reserveAdventure(String guestName,String date,String totalcountofPersons)
    {
        //nameTextBox.click();
        SeleniumWrapper.click(nameTextBox, driver);
        //nameTextBox.sendKeys(guestName);
        SeleniumWrapper.sendKeys(nameTextBox, guestName);

        //pickDateWebElement.click();
        SeleniumWrapper.click(pickDateWebElement, driver);
        //pickDateWebElement.sendKeys(date);
        SeleniumWrapper.sendKeys(pickDateWebElement, date);

        //totalCountOfPersonsTextBox.click();
        SeleniumWrapper.click(totalCountOfPersonsTextBox, driver);
        //totalCountOfPersonsTextBox.sendKeys(totalcountofPersons);
        SeleniumWrapper.sendKeys(totalCountOfPersonsTextBox, totalcountofPersons);

        //reserveButton.click();
        SeleniumWrapper.click(reserveButton, driver);

    }

    public Boolean verifyBookingSuccessful() throws InterruptedException{
        Thread.sleep(2000);
        return successAlertElement.isDisplayed();
    }

    public void clickOnHereButton() throws InterruptedException
    {
        //reservationsButton.click();
        SeleniumWrapper.click(reservationsButton, driver);
        Thread.sleep(3000);
    }

}