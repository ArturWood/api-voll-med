package br.com.dev.api.voll.med.service;

import br.com.dev.api.voll.med.dto.endereco.EnderecoRequestDto;
import br.com.dev.api.voll.med.dto.medico.MedicoPageDto;
import br.com.dev.api.voll.med.dto.medico.MedicoRequestDto;
import br.com.dev.api.voll.med.dto.medico.MedicoUpdateDto;
import br.com.dev.api.voll.med.model.medico.Especialidade;
import br.com.dev.api.voll.med.model.medico.Medico;
import br.com.dev.api.voll.med.repository.MedicoRepository;
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
import static org.mockito.Mockito.*;

public class MedicoServiceTest {

    @InjectMocks
    private MedicoService medicoService;

    @Mock
    private MedicoRepository medicoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Deveria cadastrar medico com informações validas")
    public void scenario01() {
        // Arrange
        MedicoRequestDto dto = new MedicoRequestDto("Medico", "medico@voll.med", "11123456789", "654654", Especialidade.CARDIOLOGIA,
                new EnderecoRequestDto("rua xpto", "bairro", "00000000", "Brasilia", "DF", "312", null)
        );
        Medico medico = new Medico(dto);
        when(medicoRepository.save(any())).thenReturn(medico);

        // Act
        Medico result = medicoService.cadastrar(dto);

        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("Deveria retornar Page de medicos")
    public void scenario02() {
        // Arrange
        Pageable paginacao = mock(Pageable.class);
        Medico medico = new Medico();
        Page<Medico> page = new PageImpl<>(Collections.singletonList(medico));
        when(medicoRepository.findAllByAtivoTrue(paginacao)).thenReturn(page);

        // Act
        Page<MedicoPageDto> result = medicoService.listar(paginacao);

        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("Deveria atualizar as informações de um medico")
    public void scenario03() {
        // Arrange
        MedicoUpdateDto dto = new MedicoUpdateDto(1L, "", "", new EnderecoRequestDto("rua xpto", "bairro", "00000000", "Brasilia", "DF", "312", null));
        Medico medico = mock(Medico.class);
        when(medicoRepository.findById(any())).thenReturn(java.util.Optional.of(medico));

        // Act
        Medico result = medicoService.atualizar(dto);

        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("Deveria inativar um medico")
    public void scenario04() {
        // Arrange
        Long id = 1L;
        Medico medico = mock(Medico.class);
        when(medicoRepository.findById(any())).thenReturn(java.util.Optional.of(medico));

        // Act
        medicoService.excluir(id);

        // Assert
        verify(medico).inativar();
    }

    @Test
    @DisplayName("Deveria retornar os detalhes de um medico")
    public void scenario05() {
        // Arrange
        Long id = 1L;
        Medico medico = mock(Medico.class);
        when(medicoRepository.findById(any())).thenReturn(java.util.Optional.of(medico));

        // Act
        Medico result = medicoService.detalhar(id);

        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("Deveria buscar um medico especifico pelo ID")
    public void scenario06() {
        // Arrange
        Long id = 1L;
        Medico medico = new Medico();
        when(medicoRepository.findById(any())).thenReturn(java.util.Optional.of(medico));

        // Act
        Medico result = medicoService.getMedico(id);

        // Assert
        assertNotNull(result);
    }
}
