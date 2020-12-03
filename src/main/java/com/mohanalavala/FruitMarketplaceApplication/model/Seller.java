package com.mohanalavala.FruitMarketplaceApplication.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "sellers", uniqueConstraints = @UniqueConstraint(columnNames = "email_address"))
public class Seller {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "seller_id")
	private int id;
	
	@Column(name = "seller_name")
	private String name;
	
	@Column(name = "password")
	private String password;

	@Column(name = "email_address")
	private String email;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "seller")
	private List<Order> orders;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "seller")
	private List<Fruit> fruits;
	
	@Column(name = "fruits_sold")
	private int fruits_sold;
	
	@Column(name = "total_revenue")
	private double totalRevenue;
		
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(
			name = "sellers_roles",
			joinColumns = @JoinColumn(name = "seller_id", referencedColumnName = "seller_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;
	

	public Seller() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Fruit> getFruits() {
		return fruits;
	}

	public void setFruits(List<Fruit> fruits) {
		this.fruits = fruits;
	}

	public Integer getFruits_sold() {
		return fruits_sold;
	}

	public void setFruits_sold(int fruits_sold) {
		this.fruits_sold = fruits_sold;
	}

	public double getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	

}
