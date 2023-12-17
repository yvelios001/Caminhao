package com.example.caminhao.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Caminhao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String placa;
	private LocalDate ultimaEntregaData;
	@JsonIgnore
	@OneToMany(mappedBy = "caminhao", cascade = CascadeType.ALL)
	private List<Entrega> entregas = new ArrayList<>();

	public Caminhao() {
		this.entregas = new ArrayList<>();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Entrega> getEntregas() {
		return entregas;
	}

	public void setEntregas(List<Entrega> entregas) {
		this.entregas = entregas;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public LocalDate getUltimaEntregaData() {
		return ultimaEntregaData;
	}

	public void setUltimaEntregaData(LocalDate ultimaEntregaData) {
		this.ultimaEntregaData = ultimaEntregaData;
	}

}
