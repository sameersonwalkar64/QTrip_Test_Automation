package qtriptest;


import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;


public class DriverSingleton 
{
    private static RemoteWebDriver driver  = null;

    private DriverSingleton(){

    }

     public static RemoteWebDriver getDriver() throws MalformedURLException{

       if(driver==null){
        
             ChromeOptions options  = new ChromeOptions();
             options.addArguments("--no-sandbox");
             options.addArguments("--disable-gpu");

             driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), options);
             driver.manage().window().maximize();
             driver.manage().deleteAllCookies();

       }

       return driver;


    }
}