package com.mohanalavala.FruitMarketplaceApplication.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohanalavala.FruitMarketplaceApplication.model.Cart;
import com.mohanalavala.FruitMarketplaceApplication.model.Fruit;
import com.mohanalavala.FruitMarketplaceApplication.repository.CartFruitRepository;
import com.mohanalavala.FruitMarketplaceApplication.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartFruitRepository cartFruitRepository;
	
	
//	create cart
	public void createCart(Cart cart) {
		cartRepository.save(cart);
	}
	
//	get cart by id
	public Cart getCartById(int id) {
		return cartRepository.findById(id).get();
	}
//	update cart
	public void updateCart(Cart cart) {
		cartRepository.save(cart);
	}
//	get cart items
	public List<Fruit> getFruits(int cart_id) {
		Cart cart = cartRepository.findById(cart_id).get();
		return cart.getFruits();
		
	}
//	add fruit to cart
	public void addFruitToCart(int cart_id, Fruit fruit) {
		Cart cart = cartRepository.findById(cart_id).get();
		
		cart.getFruits().add(fruit);
		
		cartFruitRepository.save(fruit);
		
		cartRepository.save(cart);
	}
	
	public void removeFruitFromCart(Fruit fruit) {
		cartFruitRepository.delete(fruit);
	}
//	get fruits in cart
	public List<Fruit> getFruitsInCart(int cart_id){
		Cart cart = cartRepository.findById(cart_id).get();
		List<Fruit> fruits = new ArrayList<Fruit>();
		
		for (Fruit fruit : cart.getFruits()) {
			if(cartFruitRepository.findById(fruit.getId()) != null) {
				fruits.add(fruit);
			}
		}
 		return fruits;
	}
}
