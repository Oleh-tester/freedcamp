package common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // <- тільки методи
@Retention(RetentionPolicy.RUNTIME) // <- доступно під час виконання
public @interface SkipSessionInjection {
}

