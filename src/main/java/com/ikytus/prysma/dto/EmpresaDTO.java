package com.ikytus.prysma.dto;

import java.io.Serializable;

import com.ikytus.prysma.domain.Empresa;

public class EmpresaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String nome;
    private String tipo;
    private EmpresaDTO matriz;
    private String email;
	
	public EmpresaDTO() {
		
	}

	public EmpresaDTO(Empresa empresa) {
		super();
		this.id = empresa.getId();
		this.nome = empresa.getNome();
		this.tipo = empresa.getTipo();
		this.matriz = empresa.getMatriz();
		this.email = empresa.getEmail();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public EmpresaDTO getMatriz() {
		return matriz;
	}

	public void setMatriz(EmpresaDTO matriz) {
		this.matriz = matriz;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
