package com.clinica.ibmr.repository;

import com.clinica.ibmr.domain.IntegracaoGoverno;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntegracaoGovernoRepository extends JpaRepository<IntegracaoGoverno, Long> {
    List<IntegracaoGoverno> findAllByAnimal_IdOrderByDataHoraSolicitacaoDesc(Long animalId);
    boolean existsByVacina_Id(Long vacinaId);
}
