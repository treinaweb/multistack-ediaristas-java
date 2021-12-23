package br.com.treinaweb.ediaristas.api.dtos.requests;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

import br.com.treinaweb.ediaristas.core.validators.Idade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {

    @NotNull
    @Size(min = 3, max = 255)
    private String nomeCompleto;

    @NotNull
    @Size(max = 255)
    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String passwordConfirmation;

    @NotNull
    private Integer tipoUsuario;

    @NotNull
    @Size(min = 11, max = 11)
    @CPF
    private String cpf;

    @NotNull
    @Past
    @Idade(min = 18, max = 100)
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate nascimento;

    @NotNull
    @Size(min = 11, max = 11)
    private String telefone;

    @Size(min = 11, max = 255)
    private String chavePix;

    @NotNull
    private MultipartFile fotoDocumento;

    public void setNome_completo(String nomeCompleto) {
        setNomeCompleto(nomeCompleto);
    }

    public void setPassword_confirmation(String passwordConfirmation) {
        setPasswordConfirmation(passwordConfirmation);
    }

    public void setTipo_usuario(Integer tipoUsuario) {
        setTipoUsuario(tipoUsuario);
    }

    public void setChave_pix(String chavePix) {
        setChavePix(chavePix);
    }

    public void setFoto_documento(MultipartFile fotoDocumento) {
        setFotoDocumento(fotoDocumento);
    }

}
