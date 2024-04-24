package br.com.dev.api.voll.med.model.paciente;

import br.com.dev.api.voll.med.dto.paciente.PacienteRequestDto;
import br.com.dev.api.voll.med.dto.paciente.PacienteUpdateDto;
import br.com.dev.api.voll.med.model.endereco.Endereco;
import jakarta.persistence.*;

@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Paciente() {
    }

    public Paciente(PacienteRequestDto dto) {
        this.ativo = true;
        this.nome = dto.nome();
        this.email = dto.email();
        this.telefone = dto.telefone();
        this.cpf = dto.cpf();
        this.endereco = new Endereco(dto.endereco());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void atualizaDados(PacienteUpdateDto dto) {
        this.nome = dto.nome() != null ? dto.nome() : this.nome;
        this.telefone = dto.telefone() != null ? dto.telefone() : this.telefone;
        if (dto.endereco() != null) {
            this.endereco.atualizaDados(dto.endereco());
        }
    }

    public void inativar() {
        this.ativo = false;
    }
}
