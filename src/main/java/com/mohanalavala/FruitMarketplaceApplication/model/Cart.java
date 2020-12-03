package com.mohanalavala.FruitMarketplaceApplication.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carts")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cart_id")
	private int id;
	
	@OneToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "buyer_id"))
	private Buyer buyer;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cart")
	private List<Fruit> fruits;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public List<Fruit> getFruits() {
		return fruits;
	}

	public void setFruits(List<Fruit> fruits) {
		this.fruits = fruits;
	}

	public Cart(Buyer buyer) {
		super();
		this.buyer = buyer;
	}

	public Cart() {
		super();
	}

	
	
}
