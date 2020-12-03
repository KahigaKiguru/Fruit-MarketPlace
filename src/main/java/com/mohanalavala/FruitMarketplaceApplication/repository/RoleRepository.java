package com.mohanalavala.FruitMarketplaceApplication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mohanalavala.FruitMarketplaceApplication.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{

}
