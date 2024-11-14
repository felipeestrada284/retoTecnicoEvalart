package com.evalartapp.tasks.tasks;

import net.serenitybdd.core.environment.ConfiguredEnvironment;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class NavigateToWebsite implements Task {
    private static final EnvironmentVariables environmentVariables = ConfiguredEnvironment.getEnvironmentVariables();
    public static final  String url = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("environments.qa.base.url");

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url(url));
    }

    public static Performable withLink() {
        return instrumented(NavigateToWebsite.class);
    }

}
