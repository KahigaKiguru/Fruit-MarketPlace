package com.mohanalavala.FruitMarketplaceApplication.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.mohanalavala.FruitMarketplaceApplication.model.Order;
import com.mohanalavala.FruitMarketplaceApplication.model.Role;
import com.mohanalavala.FruitMarketplaceApplication.model.Seller;
import com.mohanalavala.FruitMarketplaceApplication.model.SellerDTO;
import com.mohanalavala.FruitMarketplaceApplication.repository.SellerRepository;

@Service
public class SellerService implements UserDetailsService {

	@Autowired
	private SellerRepository sellerRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

//	create a seller
	public void createSeller(Seller seller) {
		seller.setName(seller.getName());
		seller.setEmail(seller.getEmail());
		seller.setPassword(passwordEncoder.encode(seller.getPassword()));
		seller.setRoles(Arrays.asList(new Role("ROLE_SELLER")));
		seller.setTotalRevenue(0.0);
		seller.setFruits(null);
		seller.setFruits_sold(0);
		seller.setOrders(null);
		
		sellerRepository.save(seller);
	}

//	update a seller
	public void updateSeller(Seller seller) {
//		update address
		
		sellerRepository.save(seller);
	}

//	delete a seller
	public void deleteSeller(Seller seller) {
		sellerRepository.delete(seller);
	}

//	get all sellers
	public Iterable<Seller> getAllSellers() {
		return sellerRepository.findAll();
	}

//	get seller by name
	public Seller getSellerByName(String name) {
		return sellerRepository.findByName(name);
	}

//	get seller by id
	public Optional<Seller> getSellerById(int id) {
		return sellerRepository.findById(id);
	}

////	get total fruits sold
//	public int getTotalFruitsSold(int seller_id) {
//
//		Optional<Seller> seller = sellerRepository.findById(seller_id);
//		
//		List<Order> orders = seller.get().getOrders();
//		
//		int total_fruits = 0;
//		for (Order order : orders) {
//			total_fruits += order.getFruits().size();
//		}
//		
//		return total_fruits;
//	}

////	get total revenue
//	public double getTotalRevenue(int seller_id) {
//
//		Optional<Seller> seller = sellerRepository.findById(seller_id);
//
//		List<Fruit> fruits = seller.get().getFruits();
//		
//		double revenue = 0;
//
//		for (Fruit fruit : fruits) {
//			revenue += fruit.getPrice();
//		}
//		
//		seller.get().setTotalRevenue(revenue);
//		
//		return revenue;
//	}

//	get all orders
	public List<Order> getAllOrders(int seller_id) {
		Optional<Seller> seller =  sellerRepository.findById(seller_id);
		
		return seller.get().getOrders();
	}
//	update order
	public void updateOrder(Seller seller) {
		sellerRepository.save(seller);
	}
	
//	get Seller by email
	public Seller getSellerByEmail(String email) {
		return sellerRepository.findByEmail(email);
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Seller seller = sellerRepository.findByEmail(email);
		
		if(seller != null) {
			return new SellerDTO(seller);
			
		}else
			throw new UsernameNotFoundException("invalid credentials");
	}
}
