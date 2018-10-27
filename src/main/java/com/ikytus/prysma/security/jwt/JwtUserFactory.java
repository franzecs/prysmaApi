package com.ikytus.prysma.security.jwt;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.ikytus.prysma.domain.User;

public class JwtUserFactory {
	 private JwtUserFactory() {
	    }

	    public static JwtUser create(User user) {
	        return new JwtUser(
	                user.getId(),
	                user.getEmail(),
	                user.getPassword(),
	                user.getProfile().stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList())
	                //mapToGrantedAuthorities(user.getProfile())
	        );
	    }
/*
	    private static List<GrantedAuthority> mapToGrantedAuthorities(List<ProfileEnum> profileEnum) {
	    		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(); 
	    		authorities = profileEnum.stream().map(x-> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList()); 
	    		return   authorities ;
	    }*/
}
