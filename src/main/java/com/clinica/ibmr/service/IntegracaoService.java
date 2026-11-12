package com.clinica.ibmr.service;

import com.clinica.ibmr.api.ApiDtos.IntegracaoResponse;
import com.clinica.ibmr.api.ApiExceptions.NotFoundException;
import com.clinica.ibmr.api.ApiMapper;
import com.clinica.ibmr.repository.AnimalRepository;
import com.clinica.ibmr.repository.IntegracaoGovernoRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IntegracaoService {
    private final IntegracaoGovernoRepository integracoes;
    private final AnimalRepository animais;
    public IntegracaoService(IntegracaoGovernoRepository integracoes, AnimalRepository animais) {
        this.integracoes = integracoes; this.animais = animais;
    }
    @Transactional(readOnly = true)
    public List<IntegracaoResponse> porAnimal(Long animalId) {
        if (!animais.existsById(animalId)) throw new NotFoundException("Animal não encontrado.");
        return integracoes.findAllByAnimal_IdOrderByDataHoraSolicitacaoDesc(animalId).stream()
                .map(ApiMapper::integracao).toList();
    }
    @Transactional(readOnly = true)
    public IntegracaoResponse buscar(Long id) {
        return ApiMapper.integracao(integracoes.findById(id)
                .orElseThrow(() -> new NotFoundException("Operação de integração não encontrada.")));
    }
}
