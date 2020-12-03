package com.mohanalavala.FruitMarketplaceApplication.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
	private int id;
	
	@Column(name = "total_price")
	private double totalPrice; 
	
	@Column(name = "delivered")
	private boolean isDelivered;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
	private List<Fruit> fruits;

	@ManyToOne
	@JoinColumn(name = "seller_id")
	private Seller seller;
	
	public Order() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public boolean isDelivered() {
		return isDelivered;
	}

	public void setDelivered(boolean isDelivered) {
		this.isDelivered = isDelivered;
	}

	public List<Fruit> getFruits() {
		return fruits;
	}

	public void setFruits(List<Fruit> fruits) {
		this.fruits = fruits;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	
	
}
