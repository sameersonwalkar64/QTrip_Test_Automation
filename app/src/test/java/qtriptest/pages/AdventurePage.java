package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.util.List;
import java.util.SimpleTimeZone;
import javax.lang.model.util.ElementScanner6;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

public class AdventurePage 
{
    RemoteWebDriver driver;

    public AdventurePage(RemoteWebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    @FindBy(id="duration-select")
    private WebElement durationDropdown;

    @FindBy(xpath="//div[@onclick='clearDuration(event)']")
    private WebElement durationClearButton;

    @FindBy(id="category-select")
    private WebElement categoryDropdown;

    @FindBy(xpath="//div[@onclick='clearCategory(event)']")
    private WebElement categoryClearButton;

    @FindBy(xpath="//input[@id='search-adventures']")
    private WebElement searchAdventureTextBox;

    @FindBy(xpath="//div[@onclick='resetAdventuresData()']")
    private WebElement searchAdventureClearButton;

    @FindBy(xpath="//div[@class='activity-card']")
    private List<WebElement> adventuresCount;

    @FindBy(xpath="//div[@class='activity-card']")
    private WebElement adventure;

    // Select selectDuration=new Select(durationDropdown);

    // Select selectCategory=new Select(categoryDropdown);

    // public boolean selectDuration(String hours)
    // {
    //     durationSelectDropdown.click();
    //     if(hours.equals("0-2 Hours"))
    //     {
    //         selectOptions.selectByVisibleText("0-2 Hours");
            
    //     }
    //     else if(hours.equals("2-6 Hours"))
    //     {
    //         selectOptions.selectByVisibleText("2-6 Hours");
    //     }
    //     else if(hours.equals("6-12 Hours"))
    //     {
    //         selectOptions.selectByVisibleText("6-12 Hours");
    //     }
    //     else
    //     {
    //         selectOptions.selectByVisibleText("12+ Hours");
    //     }
    // }

    public void selectAdventureWithFilters(String duration, String category) throws InterruptedException
    {
        Select selectDuration=new Select(durationDropdown);
        Select selectCategory=new Select(categoryDropdown);
        
        // durationClearButton.click();
        // categoryClearButton.click();
        SeleniumWrapper.click(durationClearButton, driver);
        SeleniumWrapper.click(categoryClearButton, driver);

        selectDuration.selectByVisibleText(duration);
        Thread.sleep(3000);

        selectCategory.selectByVisibleText(category);
        Thread.sleep(3000);

    }

    public void selectAdventureWithoutFilters() throws InterruptedException
    {
        //durationClearButton.click();
        SeleniumWrapper.click(durationClearButton, driver);
        Thread.sleep(2000);
        //categoryClearButton.click();
        SeleniumWrapper.click(categoryClearButton, driver);
        Thread.sleep(2000);
        
    }

    public boolean verifyAdventuresForFilteredResults(String expectedFilteredResults)
    {
        boolean status=false;
        int size=adventuresCount.size();
        int expectedResult = Integer.parseInt(expectedFilteredResults);
        
        try
        {
        if(size==expectedResult)
        {
            status=true;
            
        }
        return status;
    }
    catch(Exception e)
    {
        System.out.println(e.getMessage());
        return status;
    }
}

    public boolean verifyAdventuresForUnFilteredResults(String expectedUnFilteredResults)
    {
        boolean status=false;
        int size=adventuresCount.size();
        int expectedResult = Integer.parseInt(expectedUnFilteredResults);
        try
        {
        if(size==expectedResult)
        {
            status=true;
            
        }
        return status;
    }
    catch(Exception e)
    {
        System.out.println(e.getMessage());
        return status;
    }
}

public void searchAdventureWithName(String adventureName) throws InterruptedException
{
   
    Thread.sleep(2000);

   //searchAdventureTextBox.sendKeys(adventureName);
   SeleniumWrapper.sendKeys(searchAdventureTextBox, adventureName);

   Thread.sleep(3000);

   //adventure.click();
   SeleniumWrapper.click(adventure, driver);

}

    // public boolean total_results_displayed(String expextedFilteredResults) {
    //     return false;
    // }

    // public void set_hours(String durationFilter) {}

    // public void clear_filters() {}

    // public void set_category(String category_Filter) {}

    
}