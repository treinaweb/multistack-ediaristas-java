package br.com.treinaweb.ediaristas.core.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ServicoExistsByIdValidator.class)
public @interface ServicoExistsById {

    String message() default "serviço com id ${validatedValue} não existe";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
