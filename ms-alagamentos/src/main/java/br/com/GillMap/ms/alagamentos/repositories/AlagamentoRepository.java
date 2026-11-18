package br.com.GillMap.ms.alagamentos.repositories;

import br.com.GillMap.ms.alagamentos.entities.Alagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlagamentoRepository extends JpaRepository<Alagamento, Long> {
}
