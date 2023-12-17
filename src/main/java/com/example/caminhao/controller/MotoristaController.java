package com.example.caminhao.controller;

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

import com.example.caminhao.entidades.Motorista;
import com.example.caminhao.repository.MotoristaRepository;
import com.example.caminhao.service.MotoristaService;

import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/motorista")
public class MotoristaController {
	@Autowired
	private MotoristaService motoristaService;
	
	@Autowired
	private MotoristaRepository motoristaRepository;

	@PostMapping
	public ResponseEntity<String> CadastrarMotorista(@RequestBody Motorista motorista) {

		try {
			motoristaService.cadastrarMotorista(motorista);
			return new ResponseEntity<>("Caminhão cadastrado com sucesso", HttpStatus.CREATED);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping("/pegar")
	public ResponseEntity<List<Motorista>> getAllMotoristas(){
		List <Motorista> motoristas=motoristaRepository.findAll();
		if(motoristas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(motoristas, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Motorista>atualizarMotorista(@PathVariable Long id, @RequestBody @NotNull Motorista novoMotorista) {
		motoristaService.cadastrarMotorista(novoMotorista);
		if (!motoristaRepository.existsById(id)) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	Motorista motoristaExiste = motoristaRepository.findById(id).get();	
	
	motoristaExiste.setNome(novoMotorista.getNome());
	Motorista motoristaAtualizado = motoristaRepository.save(motoristaExiste);
	return new ResponseEntity<>(motoristaAtualizado,HttpStatus.OK);
	
	
		
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity <Void> deleteById(@PathVariable Long id){
	
		try {
			Optional <Motorista> motoristaOptional = motoristaRepository.findById(id);
			if(motoristaOptional.isPresent()) {
				motoristaRepository.deleteById(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}



