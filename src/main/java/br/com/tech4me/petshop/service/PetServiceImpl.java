package br.com.tech4me.petshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.petshop.model.Pet;
import br.com.tech4me.petshop.repository.PetRepository;
import br.com.tech4me.petshop.shared.PetDTO;
import br.com.tech4me.petshop.shared.PetListagemDTO;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository repositorio;

    @Override
    public List<PetListagemDTO> listarPets() {
        return repositorio.findAll().stream().map(p -> new PetListagemDTO(p.getId(), p.getNome(), p.getProcedimentos())).toList();
    }

    @Override
    public Optional<PetListagemDTO> buscarPorId(String id) {
        Optional<Pet> pet = repositorio.findById(id);

        if (pet.isPresent()) {
            return Optional.of(new PetListagemDTO(pet.get().getId(), pet.get().getNome(), pet.get().getProcedimentos()));
        }
        return Optional.empty();
    }

    @Override
    public PetDTO registrar(PetDTO dto) {
        Pet p = new Pet(dto);
        repositorio.save(p);
        return new PetDTO(p.getId(), p.getNome(), p.getRaca(), p.getAnoNascimento(), p.isVacinado(), p.getProcedimentos());
    }

    @Override
    public Optional<PetDTO> atualizarPorId(String id, PetDTO dto) {
        Optional<Pet> pet = repositorio.findById(id);

        if (pet.isPresent()) {
            Pet atualizarPet = new Pet(dto);
            atualizarPet.setId(id);
            repositorio.save(atualizarPet);

            return Optional.of(new PetDTO(atualizarPet.getId(), atualizarPet.getNome(),
             atualizarPet.getRaca(), atualizarPet.getAnoNascimento(), atualizarPet.isVacinado(), atualizarPet.getProcedimentos()));
        }
        return Optional.empty();

    }

    @Override
    public void excluirPorId(String id) {
        repositorio.deleteById(id);
    }


    
}
