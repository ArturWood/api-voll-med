package br.com.dev.api.voll.med.repository;

import br.com.dev.api.voll.med.model.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
