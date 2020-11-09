package starter.testing.core.driver.appium;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;

public class AppiumServer {

    private static final Logger logger = LoggerFactory.getLogger(AppiumServer.class);

    private static AppiumDriverLocalService service;
    private static URL serverURL       = null;
    private static boolean isRunning   = false;

    public static URL startAppiumServer() {

        String osName = System.getProperty("os.name").toLowerCase();
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();

        String nodePath   = null;
        String appiumPath = null;

        if (osName.contains("mac")) {
            //mac path
            nodePath    = "/usr/local/bin/node";
            appiumPath  = "/usr/local/lib/node_modules/appium/build/lib/main.js";
        } else if (osName.contains("linux")) {
            //linux path
            nodePath    = System.getenv("HOME") + "/.linuxbrew/bin/node";
            appiumPath  = System.getenv("HOME") + "/.linuxbrew/lib/node_modules/appium/build/lib/main.js";
        }

        logger.info("--------------------------------");
        logger.info("About to start the appium server");
        logger.info("--------------------------------");

        //Use any port, in case the default 4723 is already taken (maybe by another Appium server)
        serviceBuilder.usingDriverExecutable(new File(nodePath));
        serviceBuilder.withAppiumJS(new File(appiumPath));
        serviceBuilder.usingAnyFreePort();

        service = AppiumDriverLocalService.buildService(serviceBuilder);
        service.start();

        logger.info("---------------------------------------------");
        logger.info("Appium server running on: " + service.getUrl());
        logger.info("---------------------------------------------");

        isRunning = service.isRunning();
        serverURL = service.getUrl();
        return serverURL;
    }

    public static void stopAppiumServer() {
        service.stop();
        isRunning = service.isRunning();
    }

    public static URL getServerURL(){
        return serverURL;
    }

    public static boolean isServerRunning(){
        return isRunning;
    }

}
