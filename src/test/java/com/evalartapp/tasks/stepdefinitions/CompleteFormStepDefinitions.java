package com.evalartapp.tasks.stepdefinitions;

import com.evalartapp.tasks.questions.ValidateText;
import com.evalartapp.tasks.tasks.CompleteForm;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import com.evalartapp.tasks.tasks.Login;
import com.evalartapp.tasks.tasks.NavigateToWebsite;

import static com.evalartapp.tasks.userinterfaces.CompleteFormUI.TXT_FINISHED;
import static com.evalartapp.tasks.userinterfaces.LoginUI.TXT_TITLE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.setTheStage;

public class CompleteFormStepDefinitions {

    public static Actor actor = Actor.named("Usuario");

    @Managed(driver = "chrome")
    WebDriver driver;

    @Before
    public void setupInitial() {
        setTheStage(new OnlineCast());
        actor.can(BrowseTheWeb.with(driver));
    }

    @Given("^user navigate to web site login page$")
    public void userNavigateToWebSiteLoginPage() {
        actor.wasAbleTo(
                NavigateToWebsite.withLink()
        );
    }

    @When("user login in the app whit the user {string} and password {string}")
    public void userLoginInTheAppWhitTheUserAndPassword(String username, String password) {
        actor.attemptsTo(
                Login.successful(username, password)
        );
    }

    @And("the user can see the login text")
    public void theUserCanSeeTheLoginText() {
        actor.should(
                seeThat(
                        ValidateText.ofElement(TXT_TITLE, "Prueba de automatización")
                )
        );
    }

    @And("the user complete the form")
    public void theUserCompleteTheForm() {
        actor.attemptsTo(
                CompleteForm.inTheSite()
        );
    }

    @Then("validates that the test was successfully completed")
    public void validatesThatTheTestWasSuccessfullyCompleted() {
        actor.should(
                seeThat(
                        ValidateText.ofElement(TXT_FINISHED, "Felicidades, has terminado la prueba exitosamente")
                )
        );
    }
}
