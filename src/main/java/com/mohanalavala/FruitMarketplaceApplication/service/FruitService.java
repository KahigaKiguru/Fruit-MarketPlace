package com.mohanalavala.FruitMarketplaceApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mohanalavala.FruitMarketplaceApplication.model.Fruit;
import com.mohanalavala.FruitMarketplaceApplication.repository.FruitRepository;

@Service
public class FruitService {

	private FruitRepository fruitRepository;

	public FruitService(FruitRepository fruitRepository) {
		super();
		this.fruitRepository = fruitRepository;
	}
	
//	create a fruit
	public void createFruit(Fruit fruit) {
		fruitRepository.save(fruit);
	}
//	update a fruit
	public void updateFruit(Fruit fruit) {
		fruitRepository.save(fruit);
	}
//	delete a fruit
	public void deleteFruit(Fruit fruit) {
		fruitRepository.delete(fruit);
	}
//	get all fruits
	public Iterable<Fruit> getAllFruits() {
		return fruitRepository.findAll();
	}
//	get fruit by name
	public List<Fruit> getFruitByName(String name) {
		return fruitRepository.findByName(name);
	}
////	get fruit by type
//	public List<Fruit> getFruitsByType(FruitType type) {
//		return fruitRepository.findByFruitType(type);
//	}
//	get fruit by id
	public Optional<Fruit> getFruitById(int id) {
		return fruitRepository.findById(id);
	}
////	get fruit by weight
//	public List<Fruit> getFruitsByWeight(double weight) {
//		return fruitRepository.findByWeight(weight);
//	}
//	get fruits by seller
	public List<Fruit> getFruitsBySeller(int seller_id) {
		return fruitRepository.findBySellerId(seller_id);
	}
}
