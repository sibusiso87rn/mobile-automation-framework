package starter.testing.tests.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import starter.testing.core.bean.ApplicationContext;
import starter.testing.tests.actions.ActionLandingPage;

@Component
@Lazy
@Scope("prototype")
public class StepDef_LandingPage {

    final ActionLandingPage actionLandingPage = (ActionLandingPage) ApplicationContext.getComponent(ActionLandingPage.class);

    @And("The user enters the first {string}")
    public void theUserEntersTheFirst(String number) {
        actionLandingPage.setFirstNumber(number);
    }

    @And("The user enters the second {string}")
    public void theUserEntersTheSecond(String number) {
        actionLandingPage.setSecondNumber(number);
    }

    @Then("The sum is {string}")
    public void theSumIs(String sum) {
        actionLandingPage.validateSum(sum);
    }

    @Given("The user is on the landing screen")
    public void theUserIsOnTheLandingScreen() {
    }

    @And("The user clicks the compute button")
    public void theUserClicksTheComputeButton() {
        actionLandingPage.computeSum();
    }
}
