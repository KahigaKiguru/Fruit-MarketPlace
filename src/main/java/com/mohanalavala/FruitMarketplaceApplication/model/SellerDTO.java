package com.mohanalavala.FruitMarketplaceApplication.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mohanalavala.FruitMarketplaceApplication.service.FruitService;
import com.mohanalavala.FruitMarketplaceApplication.service.SellerService;

public class SellerDTO implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Seller seller;
	
	@Autowired
	private FruitService fruitService;


	
	public SellerDTO(Seller seller) {
		this.seller = seller;
	}

	public SellerDTO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<Role> roles = seller.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return seller.getPassword();
	}

	@Override
	public String getUsername() {
		return seller.getEmail();
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
	
	public String getName() {
		return seller.getName();
	}
	
	public List<Order> getOrders(){
		return seller.getOrders();
	}
	
	public List<Fruit> getFruits(){
//		List<Fruit> fruits = fruitService.getFruitsBySeller(seller.getId());
		return seller.getFruits();
	}
	
	public int getId() {
		return seller.getId();
	}

}
