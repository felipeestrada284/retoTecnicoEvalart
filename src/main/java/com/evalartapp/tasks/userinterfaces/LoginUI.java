package com.evalartapp.tasks.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginUI {

    private LoginUI() {
    }

    public static final Target INPUT_USERNAME = Target.the("Text box username").located(By.cssSelector("input[name='username']"));
    public static final Target INPUT_PASSWORD = Target.the("Text box password").located(By.cssSelector("input[name='password']"));
    public static final Target BUTTON_SEND = Target.the("Button send").locatedBy("//button[@type='submit']");
    public static final Target TXT_TITLE = Target.the("tittle").locatedBy("//h1[@class='text-center text-3xl p-3 m-3']");
}
