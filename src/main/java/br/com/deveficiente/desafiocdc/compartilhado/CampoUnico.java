package br.com.deveficiente.desafiocdc.compartilhado;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {CampoUnicoValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface CampoUnico {

    String message() default "Campo com duplicação";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String campo();

    Class<?> classeDominio();
}
