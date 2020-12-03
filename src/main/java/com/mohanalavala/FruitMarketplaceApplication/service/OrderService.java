package com.mohanalavala.FruitMarketplaceApplication.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mohanalavala.FruitMarketplaceApplication.model.Order;
import com.mohanalavala.FruitMarketplaceApplication.repository.OrderRepository;

@Service
public class OrderService {

	private OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		super();
		this.orderRepository = orderRepository;
	}
	
//	create an order
	public void createOrder(Order order) {
		orderRepository.save(order);
	}
//	update an order
	public void updateOrder(Order order) {
		orderRepository.save(order);
	}
//	delete order
	public void deleteOrder(Order order) {
		orderRepository.delete(order);
	}
//	get all orders
	public Iterable<Order> getAllOrders() {
		return orderRepository.findAll();
	}
//	get order by id
	public Optional<Order> getOrderById(int id) {
	
		return orderRepository.findById(id);
	}

}
