package hw10.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import service.RestSpellerService;

public class BaseTest {

    RestSpellerService restSpellerService;

    @BeforeSuite(alwaysRun = true)
    public void setUpRestSpellerService() {
        if (restSpellerService == null) {
            restSpellerService = new RestSpellerService();
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownRestSpellerService() {
        restSpellerService = null;
    }
}
