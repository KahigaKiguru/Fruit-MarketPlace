package com.mohanalavala.FruitMarketplaceApplication.service;

import java.util.Arrays;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mohanalavala.FruitMarketplaceApplication.model.Buyer;
import com.mohanalavala.FruitMarketplaceApplication.model.BuyerDTO;
import com.mohanalavala.FruitMarketplaceApplication.model.Cart;
import com.mohanalavala.FruitMarketplaceApplication.model.Role;
import com.mohanalavala.FruitMarketplaceApplication.repository.BuyerRepository;

@Service
public class BuyerService implements UserDetailsService {

	@Autowired
	private BuyerRepository buyerRepository;
	

	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

//	create a buyer
	public void createBuyer(Buyer buyer) {
		buyer.setName(buyer.getName());
		buyer.setEmail(buyer.getEmail());
		buyer.setPassword(passwordEncoder.encode(buyer.getPassword()));
		buyer.setRoles(Arrays.asList(new Role("ROLE_BUYER")));
		buyer.setCart(new Cart(buyer));
		
//		persist buyer
		buyerRepository.save(buyer);
	}

	
//	update buyer
	public void updateBuyer(Buyer buyer) {
		buyerRepository.save(buyer);
	}
	
//	get all buyers
	public Iterable<Buyer> getAllBuyers() {
		return buyerRepository.findAll();
	}
//	get buyer by id
	public Optional<Buyer> getBuyerById(int id) {
		return buyerRepository.findById(id);
	}
//	get buyer by name
	public Buyer getBuyerByName(String name) {
		return buyerRepository.findByName(name);
	}
//	get buyer by email
	public Optional<Buyer> getBuyerByEmail(String email) {
		return buyerRepository.findByEmail(email);
	}
//	delete buyer
	public void deleteBuyer(int id) {
		buyerRepository.deleteById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Buyer buyer = buyerRepository.findByEmail(email).get();
		
		if(buyer != null) {
			return new BuyerDTO(buyer);
		}else 
			throw new UsernameNotFoundException("wrong credentials");
	}
}
