package com.evalartapp.tasks.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.pages.PageObject;

public class CompleteFormUI extends PageObject {

    private CompleteFormUI(){}

    public static final Target LBL_OPERATION = Target.the("Lbl operation").locatedBy("//input[@value='{0}']");
    public static final Target INP_FIGURE_NUMBER = Target.the("Inp figure number").locatedBy("//input[@name='number']");
    public static final Target SELECT_RESPONSE = Target.the("Select response").locatedBy("//select[@name='select']");
    public static final Target CHK_OPERATION = Target.the("chk operation").locatedBy("//input[@value='{0}']");
    public static final Target BTN_SEND = Target.the("button send").locatedBy("//button[@type='submit']");
    public static final Target TXT_FINISHED = Target.the("Text finished").locatedBy("//h1[contains(text(), 'Felicidades, has terminado la prueba exitosamente ')]");

}