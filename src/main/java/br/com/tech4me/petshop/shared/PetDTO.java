package br.com.tech4me.petshop.shared;

import java.util.List;

public record PetDTO (String id, String nome, String raca, int anoNascimento, boolean vacinado, List<String> procedimentos) {
    
}
