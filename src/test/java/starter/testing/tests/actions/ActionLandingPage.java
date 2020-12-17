package starter.testing.tests.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import starter.testing.core.util.actions.GenericUserActions;
import starter.testing.tests.pages.Page_ComputeSum;

@Lazy
@Scope("prototype")
@Component
public class ActionLandingPage {

    @Autowired
    Page_ComputeSum landingPage;

    public void setFirstNumber(String number){
        GenericUserActions.input(landingPage.getBtnFirstField(),number);
    }

    public void setSecondNumber(String number){
        GenericUserActions.input(landingPage.getBtnSecondField(),number);
    }

    public void computeSum(){
        GenericUserActions.click(landingPage.getBtnComputeSum());
    }

    public void validateSum(String expectedSum){
        GenericUserActions.validateText(landingPage.getLblSumLabel(),expectedSum);
    }

    public void validatePageTitle(String expectedPageTitle){
        GenericUserActions.validateText(landingPage.getText_title(),expectedPageTitle);
    }
}
