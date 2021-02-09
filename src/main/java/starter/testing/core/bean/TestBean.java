package starter.testing.core.bean;

import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import starter.testing.core.driver.appium.AppiumFactory;
import starter.testing.core.util.environment.TestConfigurationProperty;
import starter.testing.core.util.report.config.ReportConfig;

import java.util.Properties;

@Component
@Configuration
@ConditionalOnProperty(
        value="configs.set",
        havingValue = "true"
)
public class TestBean {

    private static final Logger logger = LogManager.getLogger(TestBean.class);

    //Set the driver before the test starts
    @Bean
    public TestBean createTestBean(){
        return new TestBean();
    }

    @Bean
    public ReportConfig reportConfig() {
        return new ReportConfig();
    }

    public WebDriver getAppiumDriver(){
       return AppiumFactory.getInstance().getThreadLocalAppiumDriver();
    }

    public void quitAppiumDriver(){
        AppiumFactory.getInstance().getThreadLocalAppiumDriver().quit();
        TestConfigurationProperty.cleanup();
    }


    public void createDriver() throws Exception {
        logger.info("Creating thread local driver");
        AppiumFactory.getInstance().createAppiumDriver();
    }

}
