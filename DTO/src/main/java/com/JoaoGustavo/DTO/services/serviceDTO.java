package com.JoaoGustavo.DTO.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JoaoGustavo.DTO.entities.entityDTO;
import com.JoaoGustavo.DTO.repositories.repositoryDTO;

@Service
public class serviceDTO {

	private final repositoryDTO repositoriesDTO;
	
	@Autowired
	public serviceDTO(repositoryDTO repositoriesDTO) {
		this.repositoriesDTO = repositoriesDTO;
	}
	
	public entityDTO salvar(entityDTO entitiesDTO) {
		entityDTO salvarEntityDTO = repositoriesDTO.save(entitiesDTO);
		return new entityDTO(salvarEntityDTO.getId(), salvarEntityDTO.getTitulo(), salvarEntityDTO.getAutor());
	}
	
	public entityDTO atualizar(Long id, entityDTO entitiesDTO) {
		entityDTO existeEntity = repositoriesDTO.findById(id).orElseThrow(() -> new RuntimeException("Livro não encontrado"));
		existeEntity.setTitulo(entitiesDTO.getTitulo());
		existeEntity.setAutor(entitiesDTO.getAutor());
		entityDTO updatedEntity = repositoriesDTO.save(existeEntity);
		return new entityDTO(updatedEntity.getId(), updatedEntity.getTitulo(), updatedEntity.getAutor());
	}
	
	public boolean deletar(Long id) {
		Optional <entityDTO> existeCliente = repositoriesDTO.findById(id);
		if(existeCliente.isPresent()) {
			repositoriesDTO.deleteById(id);
			return true;
		}
		return false;
	}
	
	public List<entityDTO> buscarTodos(){
		return repositoriesDTO.findAll();
	}
	
	public entityDTO buscarPorId(Long id) {
		Optional <entityDTO> Entity = repositoriesDTO.findById(id);
		return Entity.orElse(null);
	}
	
}
