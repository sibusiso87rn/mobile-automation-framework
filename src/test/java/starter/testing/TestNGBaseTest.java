package starter.testing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;
import starter.testing.core.bean.ApplicationContext;
import starter.testing.core.util.environment.EnvironmentConfig;
import starter.testing.core.util.environment.TestConfigurationProperty;
import starter.testing.core.util.report.config.ReportConfig;
import starter.testing.core.util.report.extent.ExtentManagerAdapter;

@Listeners({ExtentManagerAdapter.class})
@ContextConfiguration(locations = {"classpath:spring-bean.xml"})
public abstract class TestNGBaseTest extends AbstractTestNGSpringContextTests{

    private static final Logger logger = LogManager.getLogger(TestNGBaseTest.class);

    @BeforeClass(alwaysRun = true)
    public void initSpringProps() {
        System.setProperty("configs.set", "true");
        ApplicationContext.getTestBean();
        ApplicationContext.getComponent(ReportConfig.class);
    }

    @BeforeTest
    @Parameters({"environment","test.configuration"})
    public void initEnvironmentConfig(String environment,String testConfiguration) throws Exception {
        System.setProperty("environment",environment);
        //Initialize environment configs
        EnvironmentConfig.getInstance();
    }

    @AfterMethod(alwaysRun = true)
    public void quitAppiumDriver() {
        //Quit appium driver
        logger.info("Quiting driver for Thread ID {}", Thread.currentThread().getId());
        ApplicationContext.getTestBean().quitAppiumDriver();
    }

    public void createTestDriver(String testConfiguration) throws Exception {
        //Initialize driver properties
        logger.info("Creating test configuration using {}",testConfiguration);
        TestConfigurationProperty.createTestConfigProperties(testConfiguration);
        //TestConfigurationProperty.getThreadLocalProperties().setProperty("device.name",device);
        logger.info("Creating driver for Thread ID {}",Thread.currentThread().getId());
        ApplicationContext.getTestBean().createDriver();
    }

    public void createTestDriver(String testConfiguration,String browserStackTestName) throws Exception {
        //Initialize driver properties
        logger.info("Creating test configuration using {}",testConfiguration);
        TestConfigurationProperty.createTestConfigProperties(testConfiguration);
        //TestConfigurationProperty.getThreadLocalProperties().setProperty("device.name",device);
        logger.info("Creating driver for Thread ID {}",Thread.currentThread().getId());
        ApplicationContext.getTestBean().createDriver();
    }



}
