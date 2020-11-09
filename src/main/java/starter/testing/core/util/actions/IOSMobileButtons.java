package starter.org.testing.app.core.actions;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import starter.org.testing.app.core.interfaces.IMobileButtons;

/**Emulates the IOS Mobile key events*/
public class IOSMobileButtons implements IMobileButtons {

    private static final Logger logger = LoggerFactory.getLogger(IOSMobileButtons.class);

    @Override
    public void pressHomeButton(AppiumDriver<?> driver) {
        logger.info("Pressing the Home button");
        driver.executeScript("mobile: pressButton", ImmutableMap.of("name", "home"));
    }
}
