
package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HistoryPage 
{
    RemoteWebDriver driver;

    public HistoryPage(RemoteWebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    @FindBy(xpath="//tbody/tr/th")
    private WebElement transactionID;

    @FindBy(xpath="//button[text()='Cancel']")
    private WebElement cancelButton;

    @FindBy(xpath = "//tbody[@id='reservation-table']//tr")
    private List<WebElement> totalBookings;

   //String transactionValue = transactionID.getText();

    public void cancelAdventureReservationAndRefreshPage() throws InterruptedException
    {
        Thread.sleep(2000);
        //cancelButton.click();
        SeleniumWrapper.click(cancelButton, driver);
        if(!transactionID.isDisplayed()){

            System.out.println("Booking Cancellation is successful");
            //return true;
        }
        else{
            
            System.out.println(" Booking Cancellation failed");
            //return false;

        }
        driver.navigate().refresh();
        Thread.sleep(3000);
        Alert alert=driver.switchTo().alert();
        alert.accept();
    
    }

    

    public List<WebElement> bookings()
    {
        return totalBookings;
    }


}