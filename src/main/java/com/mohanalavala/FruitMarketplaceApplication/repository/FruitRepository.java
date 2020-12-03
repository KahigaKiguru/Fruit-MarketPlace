package com.mohanalavala.FruitMarketplaceApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohanalavala.FruitMarketplaceApplication.model.Fruit;

@Repository
public interface FruitRepository extends JpaRepository<Fruit, Integer>{

	List<Fruit> findByName(String name);
	List<Fruit> findBySellerId(int seller_id);
}
