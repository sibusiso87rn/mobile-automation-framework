package starter.testing.core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.LogManager;
import starter.testing.core.bean.ApplicationContext;

public abstract class AbstractBasePage {

    protected AppiumDriver<?> driver;
    private static final Logger logger = LogManager.getLogger(AbstractBasePage.class);

    public AbstractBasePage(){
        driver = ApplicationContext.getTestBean().getAppiumDriver();
        logger.info("Creating page object ["+this.getClass().getSimpleName()+"] for sessionId [" + driver.getSessionId()+"] and threadId ["+Thread.currentThread().getId()+"]");
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

}
