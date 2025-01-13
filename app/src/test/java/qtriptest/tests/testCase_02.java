package qtriptest.tests;

import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.net.MalformedURLException;
import java.net.URL;
import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ExternalDataProvider;
import java.util.concurrent.TimeUnit;

public class testCase_02
{
    RemoteWebDriver driver;
    HomePage homePage;
    AdventurePage adventurePage;
    SoftAssert softAssert;
    
    @BeforeTest(alwaysRun = true)
    public void createDriver() throws MalformedURLException
    {
        driver = DriverSingleton.getDriver();
    }

    @Test(dataProvider="qtripData02", dataProviderClass=ExternalDataProvider.class, priority = 2, groups = {"Search and Filter flow"},description = "Saerch and filter flow test case")
    public void TestCase02(String city,String category,String duration,String expectedFilteredResult,String expectedUnFilteredResult) throws InterruptedException
    {
    
        homePage = new HomePage(driver);

        adventurePage = new AdventurePage(driver);

        softAssert = new SoftAssert();

        homePage.gotoHomePage();
        
        Thread.sleep(4000);
        
        homePage.searchForCity(city);

        softAssert.assertTrue(homePage.onCityFound(),"City Not Found!");

        adventurePage.selectAdventureWithFilters(duration, category);

        softAssert.assertTrue(adventurePage.verifyAdventuresForFilteredResults(expectedFilteredResult),"Data Mis-Match!");

        adventurePage.selectAdventureWithoutFilters();

        softAssert.assertTrue(adventurePage.verifyAdventuresForUnFilteredResults(expectedUnFilteredResult),"Data Mis-Match!");

        softAssert.assertAll();
    }

    
 }
