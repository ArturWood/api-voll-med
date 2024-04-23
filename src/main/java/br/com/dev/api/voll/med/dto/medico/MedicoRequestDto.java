package br.com.dev.api.voll.med.dto.medico;

import br.com.dev.api.voll.med.dto.endereco.EnderecoRequestDto;
import br.com.dev.api.voll.med.model.medico.Especialidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicoRequestDto(
        @NotBlank
        String nome,
        @NotBlank
        @Email(message = "Deve ser um endereço de e-mail valido")
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{11}", message = "O Telefone deve conter 11 digitos")
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}", message = "O CRM deve conter de 4 a 6 digitos")
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull
        @Valid
        EnderecoRequestDto endereco
) {
}
