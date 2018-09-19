package com.ikytus.prysma.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikytus.prysma.domain.Cliente;
import com.ikytus.prysma.repository.ClienteRepository;
import com.ikytus.prysma.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
			
	public List<Cliente> findAll(){
		return clienteRepository.findAll();
	}
	
	public Cliente findById(String id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public Cliente insert (Cliente cliente) {
		return clienteRepository.insert(cliente);
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
	
	public List<Empresa> empresasFindByCliente(String id){
			List<Empresa> empresas = new ArrayList<>();
			empresas.add(empresaService.findById(id));
			empresas.addAll(empresaRepository.findByMatrizId(id));
		return empresas;
	}
	*/
}
