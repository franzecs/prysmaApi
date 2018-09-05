package com.ikytus.wsmongo.dto;

import java.io.Serializable;

import com.ikytus.wsmongo.domain.Produto;

public class ProdutoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String descricao;
	private Double valor;
	
	public ProdutoDTO() {
		
	}

	public ProdutoDTO(Produto produto) {
		super();
		this.id = produto.getId();
		this.descricao = produto.getDescricao();
		this.valor = produto.getValor();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}	
}
