package starter.testing.tests.pages;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import starter.testing.core.AbstractBasePage;

@Component
@Lazy
@Scope("prototype")
public class Page_ComputeSum extends AbstractBasePage {

    @iOSXCUITFindBy(accessibility = "IntegerA")
    WebElement btnIntegerAButton;

    @iOSXCUITFindBy(accessibility = "IntegerB")
    WebElement btnIntegerBButton;

    @iOSXCUITFindBy(accessibility = "Compute Sum")
    WebElement btnComputeSum;

    @iOSXCUITFindBy(accessibility = "SumLabel")
    WebElement lblSumLabel;

    public WebElement getBtnIntegerAButton() {
        return btnIntegerAButton;
    }

    public WebElement getBtnIntegerBButton() {
        return btnIntegerBButton;
    }

    public WebElement getBtnComputeSum() {
        return btnComputeSum;
    }

    public WebElement getLblSumLabel() {
        return lblSumLabel;
    }
}
