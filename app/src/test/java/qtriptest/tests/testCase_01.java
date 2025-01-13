package qtriptest.tests;


import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.net.URL;
import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ExternalDataProvider;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase_01 
{   
    RemoteWebDriver driver;
    HomePage homePage;
    RegisterPage registerPage;
    LoginPage loginPage;
    SoftAssert softAssert;

    @BeforeTest(alwaysRun = true)
    public void createDriver() throws MalformedURLException
    {
        driver = DriverSingleton.getDriver();
    }
    
    @Test(enabled = true, dataProvider="qtripData01", dataProviderClass=ExternalDataProvider.class,priority = 1,description = "Login flow test case",groups = {"Login Flow"})
    public void TestCase01(String username, String password) throws InterruptedException
    {
         //RemoteWebDriver driver = DriverSingleton.getDriver();
        
         homePage = new HomePage(driver);
         registerPage = new RegisterPage(driver);
         loginPage = new LoginPage(driver, registerPage);
 
         softAssert = new SoftAssert();
 
         homePage.gotoHomePage();
         
         softAssert.assertTrue(homePage.isRegisterButtonVisible(), "Register button on Homepage not found!");
 
         homePage.navigateToRegisterPage();
 
         softAssert.assertTrue(registerPage.isRegisterPageNavigationSucceded(),"Register page navigation is failed!");

        //registerPage.registerUser("sameer64", "Sameer@64", "Sameer@64", true);
        registerPage.registerUser(username, password, password, true);

        Thread.sleep(5000);

        softAssert.assertTrue(loginPage.isLoginPageNavigationSucceded(), "Login page navigation is failed!");

        //loginPage.performLogin(registerPage.lastGeneratedUsername,"Sameer@64");
        loginPage.performLogin(registerPage.lastGeneratedUsername, password);

        homePage.performLogout();

        softAssert.assertAll();
    }
}
