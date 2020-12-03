package com.mohanalavala.FruitMarketplaceApplication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mohanalavala.FruitMarketplaceApplication.model.Cart;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer>{

}
