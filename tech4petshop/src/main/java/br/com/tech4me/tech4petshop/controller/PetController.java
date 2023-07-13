package br.com.tech4me.tech4petshop.controller;

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

import br.com.tech4me.tech4petshop.service.PetService;
import br.com.tech4me.tech4petshop.shared.PetDto;
import br.com.tech4me.tech4petshop.shared.PetListagemDto;

@RestController
@RequestMapping("/petshop")

public class PetController {
    @Autowired
    private PetService servico;
    @GetMapping
    private ResponseEntity<List<PetDto>> obterPets(){
        return new ResponseEntity<>(servico.obterTodos(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    private ResponseEntity<PetListagemDto>obterPorId(@PathVariable String id){
        Optional<PetListagemDto> pets=servico.obterPorId(id);
        if(pets.isPresent()){
            return new ResponseEntity<>(pets.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}")
    private ResponseEntity<PetListagemDto> cadastrar(@RequestBody PetListagemDto pets){
        return new ResponseEntity<>(servico.cadastrar(pets),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> excluirPorId(@PathVariable String id){
        servico.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    private ResponseEntity<PetListagemDto> atualizar(@PathVariable String id, @RequestBody PetListagemDto pets){
        PetListagemDto atualizado=servico.atualizarPorId(id, pets);
        if(atualizado!=null){
            return new ResponseEntity<>(atualizado,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
