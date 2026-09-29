package senai._9.com.br.Bom.Cafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import senai._9.com.br.Bom.Cafe.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> { }
