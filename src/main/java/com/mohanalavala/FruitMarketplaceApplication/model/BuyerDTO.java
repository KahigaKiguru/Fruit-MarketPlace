package com.mohanalavala.FruitMarketplaceApplication.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class BuyerDTO implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Buyer buyer;
	
	public BuyerDTO(Buyer buyer) {
		this.buyer = buyer;
	}

	public BuyerDTO() {}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<Role> roles = buyer.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return buyer.getPassword();
	}

	@Override
	public String getUsername() {
		
		return buyer.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}
	
	public int getId() {
		return buyer.getId();
	}
	
}
