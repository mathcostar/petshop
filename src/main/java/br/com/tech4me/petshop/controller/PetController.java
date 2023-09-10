package br.com.tech4me.petshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.petshop.service.PetService;
import br.com.tech4me.petshop.shared.PetDTO;
import br.com.tech4me.petshop.shared.PetListagemDTO;

@RestController
@RequestMapping("/petshop")
public class PetController {

    @Autowired
    private PetService servico;

    @GetMapping("/listagem")
    private ResponseEntity<List<PetListagemDTO>> listarPets() {
        return new ResponseEntity<>(servico.listarPets(), HttpStatus.OK);
    }

    @GetMapping("/listagem/{id}")
    private ResponseEntity<PetListagemDTO> buscarPorId(@PathVariable String id) {
        if (servico.buscarPorId(id).isPresent()) {
            return new ResponseEntity<>(servico.buscarPorId(id).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PostMapping
    private ResponseEntity<PetDTO> registrar(@RequestBody PetDTO pet) {
        return new ResponseEntity<>(servico.registrar(pet), HttpStatus.CREATED);
    }

    @PutMapping("/listagem/{id}")
    private ResponseEntity<PetDTO> atualizarPet(@PathVariable String id, @RequestBody PetDTO pet) {
        Optional<PetDTO> atualizarPet = servico.atualizarPorId(id, pet);

        if (atualizarPet.isPresent()) {
            return new ResponseEntity<>(atualizarPet.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("listagem/{id}")
    private ResponseEntity<Void> excluirPorId(@PathVariable String id) {
        servico.excluirPorId(id);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
