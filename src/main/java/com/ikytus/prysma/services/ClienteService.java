package com.ikytus.prysma.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ikytus.prysma.domain.Cliente;
import com.ikytus.prysma.repository.ClienteRepository;
import com.ikytus.prysma.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
			
	public Page<Cliente> findAll(int page, int count){
		//Pageable pages = PageRequest.of(page, count);
		return clienteRepository.findAll(this.pages(page, count));
	}
	
	public Page<Cliente> findByEmpresa(int page, int count, String empresaId) {
		//Pageable pages = PageRequest.of(page, count);
		return this.clienteRepository.findByEmpresaId(this.pages(page, count), empresaId);
	}
		
	public Page<Cliente> findByNome(int page, int count, String nome, String empresaId) {
		//Pageable pages = PageRequest.of(page, count);
		return this.clienteRepository.findByNomeIgnoreCaseContainingAndEmpresaId(this.pages(page, count), nome, empresaId);
	}
	
	public Page<Cliente> findByCpf(int page, int count, String cpf, String empresaId) {
		//Pageable pages = PageRequest.of(page, count);
		return this.clienteRepository.findByCpfIgnoreCaseContainingAndEmpresaId(this.pages(page, count), cpf, empresaId);
	}
	
	public Page<Cliente> findByAniversario(int page, int count, Date dataInicial, Date dataFinal, String empresaId) {
		//Pageable pages = PageRequest.of(page, count);
		return this.clienteRepository.findByDataNascimentoBetweenAndEmpresaId(this.pages(page, count), dataInicial, dataFinal, empresaId);
	}
	
	public Cliente findById(String id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
		
	public Cliente createOrUpdate(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}
		
	public void delete(String id) {
		findById(id);
		clienteRepository.deleteById(id);
	}
	
	public Cliente update(Cliente cliente) {
		Cliente newCliente = findById(cliente.getId());
		//updateData(newCliente, cliente);
		return clienteRepository.save(newCliente);
	}
	/*
	public void updateData(Cliente newCliente, Cliente cliente) {
		if(cliente.getSenha() != null) {
			newCliente.setSenha(cliente.getSenha());
		}
		newCliente.setNome(cliente.getNome());
		newCliente.setEmail(cliente.getEmail());
		newCliente.setEmpresa(cliente.getEmpresa());
		newCliente.setEndereco(cliente.getEndereco());
		newCliente.setIsAtivo(cliente.getIsAtivo());
		newCliente.setPerfis(cliente.getPerfis());
		newCliente.setUrl_perfil(cliente.getUrl_perfil());
	}
	
	public Cliente fromDTO(ClienteDTO clienteDto) {
		return new Cliente(clienteDto.getId(), clienteDto.getNome(), clienteDto.getEmail(), "", clienteDto.getPerfis(), clienteDto.getIsAtivo(), clienteDto.getEndereco(), clienteDto.getEmpresa());
	}
	
	*/
	
	public Pageable pages(int page, int count) {
		return PageRequest.of(page, count);
	}
}
