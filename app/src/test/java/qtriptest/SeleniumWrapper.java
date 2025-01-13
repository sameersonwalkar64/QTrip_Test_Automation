package qtriptest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumWrapper {

    public static boolean click(WebElement elementToClick, WebDriver driver)
    {
        try
        {
            if(elementToClick.isDisplayed())
            {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                //js.executeScript("window.scrollBy(0,250)", elementToClick);
                js.executeScript("arguments[0].scrollIntoView(true);", elementToClick);
                elementToClick.click();
                return true;
            }
            //return false;
            
        }
        catch(Exception e)
        {
            e.getMessage();
            //return false;
            
        }
        return false;
    }

    public static boolean sendKeys(WebElement inputBox, String keysToSend)
    {
        try
        {
            inputBox.clear();
            inputBox.sendKeys(keysToSend);
            return true;
        }
        catch(Exception e)
        {
            e.getMessage();
            return false;
        }
    
    }

    public static  boolean navigate(WebDriver driver, String url)
    {
        if(!(driver.getCurrentUrl().equals(url)))
        {
            driver.get(url);
            if(driver.getCurrentUrl().equals(url))
            {
                return true;
            }
            //return false;
        }
        return false;
    }

    public static WebElement findElementWithRetry(WebDriver driver, By by, int retryCount)
    {
        WebElement e = driver.findElement(by);
        while(retryCount<=3)
        {
            try
            {
                return e;
            }
            catch(NoSuchElementException e1)
            {
                retryCount++;
            }
           
        }
        return e;

    }
}
