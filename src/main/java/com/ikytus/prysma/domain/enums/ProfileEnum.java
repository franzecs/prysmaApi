package com.ikytus.prysma.domain.enums;

public enum ProfileEnum {
	
	ADMIN_SISTEMA(1, "ROLE_ADMIN_SISTEMA"),
	ADMIN_EMPRESA(2, "ROLE_ADMIN_EMPRESA"),
	GERENTE_LOJA(3, "ROLE_GERENTE_LOJA"),
	VENDEDOR(4, "ROLE_VENDEDOR"),
	CLIENTE(5, "ROLE_CLIENTE");
		
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
