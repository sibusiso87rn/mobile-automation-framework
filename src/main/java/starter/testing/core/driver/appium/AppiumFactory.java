package starter.testing.core.driver.appium;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import starter.testing.core.util.environment.TestConfigurationProperty;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class AppiumFactory {

    private static AppiumFactory appiumFactoryInstance = null;
    private static final Logger logger                 = LoggerFactory.getLogger(AppiumFactory.class);
    private final AppiumDriverType defaultDriverType   = AppiumDriverType.IPHONE;
    private static final ThreadLocal<WebDriver> appiumDriverThreadLocal   = new ThreadLocal<>();


    @Autowired
    private TestConfigurationProperty testConfigurationProperty;

    private AppiumFactory(){
    }

    public static AppiumFactory getInstance(){
        if(appiumFactoryInstance==null){
            logger.info("Appium factory is instance is null, creating new instance");
            appiumFactoryInstance = new AppiumFactory();
        }
        return appiumFactoryInstance;
    }

    public AppiumDriver<?> getThreadLocalAppiumDriver(){
        return (AppiumDriver<?>) appiumDriverThreadLocal.get();
    }

    public void createAppiumDriver() throws Exception {
        Properties properties = testConfigurationProperty.getThreadLocalProperties();
        String appiumConfig   = properties.getProperty("driver", defaultDriverType.toString()).toUpperCase();
        boolean setDebugMode  = Boolean.getBoolean(properties.getProperty("debug.mode"));
        String pathToAppFile  = properties.getProperty("binary.path");
        logger.info("Using app binary from location {}",pathToAppFile);
        AppiumDriverType    selectedDriverType  = determineEffectiveDriverType(appiumConfig);
        DesiredCapabilities desiredCapabilities = selectedDriverType.getDesiredCapabilities(properties,pathToAppFile,setDebugMode);
        appiumDriverThreadLocal.set(instantiateWebDriver(desiredCapabilities,selectedDriverType,properties));
    }

    /**
     * Determines the driver type.
     * **/
    private AppiumDriverType determineEffectiveDriverType(String appiumConfig) {
        AppiumDriverType driverType = defaultDriverType;
        try {
            driverType = AppiumDriverType.valueOf(appiumConfig);
        } catch (IllegalArgumentException ignored) {
            logger.error("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException ignored) {
            logger.error("No driver specified, defaulting to '" + driverType + "'...");
        }
        return driverType;
    }

    private RemoteWebDriver instantiateWebDriver(DesiredCapabilities desiredCapabilities,AppiumDriverType driverType,Properties driverProperties) throws MalformedURLException {
        String appiumServerLocation = driverProperties.getProperty("appium.server.location", "http://127.0.0.1:4723/wd/hub");
        boolean useRemoteWebDriver = Boolean.getBoolean(driverProperties.getProperty("remote.driver"));

        logger.info("Current Appium Config Selection: " + driverType);
        logger.info("Current Appium Server Location: " + appiumServerLocation);

        if (useRemoteWebDriver) {
            logger.info("****Using remote driver****");
            URL seleniumGridURL = new URL(System.getProperty("gridURL"));
            String desiredVersion = driverProperties.getProperty("desiredVersion");
            String desiredPlatform = driverProperties.getProperty("desiredPlatform");

            if (null != desiredPlatform && !desiredPlatform.isEmpty()) {
                desiredCapabilities.setPlatform(Platform.valueOf(desiredPlatform.toUpperCase()));
            }

            if (null != desiredVersion && !desiredVersion.isEmpty()) {
                desiredCapabilities.setVersion(desiredVersion);
            }
            return new RemoteWebDriver(seleniumGridURL, desiredCapabilities);
        } else {
            return driverType.getWebDriverObject(new URL(appiumServerLocation), desiredCapabilities);
        }

    }

    public void quitThreadLocalAppiumDriver(){
        logger.debug("Quiting web driver");
        getThreadLocalAppiumDriver().quit();
        logger.debug("Removing driver instance");
        appiumDriverThreadLocal.remove();
    }

}

