package com.mohanalavala.FruitMarketplaceApplication.model;

import javax.persistence.*;

@Entity
@Table(name = "fruits")
public class Fruit {

	@Id
	@Column(name = "fruit_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "fruit_name")
	private String name;
	
	@Column(name = "fruit_quantity")
	private int quantity;
	
	@Column(name = "fruit_description")
	private String description;

	@Column(name = "purchase_quantity")
	private int purchasequantity;
	
	@Column(name = "price")
	private double price;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "seller_id"))
	private Seller seller;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "order_id"))
	private Order order;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "cart_id"))
	private Cart cart;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	
	public int getPurchasequantity() {
		return purchasequantity;
	}

	public void setPurchasequantity(int purchasequantity) {
		this.purchasequantity = purchasequantity;
	}

	public Fruit() {
		super();
	}
	
//	
	
	
	
}
