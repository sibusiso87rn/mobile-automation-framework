package starter.testing.core.interfaces;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.Properties;

public interface IAppiumDriverSetup {

    String realDeviceLogger = "/usr/local/lib/node_modules/deviceconsole/deviceconsole";

    RemoteWebDriver getWebDriverObject(URL appiumServerURL, DesiredCapabilities desiredCapabilities);

    DesiredCapabilities getDesiredCapabilities(Properties appiumProperties, String pathToAppFile, boolean debug);
}