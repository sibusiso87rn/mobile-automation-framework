package starter.testing.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import starter.testing.core.AbstractAction;
import starter.testing.core.util.actions.GenericUserActions;
import starter.testing.pages.PageComputeSum;

@Scope("prototype")
@Component
public class ActionLandingPage extends AbstractAction {


    //TODO
    /**Create Factory pattern for user actions?**/
    PageComputeSum landingPage = new PageComputeSum();

    //final ActionLandingPage actionLandingPage = new ActionLandingPage();

    public ActionLandingPage setFirstNumber(String number){
        GenericUserActions.input(landingPage.getBtnFirstField(),number);
        return this;
    }

    public ActionLandingPage setSecondNumber(String number){
        GenericUserActions.input(landingPage.getBtnSecondField(),number);
        return this;
    }

    public ActionLandingPage computeSum(){
        GenericUserActions.click(landingPage.getBtnComputeSum());
        return this;
    }

    public ActionLandingPage validateSum(String expectedSum){
        GenericUserActions.validateText(landingPage.getLblSumLabel(),expectedSum);
        return this;
    }

    public ActionLandingPage validatePageTitle(String expectedPageTitle){
        GenericUserActions.validateText(landingPage.getText_title(),expectedPageTitle);
        return this;
    }
}
