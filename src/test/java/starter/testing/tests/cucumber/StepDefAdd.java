package starter.testing.tests.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import starter.testing.actions.ActionLandingPage;


public class StepDefAdd {

    //TODO
    /*Based on the os get the relevant one*/
    final ActionLandingPage actionLandingPage = new ActionLandingPage();

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

    @Then("The title reads {string}")
    public void theTitleReads(String pageTitle) {
        actionLandingPage.validatePageTitle(pageTitle);
    }
}
