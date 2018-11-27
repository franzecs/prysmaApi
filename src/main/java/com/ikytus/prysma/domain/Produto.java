package com.ikytus.prysma.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ikytus.prysma.dto.EmpresaDTO;

@Document
public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String nome;
	private String fabricante;
	private int estoque;
    private Double valor;
    private EmpresaDTO empresa;
    private String url_img;
        
    public Produto() {
    }
	
	public Produto(String id, String nome, String fabricante, int estoque, Double valor, EmpresaDTO empresa, String url_img) {
		super();
		this.id = id;
		this.nome = nome;
		this.fabricante = fabricante;
		this.estoque = estoque;
		this.valor = valor;
		this.empresa = empresa;
		this.url_img = url_img;
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

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public EmpresaDTO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
	}
	
	public String getUrl_img() {
		return url_img;
	}

	public void setUrl_img(String url_img) {
		this.url_img = url_img;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}