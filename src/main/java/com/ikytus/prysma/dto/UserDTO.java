package com.ikytus.prysma.dto;

import java.io.Serializable;
import java.util.List;

import com.ikytus.prysma.domain.User;
import com.ikytus.prysma.domain.enums.ProfileEnum;
import com.ikytus.prysma.domain.models.Endereco;

public class UserDTO implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String nome;
	private String email;
	private List<ProfileEnum> profile;
    private Boolean isAtivo;
    private Endereco endereco;
    private EmpresaDTO empresa;
    private String url_perfil;
	
	public UserDTO() {
	}
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.nome = user.getNome();
		this.email = user.getEmail();
		this.isAtivo = user.getIsAtivo();
		this.endereco = user.getEndereco();
		this.empresa = user.getEmpresa();
		this.url_perfil = user.getUrl_perfil();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<ProfileEnum> getProfile() {
		return profile;
	}

	public void setProfile(List<ProfileEnum> profile) {
		this.profile = profile;
	}

	public Boolean getIsAtivo() {
		return isAtivo;
	}

	public void setIsAtivo(Boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public EmpresaDTO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
	}

	public String getUrl_perfil() {
		return url_perfil;
	}

	public void setUrl_perfil(String url_perfil) {
		this.url_perfil = url_perfil;
	}
}
