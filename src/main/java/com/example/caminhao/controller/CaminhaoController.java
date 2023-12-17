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

import com.example.caminhao.entidades.Caminhao;
import com.example.caminhao.repository.CaminhaoRepository;
import com.example.caminhao.service.CaminhaoService;

@RestController
@RequestMapping("/caminhao")
public class CaminhaoController {

	@Autowired
	private CaminhaoRepository caminhaoRepository;
	@Autowired
	private CaminhaoService caminhaoService;

	@PostMapping("/cadastrar")
	public ResponseEntity<String> cadastrarCaminhao(@RequestBody Caminhao caminhao) {
		try {
			caminhaoService.cadastrarCaminhao(caminhao);
			return new ResponseEntity<>("Caminhão cadastrado com sucesso", HttpStatus.CREATED);
		} catch (RuntimeException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/pegar")
	public ResponseEntity<List<Caminhao>> getAllCaminhoes() {
		List<Caminhao> caminhoes = caminhaoRepository.findAll();
		if (caminhoes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(caminhoes, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Caminhao> atualizarCaminhao(@PathVariable Long id, @RequestBody Caminhao novoCaminhao) {

		caminhaoService.cadastrarCaminhao(novoCaminhao);
		if (!caminhaoRepository.existsById(id)) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Caminhao caminhaoExistente = caminhaoRepository.findById(id).get();

		caminhaoExistente.setPlaca(novoCaminhao.getPlaca());
		caminhaoExistente.setUltimaEntregaData(novoCaminhao.getUltimaEntregaData());

		Caminhao caminhaoAtualizado = caminhaoRepository.save(caminhaoExistente);

		return new ResponseEntity<>(caminhaoAtualizado, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deleteCaminhao(@PathVariable Long id) {
		try {
			Optional<Caminhao> caminhaoOptional = caminhaoRepository.findById(id);

			if (caminhaoOptional.isPresent()) {
				caminhaoRepository.deleteById(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}