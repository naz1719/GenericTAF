package adaptation.ui.injector;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Injector {
    boolean required() default true;
}
