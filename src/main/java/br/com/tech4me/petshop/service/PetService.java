package br.com.tech4me.petshop.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.petshop.shared.PetDTO;
import br.com.tech4me.petshop.shared.PetListagemDTO;

public interface PetService {
    
    List<PetListagemDTO> listarPets();
    Optional<PetListagemDTO> buscarPorId(String id);
    PetDTO registrar (PetDTO dto);
    Optional<PetDTO> atualizarPorId (String id, PetDTO dto);
    void excluirPorId (String id);
    
}
