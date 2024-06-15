package xyz.skylar11d.minecraftp.serverstatus.utilities.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ACommand {

    String name();
    String requires() default "";
    boolean onlyPlayers() default false;

}
