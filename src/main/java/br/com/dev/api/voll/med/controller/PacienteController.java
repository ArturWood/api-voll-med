package br.com.dev.api.voll.med.controller;

import br.com.dev.api.voll.med.dto.paciente.PacienteDetailDto;
import br.com.dev.api.voll.med.dto.paciente.PacientePageDto;
import br.com.dev.api.voll.med.dto.paciente.PacienteRequestDto;
import br.com.dev.api.voll.med.dto.paciente.PacienteUpdateDto;
import br.com.dev.api.voll.med.model.paciente.Paciente;
import br.com.dev.api.voll.med.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteDetailDto> cadastrar(@RequestBody @Valid PacienteRequestDto dto, UriComponentsBuilder uriBuilder) {
        Paciente paciente = pacienteService.cadastrar(dto);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new PacienteDetailDto(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<PacientePageDto>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        Page<PacientePageDto> page = pacienteService.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    public ResponseEntity<PacienteDetailDto> atualizar(@RequestBody @Valid PacienteUpdateDto dto) {
        Paciente paciente = pacienteService.atualizar(dto);
        return ResponseEntity.ok(new PacienteDetailDto(paciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id) {
        pacienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDetailDto> detalhar(@PathVariable Long id) {
        Paciente paciente = pacienteService.detalhar(id);
        return ResponseEntity.ok(new PacienteDetailDto(paciente));
    }
}
