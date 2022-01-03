package br.com.treinaweb.ediaristas.core.validators;

import java.time.LocalDateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HoraDepoisDeValidator implements ConstraintValidator<HoraDepoisDe, LocalDateTime> {

    private int horaInicio;

    @Override
    public void initialize(HoraDepoisDe constraintAnnotation) {
        horaInicio = constraintAnnotation.horaInicio();
        validarParametros();
    }

    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        return value.getHour() >= horaInicio;
    }

    private void validarParametros() {
        if (horaInicio < 0) {
            throw new IllegalArgumentException("horaInicio não pode ser negativo");
        }
    }

}
