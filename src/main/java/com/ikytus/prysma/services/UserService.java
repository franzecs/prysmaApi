package com.ikytus.prysma.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikytus.prysma.domain.Empresa;
import com.ikytus.prysma.domain.User;
import com.ikytus.prysma.dto.UserDTO;
import com.ikytus.prysma.repository.EmpresaRepository;
import com.ikytus.prysma.repository.UserRepository;
import com.ikytus.prysma.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private EmpresaService empresaService;	
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert (User user) {
		return userRepository.insert(user);
	}
		
	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	public User update(User user) {
		User newUser = findById(user.getId());
		updateData(newUser, user);
		return userRepository.save(newUser);
	}
	
	public void updateData(User newUser, User user) {
		if(user.getSenha() != null) {
			newUser.setSenha(user.getSenha());
		}
		newUser.setNome(user.getNome());
		newUser.setEmail(user.getEmail());
		newUser.setEmpresa(user.getEmpresa());
		newUser.setEndereco(user.getEndereco());
		newUser.setIsAtivo(user.getIsAtivo());
		newUser.setPerfis(user.getPerfis());
		newUser.setUrl_perfil(user.getUrl_perfil());
	}
	
	public User fromDTO(UserDTO userDto) {
		return new User(userDto.getId(), userDto.getNome(), userDto.getEmail(), "", userDto.getPerfis(), userDto.getIsAtivo(), userDto.getEndereco(), userDto.getEmpresa());
	}
	
	public List<Empresa> empresasFindByUser(String id){
			List<Empresa> empresas = new ArrayList<>();
			empresas.add(empresaService.findById(id));
			empresas.addAll(empresaRepository.findByMatrizId(id));
		return empresas;
	}
}
