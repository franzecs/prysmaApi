package com.ikytus.prysma.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikytus.prysma.domain.User;
import com.ikytus.prysma.dto.UserDTO;
import com.ikytus.prysma.repository.UserRepository;
import com.ikytus.prysma.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = repo.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert (User user) {
		return repo.insert(user);
	}
		
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User user) {
		User newUser = findById(user.getId());
		updateData(newUser, user);
		return repo.save(newUser);
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
}
