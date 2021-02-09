package starter.testing.pages;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import starter.testing.core.AbstractBasePage;

@Component
@Lazy
@Scope("prototype")
public class PageComputeSum extends AbstractBasePage {

    @AndroidBy(id = "first_field")
    AndroidElement first_field;

    @AndroidBy(id = "second_field")
    AndroidElement second_field;

    @AndroidBy(id = "button_calculate")
    AndroidElement button_calculate;

    @AndroidBy(accessibility = "text_result")
    AndroidElement text_result;

    @AndroidBy(accessibility = "text_title")
    AndroidElement text_title;

    public WebElement getBtnFirstField() {
        return first_field;
    }

    public WebElement getBtnSecondField() {
        return second_field;
    }

    public WebElement getBtnComputeSum() {
        return button_calculate;
    }

    public WebElement getLblSumLabel() {
        return text_result;
    }

    public AndroidElement getText_title() {
        return text_title;
    }
}
