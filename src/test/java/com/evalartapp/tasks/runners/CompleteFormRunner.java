package com.evalartapp.tasks.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/completeForm.feature",
        glue = "com.evalartapp.tasks.stepdefinitions",
        tags = "@completeForm",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)

public class CompleteFormRunner {
}
