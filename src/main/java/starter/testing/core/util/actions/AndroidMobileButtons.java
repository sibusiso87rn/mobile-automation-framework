package starter.testing.core.util.actions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import starter.testing.core.interfaces.IMobileButtons;

/**Emulates the Android Mobile key events*/
public class AndroidMobileButtons implements IMobileButtons {

    private static final Logger logger = LoggerFactory.getLogger(AndroidMobileButtons.class);

    @Override
    public void pressHomeButton(AppiumDriver<?> driver) {
        logger.info("Pressing the Home button");
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.HOME));
    }
}
