package br.com.treinaweb.ediaristas.core.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import br.com.treinaweb.ediaristas.core.exceptions.UsuarioJaCadastradoException;
import br.com.treinaweb.ediaristas.core.exceptions.ValidacaoException;
import br.com.treinaweb.ediaristas.core.models.Usuario;
import br.com.treinaweb.ediaristas.core.repositories.UsuarioRepository;

@Component
public class UsuarioValidator {

    @Autowired
    private UsuarioRepository repository;

    public void validar(Usuario usuario) {
        validarEmail(usuario);
    }

    private void validarEmail(Usuario usuario) {
        if (repository.isEmailJaCadastrado(usuario)) {
            var mensagem = "Já existe um usuário cadastrado com esse e-mail";
            var fieldError = new FieldError(usuario.getClass().getName(), "email", usuario.getEmail(), false, null, null, mensagem);

            throw new UsuarioJaCadastradoException(mensagem, fieldError);
        }

        validarCpf(usuario);
    }

    private void validarCpf(Usuario usuario) {
        if (repository.isCpfJaCadastrado(usuario)) {
            var mensagem = "Já existe um usuário cadastrado com esse cpf";
            var fieldError = new FieldError(usuario.getClass().getName(), "cpf", usuario.getCpf(), false, null, null, mensagem);

            throw new UsuarioJaCadastradoException(mensagem, fieldError);
        }

        validarChavePix(usuario);
    }

    private void validarChavePix(Usuario usuario) {
        if (repository.isChavePixJaCadastrada(usuario)) {
            var mensagem = "Já existe um usuário cadastrado com essa chave pix";
            var fieldError = new FieldError(usuario.getClass().getName(), "chavePix", usuario.getChavePix(), false, null, null, mensagem);

            throw new UsuarioJaCadastradoException(mensagem, fieldError);
        }

        if (usuario.isDiarista() && usuario.getChavePix() == null) {
            var mensagem = "Usuário do tipo DIARISTA precisa ter a chave pix";
            var fieldError = new FieldError(usuario.getClass().getName(), "chavePix", usuario.getChavePix(), false, null, null, mensagem);

            throw new ValidacaoException(mensagem, fieldError);
        }
    }

}
