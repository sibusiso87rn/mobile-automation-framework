package starter.testing.tests.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import starter.testing.core.util.actions.UserActions;
import starter.testing.tests.pages.Page_ComputeSum;

@Lazy
@Scope("prototype")
@Component
public class ActionLandingPage {

    @Autowired
    Page_ComputeSum landingPage;

    public void setFirstNumber(String number){
        UserActions.input(landingPage.getBtnIntegerAButton(),number);
    }

    public void setSecondNumber(String number){
        UserActions.input(landingPage.getBtnIntegerBButton(),number);
    }


    public void computeSum(){
        UserActions.click(landingPage.getBtnComputeSum());
    }


    public void validateSum(String expectedSum){
        UserActions.validateText(landingPage.getLblSumLabel(),expectedSum);
    }

}
