package com.evalartapp.tasks.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ValidateText implements Question<Boolean> {

    private final Target element;
    private final String text;

    public ValidateText(Target element, String text) {
        this.element = element;
        this.text = text;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        WaitUntil.the(element, isVisible()).
                forNoMoreThan(10).seconds();
        String textElement = Text.of(element).answeredBy(actor);
        return textElement.equals(text);
    }
    public static ValidateText ofElement(Target element, String text) {
        return new ValidateText(element, text);
    }
}
