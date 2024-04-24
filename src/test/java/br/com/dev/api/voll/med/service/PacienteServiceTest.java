package br.com.dev.api.voll.med.service;

import br.com.dev.api.voll.med.dto.endereco.EnderecoRequestDto;
import br.com.dev.api.voll.med.dto.paciente.PacientePageDto;
import br.com.dev.api.voll.med.dto.paciente.PacienteRequestDto;
import br.com.dev.api.voll.med.dto.paciente.PacienteUpdateDto;
import br.com.dev.api.voll.med.model.paciente.Paciente;
import br.com.dev.api.voll.med.repository.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PacienteServiceTest {

    @InjectMocks
    private PacienteService pacienteService;

    @Mock
    private PacienteRepository pacienteRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Deveria cadastrar paciente com informações validas")
    public void scenario01() {
        // Arrange
        PacienteRequestDto dto = new PacienteRequestDto("Paciente", "paciente@voll.med", "11123456789", "654654",
                new EnderecoRequestDto("rua xpto", "bairro", "00000000", "Brasilia", "DF", "312", null)
        );
        Paciente paciente = new Paciente(dto);
        when(pacienteRepository.save(any())).thenReturn(paciente);

        // Act
        Paciente result = pacienteService.cadastrar(dto);

        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("Deveria retornar Page de pacientes")
    public void scenario02() {
        // Arrange
        Pageable paginacao = mock(Pageable.class);
        Paciente paciente = new Paciente();
        Page<Paciente> page = new PageImpl<>(Collections.singletonList(paciente));
        when(pacienteRepository.findAllByAtivoTrue(paginacao)).thenReturn(page);

        // Act
        Page<PacientePageDto> result = pacienteService.listar(paginacao);

        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("Deveria atualizar as informações de um paciente")
    public void scenario03() {
        // Arrange
        PacienteUpdateDto dto = new PacienteUpdateDto(1L, "", "", new EnderecoRequestDto("rua xpto", "bairro", "00000000", "Brasilia", "DF", "312", null));
        Paciente paciente = mock(Paciente.class);
        when(pacienteRepository.findById(any())).thenReturn(java.util.Optional.of(paciente));

        // Act
        Paciente result = pacienteService.atualizar(dto);

        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("Deveria inativar um paciente")
    public void scenario04() {
        // Arrange
        Long id = 1L;
        Paciente paciente = mock(Paciente.class);
        when(pacienteRepository.findById(any())).thenReturn(java.util.Optional.of(paciente));

        // Act
        pacienteService.excluir(id);

        // Assert
        verify(paciente).inativar();
    }

    @Test
    @DisplayName("Deveria retornar os detalhes de um paciente")
    public void scenario05() {
        // Arrange
        Long id = 1L;
        Paciente paciente = mock(Paciente.class);
        when(pacienteRepository.findById(any())).thenReturn(java.util.Optional.of(paciente));

        // Act
        Paciente result = pacienteService.detalhar(id);

        // Assert
        assertNotNull(result);
    }
}
