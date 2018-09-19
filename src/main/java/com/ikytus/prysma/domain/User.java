package com.ikytus.prysma.domain;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.ikytus.prysma.domain.enums.ProfileEnum;
import com.ikytus.prysma.dto.EmpresaDTO;

@Document
public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String nome;
	
	@Indexed(unique = true)
	@NotBlank(message = "Email required")
	@Email(message = "Email invalid")
	private String email;
	
	@NotBlank(message = "Password required")
	@Size(min = 6)
	private String password;
	
	private ProfileEnum profile;
    private Boolean isAtivo;
    private Endereco endereco;
    private EmpresaDTO empresa;
    private String url_perfil;
			
    
	public User() {
	}

	public User(String id, String nome, String email, String password, ProfileEnum profile,	Boolean isAtivo, 
				Endereco endereco, EmpresaDTO empresa, String url_perfil) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.profile = profile;
		this.isAtivo = isAtivo;
		this.endereco = endereco;
		this.empresa = empresa;
		this.url_perfil = url_perfil;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ProfileEnum getProfile() {
		return profile;
	}

	public void setProfile(ProfileEnum profile) {
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}