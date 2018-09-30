package com.ikytus.prysma.domain.models;

import java.io.Serializable;

public class Olho implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String lado;
	private String esfico;
	private String cilindrico;
	private String eixo;
	private String adicao;
	private String dnp;
	private String alt;
	
	public Olho() {
	}

	public Olho(String lado, String esfico, String cilindrico, String eixo, String adicao, String dnp, String alt) {
		super();
		this.lado = lado;
		this.esfico = esfico;
		this.cilindrico = cilindrico;
		this.eixo = eixo;
		this.adicao = adicao;
		this.dnp = dnp;
		this.alt = alt;
	}

	public String getLado() {
		return lado;
	}

	public void setLado(String lado) {
		this.lado = lado;
	}

	public String getEsfico() {
		return esfico;
	}

	public void setEsfico(String esfico) {
		this.esfico = esfico;
	}

	public String getCilindrico() {
		return cilindrico;
	}

	public void setCilindrico(String cilindrico) {
		this.cilindrico = cilindrico;
	}

	public String getEixo() {
		return eixo;
	}

	public void setEixo(String eixo) {
		this.eixo = eixo;
	}

	public String getAdicao() {
		return adicao;
	}

	public void setAdicao(String adicao) {
		this.adicao = adicao;
	}

	public String getDnp() {
		return dnp;
	}

	public void setDnp(String dnp) {
		this.dnp = dnp;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}
}
