package qtriptest.tests;
import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ExternalDataProvider;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class testCase_04 {

    RemoteWebDriver driver;
    HomePage homePage;
    RegisterPage registerPage;
    LoginPage loginPage;
    SoftAssert softAssert;
    AdventurePage adventurePage;
    AdventureDetailsPage adventureDetailsPage;
    HistoryPage historyPage;
    
    @BeforeTest(alwaysRun = true)
    public void createDriver() throws MalformedURLException
    {
        driver = DriverSingleton.getDriver();
    }

    @Test(dataProvider="qtripData04", dataProviderClass=ExternalDataProvider.class, priority = 4, groups = {"Reliability Flow"},description = "Reliability flow test case")
    public void TestCase04(String userName,String password,String dataset1, String dataset2, String dataset3) throws InterruptedException
    {
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver, registerPage);
        adventurePage = new AdventurePage(driver);
        adventureDetailsPage = new AdventureDetailsPage(driver);
        historyPage = new HistoryPage(driver);

        softAssert = new SoftAssert();

        driver.get("https://qtripdynamic-qa-frontend.vercel.app/");

        homePage.navigateToRegisterPage();

        softAssert.assertTrue(registerPage.isRegisterPageNavigationSucceded(),"Register page navigation is failed!");

        //registerPage.registerUser("lakshman27", "Lakshman@27", "Lakshman@27", true);
        registerPage.registerUser(userName, password, password, true);

        Thread.sleep(5000);

        softAssert.assertTrue(loginPage.isLoginPageNavigationSucceded(), "Login page navigation is failed!");

        loginPage.performLogin(registerPage.lastGeneratedUsername,password);

        String [] datasetArr1 = dataset1.split(";");
        String [] datasetArr2 = dataset2.split(";");
        String [] datasetArr3 = dataset3.split(";");

        List <String[]> dataSet = Arrays.asList(datasetArr1, datasetArr2, datasetArr3);

        for(int i = 0 ; i<dataSet.size() ; i++)
        {
            Thread.sleep(5000);
            homePage.searchForCity(dataSet.get(i)[0]);

            softAssert.assertTrue(homePage.onCityFound(),"City Not Found!");
            Thread.sleep(5000);
            adventurePage.searchAdventureWithName(dataSet.get(i)[1]);
            Thread.sleep(5000);
            adventureDetailsPage.reserveAdventure(dataSet.get(i)[2], dataSet.get(i)[3], dataSet.get(i)[4]);
            Thread.sleep(5000);
            softAssert.assertTrue(adventureDetailsPage.verifyBookingSuccessful(),"Booking Unsuccessful!");
            Thread.sleep(5000);
            homePage.gotoHomePage();
            
        }

        adventureDetailsPage.clickOnHereButton();

        List<WebElement> totalBookings=historyPage.bookings();

        softAssert.assertTrue(dataSet.size()==totalBookings.size(), "Bookings are not matching !");

        //System.out.println(totalBookings.size());

        homePage.performLogout();

        softAssert.assertAll();
    }
    
}
