package starter.testing.tests.testng;

import com.aventstack.extentreports.service.ExtentTestManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;
import starter.testing.TestNGBaseTest;
import starter.testing.actions.ActionLandingPage;

public class AddTest extends TestNGBaseTest {

    private static final Logger logger = LogManager.getLogger(AddTest.class);

    private String testConfiguration = null;

    @BeforeClass(alwaysRun = true)
    @Parameters({"test.configuration"})
    public void getTestConfiguration(String testConfiguration) {
        this.testConfiguration = testConfiguration;
    }

    @Test(description = "Basic user input test")
    public void TestOne() throws Exception {
        System.out.println(ExtentTestManager.getTest().getModel().getDescription());
        createTestDriver(testConfiguration);
        new ActionLandingPage().
                setFirstNumber("10").
                setSecondNumber("10").
                validateSum("20");
    }

    @Test(description = "Input test with big numbers")
    public void TestTwo() throws Exception {
        createTestDriver(testConfiguration);
        new ActionLandingPage().
                setFirstNumber("-14").
                setSecondNumber("12").
                validateSum("-2");
    }

    @Test(description = "Input test with big numbers", dataProvider = "calculator-numbers")
    public void TestThree(String firstnumber,String secondnumber, String expectedResults) throws Exception {
        createTestDriver(testConfiguration);
        new ActionLandingPage().
                setFirstNumber(firstnumber).
                setSecondNumber(secondnumber).
                validateSum(expectedResults);
    }

    @DataProvider (name = "calculator-numbers")
    public Object[][] calculatorNumbers(){
        return new Object[][] {{"100","300","400"},{"10","30","40"}};
    }

}
