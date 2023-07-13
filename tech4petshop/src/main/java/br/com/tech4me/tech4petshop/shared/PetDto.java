package br.com.tech4me.tech4petshop.shared;

import java.util.List;

public record PetDto(String id, String nome, List<String> procedimentos) {
    
}
