package br.com.treinaweb.ediaristas.core.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HoraDepoisDeValidator.class)
public @interface HoraDepoisDe {

    String message() default "a hora não pode ser menor que {horaInicio}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int horaInicio() default 0;

    @Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {

        HoraDepoisDe[] value();

    }

}
