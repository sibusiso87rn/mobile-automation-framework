package starter.testing;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;
import starter.testing.core.bean.ApplicationContext;
import starter.testing.core.util.environment.EnvironmentConfig;
import starter.testing.core.util.environment.TestConfigurationProperty;
import starter.testing.core.util.report.CucumberReport;

/**
 * Created by Sibusiso Radebe on 2020/02/20.
 */
@CucumberOptions(
        plugin  = {
                "starter.testing.core.util.report.ExtentReportListener",
                "pretty",
                "json:target/cucumber-report.json",
        },
        features    = {"src/test/resources/features" },
        glue        = {""},
        tags        = ""
)
@ContextConfiguration(locations = {"classpath:spring-bean.xml"})
public class CucumberExecutionEngine extends AbstractTestNGSpringContextTests {

    private TestNGCucumberRunner testNGCucumberRunner;
    private static final Logger logger = LogManager.getLogger(CucumberExecutionEngine.class);


    @BeforeClass(alwaysRun = true)
    @Parameters({"environment","test.configuration","device","mobile.os.version"})
    public void setTestNGProperties(String environment,String testConfiguration,String device,String mobileOSVersion) throws Exception{

        //Read device details from the testcase on testng xml
        System.setProperty("environment",environment);

        //Initialize environment configs
        EnvironmentConfig.getInstance();

        //Initialize driver properties
        logger.info("Creating test configuration using {}",testConfiguration);
        TestConfigurationProperty.createTestConfigProperties(testConfiguration);
        TestConfigurationProperty.getThreadLocalProperties().setProperty("device.name",device);
        TestConfigurationProperty.getThreadLocalProperties().setProperty("mobile.os.version",mobileOSVersion);

        //Read device details from the testcase on testng xml
        System.setProperty("configs.set","true");

        ApplicationContext.getTestBean().createDriver();

        //Launch
        logger.info("Finished setting the TestNG properties.");
    }

    @BeforeClass(alwaysRun = true)
    public void setUpClass(){
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "cucumber scenarios", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void scenario(PickleWrapper pickleEvent, FeatureWrapper cucumberFeature) throws Throwable {
        testNGCucumberRunner.runScenario(pickleEvent.getPickle());
    }

    @DataProvider
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        testNGCucumberRunner.finish();

        //Quit appium driver
        ApplicationContext.getTestBean().quitAppiumDriver();

        //Create and finalize the report - This is done once, only after the tests have been completed.
        new CucumberReport().createReport();
    }

    @AfterSuite(alwaysRun = true)
    public void tearCreateReport() {
    }
}
