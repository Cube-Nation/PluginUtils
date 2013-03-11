package de.cubenation.plugins.utils.commandapi.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    String[] main();

    String[] sub() default "";

    int min() default 0;

    int max() default -1;

    String help() default "";

    String usage() default "";
}
