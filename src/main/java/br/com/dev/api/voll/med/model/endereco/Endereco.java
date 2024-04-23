package br.com.dev.api.voll.med.model.endereco;

import br.com.dev.api.voll.med.dto.endereco.EnderecoRequestDto;
import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String numero;
    private String complemento;

    public Endereco() {
    }

    public Endereco(EnderecoRequestDto dto) {
        this.logradouro = dto.logradouro();
        this.bairro = dto.bairro();
        this.cep = dto.cep();
        this.cidade = dto.cidade();
        this.uf = dto.uf();
        this.numero = dto.numero();
        this.complemento = dto.complemento();
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCep() {
        return cep;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void atualizaDados(EnderecoRequestDto endereco) {
        this.logradouro = endereco.logradouro() != null ? endereco.logradouro() : this.logradouro;
        this.bairro = endereco.bairro() != null ? endereco.bairro() : this.bairro;
        this.cep = endereco.cep() != null ? endereco.cep() : this.cep;
        this.uf = endereco.uf() != null ? endereco.uf() : this.uf;
        this.cidade = endereco.cidade() != null ? endereco.cidade() : this.cidade;
        this.numero = endereco.numero() != null ? endereco.numero() : this.numero;
        this.complemento = endereco.complemento() != null ? endereco.complemento() : this.complemento;
    }
}
