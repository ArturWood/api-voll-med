package br.com.dev.api.voll.med.controller;

import br.com.dev.api.voll.med.dto.medico.MedicoDetailDto;
import br.com.dev.api.voll.med.dto.medico.MedicoPageDto;
import br.com.dev.api.voll.med.dto.medico.MedicoRequestDto;
import br.com.dev.api.voll.med.dto.medico.MedicoUpdateDto;
import br.com.dev.api.voll.med.model.medico.Medico;
import br.com.dev.api.voll.med.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;

    @PostMapping
    public ResponseEntity<MedicoDetailDto> cadastrar(@RequestBody @Valid MedicoRequestDto dto, UriComponentsBuilder uriBuilder) {
        Medico medico = medicoService.cadastrar(dto);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new MedicoDetailDto(medico));
    }

    @GetMapping
    public ResponseEntity<Page<MedicoPageDto>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        Page<MedicoPageDto> page = medicoService.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    public ResponseEntity<MedicoDetailDto> atualizar(@RequestBody @Valid MedicoUpdateDto dto) {
        Medico medico = medicoService.atualizar(dto);
        return ResponseEntity.ok(new MedicoDetailDto(medico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id) {
        medicoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDetailDto> detalhar(@PathVariable Long id) {
        Medico medico = medicoService.detalhar(id);
        return ResponseEntity.ok(new MedicoDetailDto(medico));
    }
}
