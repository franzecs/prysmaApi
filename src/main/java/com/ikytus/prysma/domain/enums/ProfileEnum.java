package com.ikytus.prysma.domain.enums;

public enum ProfileEnum {
	
	ROLE_ADMIN_SISTEMA(1, "ADMINISTRADOR DO SISTEMA"),
	ROLE_ADMIN_EMPRESA(2, "ADMINISTRADOR DA EMPRESA"),
	ROLE_GERENTE_LOJA(3, "GERENTE DA UNIDADE"),
	ROLE_VENDEDOR(4, "VENDEDOR"),
	ROLE_CLIENTE(5, "CLIENTE");
		
	private int cod;
	private String descricao;
	
	private ProfileEnum(int cod, String descricao) {
		this.cod = cod;
		this.descricao=descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static ProfileEnum toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		
		for (ProfileEnum x : ProfileEnum.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
