package br.com.tech4me.tech4petshop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.tech4me.tech4petshop.model.PetModel;

public interface PetRepository extends MongoRepository<PetModel,String> {
    
}
