package com.ikytus.wsmongo.dto;

import java.io.Serializable;

import com.ikytus.wsmongo.domain.Empresa;

public class EmpresaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String nome;
	
	public EmpresaDTO() {
		
	}

	public EmpresaDTO(Empresa empresa) {
		super();
		this.id = empresa.getId();
		this.nome = empresa.getNome();
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
}
