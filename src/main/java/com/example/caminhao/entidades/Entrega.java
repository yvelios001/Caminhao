package com.example.caminhao.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Entrega {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "caminhao_id", nullable = false)
	private Caminhao caminhao;

	@ManyToOne
	@JoinColumn(name = "motorista_id", nullable = false)
	private Motorista motorista;
	@Column (nullable=false)
	private LocalDate dataFim;
	@Column(nullable=false)
	private LocalDate dataEntrega;

	@Column(nullable = false)
	private BigDecimal valor;
	@Column(nullable=false)
	private BigDecimal valorFrete;

	private boolean temSeguro;

	private boolean ePerigosa;

	public boolean iseValiosa() {
		return eValiosa;
	}

	public void seteValiosa(boolean eValiosa) {
		this.eValiosa = eValiosa;
	}

	private boolean eValiosa;

	public enum TipoEntrega {
		ELETRONICOS, COMBUSTIVEL,
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoEntrega tipo;

	public enum LocalEntrega {
		AMAZONAS, NORDESTE, ARGENTINA
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private LocalEntrega local;

	public Entrega() {

	}



	public Entrega(Caminhao caminhao, Motorista motorista) {
		super();
		this.caminhao = caminhao;
		this.motorista = motorista;
	}

	public LocalEntrega getLocal() {
		return local;
	}

	public void setLocal(LocalEntrega local) {
		this.local = local;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Caminhao getCaminhao() {
		return caminhao;
	}

	public void setCaminhao(Caminhao caminhao) {
		this.caminhao = caminhao;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public boolean isTemSeguro() {
		return temSeguro;
	}

	public void setTemSeguro(boolean temSeguro) {
		this.temSeguro = temSeguro;
	}

	public boolean isePerigosa() {
		return ePerigosa;
	}

	public void setePerigosa(boolean ePerigosa) {
		this.ePerigosa = ePerigosa;
	}

	public TipoEntrega getTipo() {
		return tipo;
	}

	public void setTipo(TipoEntrega tipo) {
		this.tipo = tipo;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}

}
