package com.ikytus.prysma.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ikytus.prysma.domain.models.Olho;
import com.ikytus.prysma.dto.EmpresaDTO;

@Document
public class Pedido implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@JsonFormat(pattern="dd/MM/yyy hh:mm")
	private Date data;
    private Cliente cliente;
    private Cliente usuario;
    private Olho olhoDireito;
    private Olho olhoEsquerdo;
    private List<String> formaPagamento = new ArrayList<>();
    private String condicaoPagamento;
    private List<String> documentos;
    private List<Item> itens;
    private String observacao;
    
    private EmpresaDTO empresa;
    
    public Pedido() {
    }

	public Pedido(String id, Date data, Cliente cliente, Cliente usuario, Olho olhoDireito, Olho olhoEsquerdo,
			List<String> formaPagamento, String condicaoPagamento, List<String> documentos, String observacao, EmpresaDTO empresa) {
		super();
		this.id = id;
		this.data = data;
		this.cliente = cliente;
		this.usuario = usuario;
		this.olhoDireito = olhoDireito;
		this.olhoEsquerdo = olhoEsquerdo;
		this.formaPagamento = formaPagamento;
		this.condicaoPagamento = condicaoPagamento;
		this.documentos = documentos;
		this.observacao = observacao;
		this.empresa = empresa;
	}
	
	public double getValorTotal() {
		double soma = 0.0;
		for (Item item : itens) {
			soma = soma +item.getValorTotalItem();
		}
		return soma;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getUsuario() {
		return usuario;
	}

	public void setUsuario(Cliente usuario) {
		this.usuario = usuario;
	}

	public Olho getOlhoDireito() {
		return olhoDireito;
	}

	public void setOlhoDireito(Olho olhoDireito) {
		this.olhoDireito = olhoDireito;
	}

	public Olho getOlhoEsquerdo() {
		return olhoEsquerdo;
	}

	public void setOlhoEsquerdo(Olho olhoEsquerdo) {
		this.olhoEsquerdo = olhoEsquerdo;
	}

	public List<String> getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(List<String> formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public String getCondicaoPagamento() {
		return condicaoPagamento;
	}

	public void setCondicaoPagamento(String condicaoPagamento) {
		this.condicaoPagamento = condicaoPagamento;
	}

	public List<String> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<String> documentos) {
		this.documentos = documentos;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public EmpresaDTO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
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
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}   
}