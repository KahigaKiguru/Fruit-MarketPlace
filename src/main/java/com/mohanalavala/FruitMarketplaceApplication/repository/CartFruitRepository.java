package com.mohanalavala.FruitMarketplaceApplication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mohanalavala.FruitMarketplaceApplication.model.Fruit;

@Repository
public interface CartFruitRepository extends CrudRepository<Fruit, Integer>{

}
