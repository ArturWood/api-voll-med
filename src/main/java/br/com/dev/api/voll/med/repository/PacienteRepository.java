package br.com.dev.api.voll.med.repository;

import br.com.dev.api.voll.med.model.paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
