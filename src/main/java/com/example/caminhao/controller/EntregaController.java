package com.example.caminhao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.caminhao.entidades.Entrega;
import com.example.caminhao.repository.EntregaRepository;
import com.example.caminhao.service.EntregaService;

@RestController
@RequestMapping("/api/entregas")
public class EntregaController {

	@Autowired
	private EntregaService entregaService;

	@PostMapping("/realizar")
	public ResponseEntity<String> realizarEntrega(@RequestBody Entrega entrega) {
		try {
			entregaService.realizarEntrega(entrega);
			return new ResponseEntity<>("Entrega realizada com sucesso", HttpStatus.CREATED);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@Autowired
	private EntregaRepository entregaRepository;

	@GetMapping("/pegar-todas")
	public ResponseEntity<List<Entrega>> getAllEntregas() {
		try {
			List<Entrega> entregas = entregaRepository.findAll();
			if (entregas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(entregas, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/pegar-por-id/{id}")
	public ResponseEntity<Entrega> getEntregaById(@PathVariable Long id) {
		try {
			Optional<Entrega> entrega = entregaRepository.findById(id);

			if (entrega.isPresent()) {
				return new ResponseEntity<>(entrega.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
