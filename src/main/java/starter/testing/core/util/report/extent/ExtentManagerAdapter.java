package starter.testing.core.util.report.extent;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Attribute;
import com.aventstack.extentreports.service.ExtentService;
import com.aventstack.extentreports.service.ExtentTestManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import starter.testing.core.bean.ApplicationContext;
import starter.testing.core.util.report.config.ReportConfig;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ExtentManagerAdapter implements ITestListener {

    private static ReportConfig reportConfig = null;
    private static final Logger logger  = LoggerFactory.getLogger(ExtentManagerAdapter.class);
    private static Boolean runStarted   = Boolean.FALSE;

    @Override
    public synchronized void onStart(ITestContext context) {
        logger.info("Logging Test {} on Report ",context.getName());
        ExtentService.getInstance().setAnalysisStrategy(AnalysisStrategy.CLASS);
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        setReportName();
        attachScreenShot();
        ExtentService.getInstance().flush();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        ExtentTestManager.createMethod(result, false);
        if(reportConfig==null){
            reportConfig = (ReportConfig) ApplicationContext.getComponent(ReportConfig.class);
        }
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest(result).pass("Test Passed");
        ExtentTestManager.getTest(result).assignDevice((reportConfig.getDeviceName()));
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        ExtentTestManager.getTest(result).fail(result.getThrowable());
        ExtentTestManager.getTest(result).assignDevice((reportConfig.getDeviceName()));
        //ExtentReports log and screenshot operations for failed tests.
        try {
            ExtentTestManager.getTest().fail("Test Failed, Please see screenshot",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshot()).build());
        } catch (IOException e) {
            logger.error("Failed to attach screenshot to report {}",e.getMessage());
        }

    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest(result).skip(result.getThrowable());
        ExtentTestManager.getTest(result).assignDevice((reportConfig.getDeviceName()));
    }

    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }


    //Here we create the reporter
    private synchronized void setReportName() {
        synchronized (runStarted){
            if(!runStarted){
                ExtentService.getInstance().setSystemInfo("Application Name", reportConfig.getProjectName());
                ExtentService.getInstance().setSystemInfo("Test Environment", reportConfig.getEnvironment());
                ExtentService.getInstance().setSystemInfo("Author", System.getProperty("user.name"));
                try{
                    ExtentService.getInstance().setSystemInfo("Machine IP", InetAddress.getLocalHost().getHostAddress());
                }catch (UnknownHostException exception){
                    logger.error("Failed to get IP address {}", exception.getMessage());
                }
                runStarted = true;
            }
        }
    }

    private boolean canAssignDevice(final ExtentTest test, final String device){
        for(Attribute deviceAttribute: test.getModel().getDeviceContext().getAll()){
            //remove all spaces and compare
            if (deviceAttribute.getName().equalsIgnoreCase(device.replace(" ","")))
                return false;
        }
        return true;
    }

    private boolean canAssignDevice(final ExtentTest test){
        for(Attribute deviceAttribute: test.getModel().getDeviceContext().getAll()){
            //remove all spaces and compare
            if (deviceAttribute.getName().equalsIgnoreCase(reportConfig.getDeviceName().replace(" ","")))
                return false;
        }
        return true;
    }

    private boolean canAssignTag(final ExtentTest test,final String tag){
        for(Attribute tagAttribute: test.getModel().getCategoryContext().getAll()){
            if (tagAttribute.getName().equalsIgnoreCase(tag))
                return false;
        }
        return true;
    }

    private String takeScreenshot() {
        logger.info("Taking screenshot...");
        try {
            return  ((TakesScreenshot) ApplicationContext.getTestBean().getAppiumDriver()).getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            logger.error("Failed to take screenshot , {}", e.getMessage());
        }
        return null;
    }

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    private void attachScreenShot(){
        ExtentTestManager.getTest().addScreenCaptureFromBase64String(
                takeScreenshot());
    }

}
