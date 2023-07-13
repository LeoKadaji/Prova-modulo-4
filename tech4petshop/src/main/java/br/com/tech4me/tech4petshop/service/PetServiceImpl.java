package br.com.tech4me.tech4petshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.tech4petshop.model.PetModel;
import br.com.tech4me.tech4petshop.repository.PetRepository;
import br.com.tech4me.tech4petshop.shared.PetDto;
import br.com.tech4me.tech4petshop.shared.PetListagemDto;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository repositorio;

    @Override
    public List<PetDto> obterTodos(){
        return repositorio.findAll()
        .stream()
        .map(p -> new PetDto(p.getId(), p.getNome(), p.getProcedimentos()))
        .toList();
    }
    @Override
    public Optional<PetListagemDto> obterPorId(String id){
        Optional<PetModel> pets=repositorio.findById(id);
        if(pets.isPresent()){
            return Optional.of(new PetListagemDto(pets.get().getId(), pets.get().getNome(), pets.get().getRaca(), pets.get().getAnoNascimento(), pets.get().getVacinado(), pets.get().getProcedimentos()));
        } else{
            return Optional.empty();
        }
    }
    @Override
    public PetListagemDto cadastrar(PetListagemDto dto){
        PetModel pets = new PetModel(dto);
        repositorio.save(pets);
        return new PetListagemDto(pets.getId(), pets.getNome(), pets.getRaca(), pets.getAnoNascimento(), pets.getVacinado(), pets.getProcedimentos());
    }
    @Override
    public PetListagemDto atualizarPorId(String id, PetListagemDto dto){
        PetModel pets = repositorio.findById(id).orElse(null);
        if(pets!=null){
            PetModel outroPet = new PetModel(dto);
            outroPet.setId(id);
            repositorio.save(outroPet);
            return new PetListagemDto(outroPet.getId(),outroPet.getNome(), outroPet.getRaca(), outroPet.getAnoNascimento(), outroPet.getVacinado(), outroPet.getProcedimentos());
        }
        return dto;
    }
    @Override
    public void excluir(String id){
        repositorio.deleteById(id);
    }


    }
    
    
