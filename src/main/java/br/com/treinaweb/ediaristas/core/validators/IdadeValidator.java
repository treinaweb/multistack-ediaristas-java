package br.com.treinaweb.ediaristas.core.validators;

import java.time.LocalDate;
import java.time.Period;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IdadeValidator implements ConstraintValidator<Idade, LocalDate> {

    private int min;
    private int max;

    @Override
    public void initialize(Idade constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
        validarParametros();
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        var dataAtual = LocalDate.now();
        var idade = Period.between(value, dataAtual).getYears();
        return idade >= min && idade <= max;
    }

    private void validarParametros() {
        if (min < 0) {
            throw new IllegalArgumentException("O parâmetro min não pode ser negativo");
        }
        if (max < 0) {
            throw new IllegalArgumentException("O parâmetro max não pode ser negativo");
        }
        if (max < min) {
            throw new IllegalArgumentException("O pârametro max não pode ser menor que o parâmetro min");
        }
    }

}
