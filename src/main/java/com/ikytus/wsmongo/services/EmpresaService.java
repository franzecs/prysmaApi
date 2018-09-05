package com.ikytus.wsmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikytus.wsmongo.domain.Empresa;
import com.ikytus.wsmongo.dto.EmpresaDTO;
import com.ikytus.wsmongo.repository.EmpresaRepository;
import com.ikytus.wsmongo.services.exception.ObjectNotFoundException;

@Service
public class EmpresaService {
	
	@Autowired
	private EmpresaRepository repo;
	
	public List<Empresa> findAll(){
		return repo.findAll();
	}
	
	public Empresa findById(String id) {
		Optional<Empresa> empresa = repo.findById(id);
		return empresa.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public Empresa insert (Empresa empresa) {
		return repo.insert(empresa);
	}
		
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public Empresa update(Empresa empresa) {
		Empresa newEmpresa = findById(empresa.getId());
		updateData(newEmpresa, empresa);
		return repo.save(newEmpresa);
	}
	
	public void updateData(Empresa newEmpresa, Empresa empresa) {
		if(empresa.getNome() != null) {
			newEmpresa.setNome(empresa.getNome());
		}
		if(empresa.getEmail() != null) {
			newEmpresa.setEmail(empresa.getEmail());
		}		
	}
	
	/*public Empresa fromDTO(EmpresaDTO empresaDto) {
		return new Empresa(empresaDto.getId(), empresaDto.getNome(), empresaDto.getEmail(), "", empresaDto.getPerfis(), empresaDto.getIsAtivo(), empresaDto.getEndereco(), empresaDto.getEmpresa());
	}*/
}
