package adaptation.parsers.excel.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface ExcelCell {

//    column index
    int value();
}
