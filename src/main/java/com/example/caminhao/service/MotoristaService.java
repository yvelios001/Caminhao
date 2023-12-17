package com.example.caminhao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.caminhao.entidades.Motorista;
import com.example.caminhao.repository.MotoristaRepository;

@Service
public class MotoristaService {

	@Autowired
	private MotoristaRepository motoristaRepository;

	public void cadastrarMotorista(Motorista motorista) {

		validarCadastro(motorista);
		motoristaRepository.save(motorista);

	}

	private void validarCadastro(Motorista motorista) {

		if (motorista.getNome().isEmpty()) {
			throw new RuntimeException("Hoje não rodrigo ");

		}

	}
	

}
