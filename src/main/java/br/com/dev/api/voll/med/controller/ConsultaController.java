package br.com.dev.api.voll.med.controller;

import br.com.dev.api.voll.med.dto.consulta.ConsultaCancelDto;
import br.com.dev.api.voll.med.dto.consulta.ConsultaDetailDto;
import br.com.dev.api.voll.med.dto.consulta.ConsultaRequestDto;
import br.com.dev.api.voll.med.model.consulta.Consulta;
import br.com.dev.api.voll.med.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<ConsultaDetailDto> criar(@RequestBody @Valid ConsultaRequestDto dto, UriComponentsBuilder uriBuilder) {
        Consulta consulta = consultaService.agendar(dto);
        var uri = uriBuilder.path("/consultas/{id}").buildAndExpand(consulta.getId()).toUri();
        return ResponseEntity.created(uri).body(new ConsultaDetailDto(consulta));
    }

    @DeleteMapping
    public ResponseEntity cancelar(@RequestBody @Valid ConsultaCancelDto dto) {
        consultaService.cancelar(dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDetailDto> detalhar(@PathVariable Long id) {
        Consulta consulta = consultaService.detalhar(id);
        return ResponseEntity.ok(new ConsultaDetailDto(consulta));
    }
}
