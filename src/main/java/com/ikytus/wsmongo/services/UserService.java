package com.ikytus.wsmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikytus.wsmongo.domain.User;
import com.ikytus.wsmongo.dto.UserDTO;
import com.ikytus.wsmongo.repository.UserRepository;
import com.ikytus.wsmongo.services.exception.ObjectNotFoundException;

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
		if(user.getNome() != null) {
			newUser.setNome(user.getNome());
		}
		if(user.getEmail() != null) {
			newUser.setEmail(user.getEmail());
		}		
	}
	
	public User fromDTO(UserDTO userDto) {
		return new User(userDto.getId(), userDto.getNome(), userDto.getEmail(), "", userDto.getPerfis(), userDto.getIsAtivo(), userDto.getEndereco(), userDto.getEmpresa());
	}
}
