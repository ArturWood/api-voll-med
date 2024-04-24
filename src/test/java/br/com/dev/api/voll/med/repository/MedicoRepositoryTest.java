package br.com.dev.api.voll.med.repository;

import br.com.dev.api.voll.med.dto.endereco.EnderecoRequestDto;
import br.com.dev.api.voll.med.dto.medico.MedicoRequestDto;
import br.com.dev.api.voll.med.dto.paciente.PacienteRequestDto;
import br.com.dev.api.voll.med.model.consulta.Consulta;
import br.com.dev.api.voll.med.model.medico.Especialidade;
import br.com.dev.api.voll.med.model.medico.Medico;
import br.com.dev.api.voll.med.model.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MedicoRepositoryTest {
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null quando unico medico cadastrado nao esta disponivel na data")
    void scenario01() {
        // Arrange
        LocalDateTime proximaSegundaAs10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        Medico medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
        Paciente paciente = cadastrarPaciente("Paciente", "paciente@email.com", "00000000000");
        cadastrarConsulta(medico, paciente, proximaSegundaAs10);

        // Act
        var medicoLivre = medicoRepository.escolheMedicoPelaEspecialidadeLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);

        // Assert
        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deveria devolver um medico cadastrado disponivel na data")
    void scenario02() {
        // Arrange
        LocalDateTime proximaSegundaAs10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        Medico medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);

        // Act
        Medico medicoLivre = medicoRepository.escolheMedicoPelaEspecialidadeLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);

        // Assert
        assertThat(medicoLivre).isEqualTo(medico);
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        em.persist(new Consulta(medico, paciente, data));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private MedicoRequestDto dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new MedicoRequestDto(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private PacienteRequestDto dadosPaciente(String nome, String email, String cpf) {
        return new PacienteRequestDto(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private EnderecoRequestDto dadosEndereco() {
        return new EnderecoRequestDto(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}
