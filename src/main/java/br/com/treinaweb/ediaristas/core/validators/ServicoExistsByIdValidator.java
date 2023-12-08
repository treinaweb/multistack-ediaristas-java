package br.com.treinaweb.ediaristas.core.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.treinaweb.ediaristas.core.repositories.ServicoRepository;

@Component
public class ServicoExistsByIdValidator implements ConstraintValidator<ServicoExistsById, Long> {

    @Autowired
    private ServicoRepository servicoRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return servicoRepository.existsById(value);
    }

}
