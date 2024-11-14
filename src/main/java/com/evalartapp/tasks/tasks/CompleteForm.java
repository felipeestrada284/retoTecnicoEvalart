package com.evalartapp.tasks.tasks;

import com.evalartapp.tasks.utils.Excel;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.evalartapp.tasks.userinterfaces.CompleteFormUI.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class CompleteForm implements Task {


    Excel excel = new Excel("DataForm");

    public <T extends Actor> void performAs(T actor) {

        for (int i = 0; i < 10; i++) {
            String valueOperation = excel.leerDatos(1, i);
            actor.attemptsTo(
                    Click.on(LBL_OPERATION.of(valueOperation)),
                    Enter.theValue(excel.leerDatos(2, i)).into(INP_FIGURE_NUMBER),
                    Scroll.to(SELECT_RESPONSE),
                    SelectFromOptions.byValue(excel.leerDatos(3, i)).from(SELECT_RESPONSE)
            );
            int contador = 4;
            while (contador < 8 && !excel.leerDatos(contador, i).isEmpty()) {
                actor.attemptsTo(
                        Click.on(CHK_OPERATION.of(excel.leerDatos(contador, i)))
                );
                contador++;
            }
            actor.attemptsTo(
                    Click.on(BTN_SEND)
            );
        }

        actor.attemptsTo(
                WaitUntil.the(TXT_FINISHED, isVisible()).forNoMoreThan(30).seconds()
        );
    }


    public static CompleteForm inTheSite() {
        return instrumented(CompleteForm.class);
    }
}
