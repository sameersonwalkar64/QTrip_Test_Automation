package qtriptest.tests;


import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.concurrent.TimeUnit;
import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ExternalDataProvider;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.net.URL;



public class testCase_03 {

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

    @Test(dataProvider="qtripData03", dataProviderClass=ExternalDataProvider.class, priority = 3, groups = {"Booking and Cancellation Flow"},description = "Booking and cancellation flow test case")
    public void TestCase03(String userName,String password,String cityName,String adventureName,String guestName,String date,String count) throws InterruptedException
    {
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver, registerPage);
        adventurePage = new AdventurePage(driver);
        adventureDetailsPage = new AdventureDetailsPage(driver);
        historyPage = new HistoryPage(driver);

        softAssert = new SoftAssert();

        // driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
        homePage.gotoHomePage();

        homePage.navigateToRegisterPage();

        softAssert.assertTrue(registerPage.isRegisterPageNavigationSucceded(),"Register page navigation is failed!");

        registerPage.registerUser(userName, password, password, true);


        Thread.sleep(5000);

        softAssert.assertTrue(loginPage.isLoginPageNavigationSucceded(), "Login page navigation is failed!");

        loginPage.performLogin(registerPage.lastGeneratedUsername,password);

        homePage.searchForCity(cityName);

        softAssert.assertTrue(homePage.onCityFound(),"City Not Found!");

        adventurePage.searchAdventureWithName(adventureName);

        adventureDetailsPage.reserveAdventure(guestName, date, count);

        softAssert.assertTrue(adventureDetailsPage.verifyBookingSuccessful(), "Booking Unsuccessful !");

        adventureDetailsPage.clickOnHereButton();

        historyPage.cancelAdventureReservationAndRefreshPage();

        //softAssert.assertTrue(historyPage.verifyIfAdventureCancelled(), "Cancelling the reservation is unsuccessful!");

         homePage.performLogout();

        softAssert.assertAll();
    }


    
}
