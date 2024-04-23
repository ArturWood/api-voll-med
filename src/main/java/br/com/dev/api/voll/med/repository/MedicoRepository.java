package br.com.dev.api.voll.med.repository;

import br.com.dev.api.voll.med.model.medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
