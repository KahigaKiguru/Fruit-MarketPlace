package com.mohanalavala.FruitMarketplaceApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mohanalavala.FruitMarketplaceApplication.model.Buyer;
import com.mohanalavala.FruitMarketplaceApplication.model.Order;
import com.mohanalavala.FruitMarketplaceApplication.model.Seller;
import com.mohanalavala.FruitMarketplaceApplication.service.BuyerService;
import com.mohanalavala.FruitMarketplaceApplication.service.OrderService;
import com.mohanalavala.FruitMarketplaceApplication.service.SellerService;

@Controller
@RequestMapping("/order")
public class OrderController {

	private OrderService orderService;
	
	private SellerService sellerService;
	
	private BuyerService buyerService;
	
	
//	create order
	@PostMapping("/create")
	public void createOrder(@RequestBody Order order) {
		orderService.createOrder(order);
	}
//	update order
	@PostMapping("/update")
	public void updateOrder(@RequestBody Order order) {
		orderService.updateOrder(order);
	}
//	delete order
	@PostMapping("/delete/{order_id}")
	public String deleteOrder(@PathVariable("order_id") int order_id) {
		Order order = orderService.getOrderById(order_id).get();
		
		orderService.deleteOrder(order);
		
		return "orders_page";
	}
	
//	get orders by seller id
	@GetMapping("/seller/orders/{seller_id}")
	public Iterable<Order> getOrdersBySeller(@PathVariable("seller_id") int seller_id){
		Seller seller = sellerService.getSellerById(seller_id).get();
		
		return seller.getOrders();
	}
////	get orders by buyer id
//	@GetMapping("/buyer/orders/{buyer_id}")
//	public Iterable<Order> getOrdersByBuyer(@PathVariable("buyer_id") int buyer_id){
//		Buyer buyer = buyerService.getBuyerById(buyer_id).get();
//		
//		return buyer.getOrders();
//	}
//	
}
