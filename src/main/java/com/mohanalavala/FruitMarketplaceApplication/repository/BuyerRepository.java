package com.mohanalavala.FruitMarketplaceApplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mohanalavala.FruitMarketplaceApplication.model.Buyer;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Integer>{

	Buyer findByName(String name);
	Optional<Buyer> findByEmail(String email);
}
