package com.mohanalavala.FruitMarketplaceApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohanalavala.FruitMarketplaceApplication.model.Fruit;
import com.mohanalavala.FruitMarketplaceApplication.model.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer>{

	public Seller findByName(String name);

	Seller findByEmail(String email);
}
