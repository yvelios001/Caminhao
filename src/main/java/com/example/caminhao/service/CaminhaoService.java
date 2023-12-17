package com.example.caminhao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import com.example.caminhao.entidades.Caminhao;
import com.example.caminhao.repository.CaminhaoRepository;

@Service
public class CaminhaoService {

	@Autowired
	private CaminhaoRepository caminhaoRepository;

	public void cadastrarCaminhao(Caminhao caminhao) {

		validarCadastroCaminhao(caminhao);

		caminhaoRepository.save(caminhao);

	}

	@SuppressWarnings("deprecation")
	private void validarCadastroCaminhao(Caminhao caminhao) {

		if (caminhao == null) {
			throw new HttpMessageNotReadableException("Hoje não Rodrigo");
		}
		if (caminhao.getPlaca() == null || caminhao.getPlaca().isEmpty()) {
			throw new RuntimeException("Hoje não Rodrigo");

		}

	}

}
