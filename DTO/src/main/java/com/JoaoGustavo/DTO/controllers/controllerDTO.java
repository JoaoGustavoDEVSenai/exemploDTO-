package com.JoaoGustavo.DTO.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JoaoGustavo.DTO.entities.entityDTO;
import com.JoaoGustavo.DTO.services.serviceDTO;

@RestController
@RequestMapping("/livros")
public class controllerDTO {

	private final serviceDTO servicesDTO;
	
	@Autowired
	public controllerDTO(serviceDTO servicesDTO) {
		this.servicesDTO = servicesDTO;
	}
	
	@PostMapping
	public ResponseEntity<entityDTO> criar(@RequestBody @Validated entityDTO entitiesDTO){
		entityDTO salvarEntity = servicesDTO.salvar(entitiesDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvarEntity);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<entityDTO> alteraClienteControl(@PathVariable Long id, @RequestBody @Validated entityDTO entitiesDTO){
		entityDTO alteraDTO = servicesDTO.atualizar(id, entitiesDTO);
		if(alteraDTO != null) {
			return ResponseEntity.ok(alteraDTO);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<entityDTO> buscarLivroPorId(@PathVariable Long id){
		boolean apagar = servicesDTO.deletar(id);
		if(apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<List<entityDTO>> buscaTodosLivros(){
		List<entityDTO> Entity = servicesDTO.buscarTodos();
		return ResponseEntity.ok(Entity);
	}
	
}
