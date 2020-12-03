package com.mohanalavala.FruitMarketplaceApplication.model;

import java.util.Collection;
import javax.persistence.*;

@Entity
@Table(name = "buyers", uniqueConstraints = @UniqueConstraint(columnNames = "email_address"))
public class Buyer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "buyer_id")
	private int id;
	
	@Column(name = "buyer_name")
	private String name;
	
	@Column(name = "email_address")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "buyers_roles",
			joinColumns = @JoinColumn(name = "buyer_id", referencedColumnName = "buyer_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "buyer")
	private Cart cart;
	
	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "Buyer [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", roles=" + roles
				+ ", cart=" + cart + "]";
	}

	
	
	
}
