package br.com.dev.api.voll.med.service;

import br.com.dev.api.voll.med.dto.consulta.ConsultaCancelDto;
import br.com.dev.api.voll.med.dto.consulta.ConsultaRequestDto;
import br.com.dev.api.voll.med.model.consulta.Consulta;
import br.com.dev.api.voll.med.model.consulta.MotivoCancelamento;
import br.com.dev.api.voll.med.model.consulta.validacoes.agendamento.ValidadorDeAgendamento;
import br.com.dev.api.voll.med.model.consulta.validacoes.cancelamento.ValidadorDeCancelamento;
import br.com.dev.api.voll.med.model.medico.Especialidade;
import br.com.dev.api.voll.med.model.medico.Medico;
import br.com.dev.api.voll.med.model.paciente.Paciente;
import br.com.dev.api.voll.med.repository.ConsultaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ConsultaServiceTest {

    @InjectMocks
    private ConsultaService consultaService;

    @Mock
    private ConsultaRepository consultaRepository;

    @Mock
    private MedicoService medicoService;

    @Mock
    private PacienteService pacienteService;

    @Mock
    private List<ValidadorDeAgendamento> validadorDeAgendamento;

    @Mock
    private List<ValidadorDeCancelamento> validadorDeCancelamento;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deveria agendar consulta com informações validas")
    void scenario01() {
        // Arrange
        ConsultaRequestDto dto = new ConsultaRequestDto(1L, 2L, LocalDateTime.now(), Especialidade.CARDIOLOGIA);
        Paciente paciente = new Paciente();
        Medico medico = new Medico();
        Consulta consulta = new Consulta(medico, paciente, LocalDateTime.now());

        when(pacienteService.detalhar(any())).thenReturn(paciente);
        when(medicoService.detalhar(any())).thenReturn(medico);
        when(consultaRepository.save(any())).thenReturn(consulta);

        // Act
        Consulta result = consultaService.agendar(dto);

        // Assert
        assertNotNull(result);
        assertEquals(consulta, result);
        verify(validadorDeAgendamento, times(1)).forEach(any());
        verify(pacienteService, times(1)).detalhar(any());
        verify(medicoService, times(1)).detalhar(any());
        verify(consultaRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Deveria cancelar consulta e gravar MotivoCancelamento no BD")
    void scenario02() {
        // Arrange
        ConsultaCancelDto dto = new ConsultaCancelDto(1L, MotivoCancelamento.PACIENTE_DESISTIU);
        Consulta consulta = new Consulta();

        when(consultaRepository.findById(any())).thenReturn(Optional.of(consulta));

        // Act
        consultaService.cancelar(dto);

        // Assert
        verify(validadorDeCancelamento, times(1)).forEach(any());
        verify(consultaRepository, times(1)).findById(any());
        assertNotNull(consulta.getMotivoCancelamento());
    }

    @Test
    @DisplayName("Deveria retornar os detalhes de uma consulta")
    void scenario03() {
        // Arrange
        Long id = 1L;
        Consulta consulta = new Consulta();

        when(consultaRepository.findById(any())).thenReturn(Optional.of(consulta));

        // Act
        Consulta result = consultaService.detalhar(id);

        // Assert
        assertNotNull(result);
        assertEquals(consulta, result);
        verify(consultaRepository, times(1)).findById(any());
    }
}
