package com.ikytus.prysma.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ikytus.prysma.domain.Produto;
import com.ikytus.prysma.repository.ProdutoRepository;
import com.ikytus.prysma.services.exception.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
			
	public Page<Produto> findAll(int page, int count){
		return produtoRepository.findAll(this.pages(page, count));
	}
	
	public Page<Produto> findByEmpresa(int page, int count, String empresaId) {
		return this.produtoRepository.findByEmpresaId(this.pages(page, count), empresaId);
	}
		
	public Page<Produto> findByNome(int page, int count, String nome, String empresaId) {

		return this.produtoRepository.findByNomeIgnoreCaseContainingAndEmpresaId(this.pages(page, count), nome, empresaId);
	}
		
	public Produto findById(String id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
		
	public Produto createOrUpdate(Produto produto) {
		return this.produtoRepository.save(produto);
	}
		
	public void delete(String id) {
		findById(id);
		produtoRepository.deleteById(id);
	}
	
	public Produto update(Produto produto) {
		Produto newProduto = findById(produto.getId());
		//updateData(newProduto, produto);
		return produtoRepository.save(newProduto);
	}
	/*
	public void updateData(Produto newProduto, Produto produto) {
		if(produto.getSenha() != null) {
			newProduto.setSenha(produto.getSenha());
		}
		newProduto.setNome(produto.getNome());
		newProduto.setEmail(produto.getEmail());
		newProduto.setEmpresa(produto.getEmpresa());
		newProduto.setEndereco(produto.getEndereco());
		newProduto.setIsAtivo(produto.getIsAtivo());
		newProduto.setPerfis(produto.getPerfis());
		newProduto.setUrl_perfil(produto.getUrl_perfil());
	}
	
	public Produto fromDTO(ProdutoDTO produtoDto) {
		return new Produto(produtoDto.getId(), produtoDto.getNome(), produtoDto.getEmail(), "", produtoDto.getPerfis(), produtoDto.getIsAtivo(), produtoDto.getEndereco(), produtoDto.getEmpresa());
	}
	
	*/
	
	public Pageable pages(int page, int count) {
		return PageRequest.of(page, count);
	}
}
