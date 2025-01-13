package qtriptest;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class ExternalDataProvider {
    DP dataproviderUtility;

    @DataProvider(name="qtripData01")
    public Object [][] qtripdata01() throws IOException  {
        dataproviderUtility=new DP();
        return dataproviderUtility.dpMethod("TestCase01");
    }

    
    @DataProvider(name="qtripData02")
    public Object [][] qtripdata02() throws IOException  {
        dataproviderUtility=new DP();
        return dataproviderUtility.dpMethod("TestCase02");
    }

    
    @DataProvider(name="qtripData03")
    public Object [][] qtripdata03() throws IOException  {
        dataproviderUtility=new DP();
        return dataproviderUtility.dpMethod("TestCase03");
    }

    
    @DataProvider(name="qtripData04")
    public Object [][] qtripdata04() throws IOException  {
        dataproviderUtility=new DP();
        return dataproviderUtility.dpMethod("TestCase04");
    }
    
    
}
