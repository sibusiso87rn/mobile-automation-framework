package starter.org.testing.app.core.appium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import starter.org.testing.app.core.interfaces.IAppiumDriverSetup;
import starter.org.testing.app.core.util.file.PropertiesUtil;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.Properties;

public enum AppiumDriverType implements IAppiumDriverSetup {

    IPHONE {
        public DesiredCapabilities getDesiredCapabilities(Properties appiumProperties,String pathToAppFile, boolean debug){

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, true);
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, appiumProperties.getProperty("device.name", "iPhone Simulator"));
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, appiumProperties.getProperty("phone.platform.version"));
            capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
            capabilities.setCapability(MobileCapabilityType.UDID, appiumProperties.getProperty("device.id"));

            //Set locale settings
            capabilities.setCapability(MobileCapabilityType.LANGUAGE,appiumProperties.getProperty("language"));
            capabilities.setCapability(MobileCapabilityType.LOCALE,appiumProperties.getProperty("locale"));

            //Settings for real iOS devices
            if(!Boolean.getBoolean("simulator")){
                capabilities.setCapability("xcodeOrgId", appiumProperties.getProperty("xcode.org.id"));
                capabilities.setCapability("xcodeSigningId",appiumProperties.getProperty("xcode.sign.id"));
            }

            //Install application based on property install.app equals true
            if(Boolean.getBoolean("simulator")){
                if (Boolean.parseBoolean(PropertiesUtil.getProperty("install.app"))) {
                    capabilities.setCapability(MobileCapabilityType.APP, pathToAppFile);
                }else {
                    capabilities .setCapability("fullReset","false");
                }
            }

            capabilities.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, true);
            capabilities.setCapability(IOSMobileCapabilityType.NATIVE_WEB_TAP, true);
            capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, appiumProperties.getProperty("bundle.id"));
            capabilities.setCapability("realDeviceLogger", realDeviceLogger);

            if (debug) {
                capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "3600");
            }

            return capabilities;
        }

        public RemoteWebDriver getWebDriverObject(URL appiumServerURL, DesiredCapabilities capabilities) {

            return new IOSDriver<>(appiumServerURL, capabilities);
        }
    },
    ANDROID {
        public DesiredCapabilities getDesiredCapabilities(Properties appiumProperties,String pathToAppFile, boolean debug) {

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, true);
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, appiumProperties.getProperty("device.name", "Android Simulator"));
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, appiumProperties.getProperty("phone.platform.version"));
            capabilities.setCapability(MobileCapabilityType.UDID, appiumProperties.getProperty("device.id", "DEFAULT_ANDROID_DEVICE_ID"));
            capabilities.setCapability(MobileCapabilityType.APP, pathToAppFile);
            capabilities.setCapability("appActivity",appiumProperties.getProperty("app.activity"));
            capabilities.setCapability("appPackage",appiumProperties.getProperty("app.package"));

            if (debug) {
                capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "3600");
            }
            return capabilities;
        }

        public RemoteWebDriver getWebDriverObject(URL appiumServerURL, DesiredCapabilities capabilities) {

            return new AndroidDriver<>(appiumServerURL, capabilities);
        }
    },
    IPAD {
        public DesiredCapabilities getDesiredCapabilities(Properties appiumProperties,String pathToAppFile, boolean debug) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, true);
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, appiumProperties.getProperty("device.name", "iPhone Simulator"));
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, appiumProperties.getProperty("phone.platform.version"));
            capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
            capabilities.setCapability(MobileCapabilityType.UDID, appiumProperties.getProperty("device.id", "DEFAULT_IPAD_DEVICE_ID"));
            capabilities.setCapability(MobileCapabilityType.APP, pathToAppFile);
            capabilities.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, true);
            capabilities.setCapability(IOSMobileCapabilityType.NATIVE_WEB_TAP, true);
            capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, appiumProperties.getProperty("device.id", "BUNDLE_ID"));
            capabilities.setCapability("realDeviceLogger", realDeviceLogger);

            //Set locale settings
            capabilities.setCapability(MobileCapabilityType.LANGUAGE,appiumProperties.getProperty("language"));
            capabilities.setCapability(MobileCapabilityType.LOCALE,appiumProperties.getProperty("locale"));

            if (debug) {
                capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "3600");
            }

            return capabilities;
        }

        public RemoteWebDriver getWebDriverObject(URL appiumServerURL, DesiredCapabilities capabilities) {
            return new IOSDriver<>(appiumServerURL, capabilities);
        }
    }
}