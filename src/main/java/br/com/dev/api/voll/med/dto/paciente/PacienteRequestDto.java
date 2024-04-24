package br.com.dev.api.voll.med.dto.paciente;

import br.com.dev.api.voll.med.dto.endereco.EnderecoRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PacienteRequestDto(
        @NotBlank
        String nome,
        @NotBlank
        @Email(message = "Deve ser um endereço de e-mail valido")
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{11}", message = "O Telefone deve conter 11 digitos")
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 digitos")
        String cpf,
        @NotNull
        @Valid
        EnderecoRequestDto endereco
) {
}
