package com.mohanalavala.FruitMarketplaceApplication.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mohanalavala.FruitMarketplaceApplication.model.Fruit;
import com.mohanalavala.FruitMarketplaceApplication.model.Order;
import com.mohanalavala.FruitMarketplaceApplication.model.Seller;
import com.mohanalavala.FruitMarketplaceApplication.model.SellerDTO;
import com.mohanalavala.FruitMarketplaceApplication.service.FruitService;
import com.mohanalavala.FruitMarketplaceApplication.service.OrderService;
import com.mohanalavala.FruitMarketplaceApplication.service.SellerService;

@Controller
@RequestMapping("/seller")
public class SellerController {

	@Autowired
	private SellerService sellerService;

	@Autowired
	private FruitService fruitService;
	
	@Autowired
	private OrderService orderService;

	@ModelAttribute("seller")
	public Seller registrationSeller() {
		return new Seller();
	}

	@ModelAttribute("sellerDTO")
	public SellerDTO getSellerDTO() {
		return new SellerDTO();
	}

	@ModelAttribute("fruit")
	public Fruit fruitsAdd() {
		return new Fruit();
	}
	@ModelAttribute("order")
	public Order sellerOrder() {
		return new Order();
	}

//	seller login page
	@GetMapping("/loginForm")
	public String sellerLogInForm() {
		return "seller_login";
	}

//	seller registration
	@PostMapping("/register")
	public String registerSeller(@ModelAttribute("seller") Seller seller) {
		sellerService.createSeller(seller);
		return "redirect:/seller/sellerRegistrationPage?success";
	}

//	create seller page
	@GetMapping("/sellerRegistrationPage")
	public String createSellerPage() {
		return "seller_register";
	}

	@GetMapping("/sellerPage")
	public String sellerPage(@AuthenticationPrincipal() SellerDTO sellerDTO, Model model) {
		String email = sellerDTO.getUsername();

		Seller seller = sellerService.getSellerByEmail(email);

		List<Fruit> fruits = seller.getFruits();

		List<Order> orders = seller.getOrders();

		model.addAttribute("sellerfruits", fruits);

		model.addAttribute("seller_orders", orders);

		model.addAttribute("seller", seller);

		return "seller_page";
	}

//	show update_seller page
	@GetMapping("/showSellerUpdatePage/{seller_id}")
	public String showSellerUpdatePage(@PathVariable("seller_id") int id, Model model) {

		Optional<Seller> seller = sellerService.getSellerById(id);
		model.addAttribute("seller", seller.get());

		return "redirect:/user/update";
	}

//	update a seller
	@PostMapping("/update")
	public String updateSeller(@RequestBody Seller request_seller) {

		Seller seller = sellerService.getSellerById(request_seller.getId()).get();

		sellerService.updateSeller(seller);

		return "seller_page";
	}

//	delete a seller
	@PostMapping("/delete")
	public String deleteSeller(int id) {
		Optional<Seller> seller = sellerService.getSellerById(id);

		sellerService.deleteSeller(seller.get());

		return "seller_page";
	}

//	get all sellers
	@GetMapping("/all")
	public String getAllSellers(Model model) {
		model.addAttribute("sellers", sellerService.getAllSellers());
		return "index";
	}

//	get orders
	@GetMapping("/orders")
	public String getAllOrders(@RequestParam("id") int seller_id, Model model) {
		Seller seller = sellerService.getSellerById(seller_id).get();
		
		model.addAttribute("seller_orders", seller.getOrders());
		return "order_page";
	}

//	get fruits
	@GetMapping("/fruits/get")
	public String getAllFruits(Model model) {
//		model.addAttribute("sellerfruits", seller.getFruits);
		return "redirect:/seller/sellerPage";
	}

//	fruit add page
	@GetMapping("/fruit/add/{id}")
	public String addFruitForm(@PathVariable("id") int id, Model model) {
		if (sellerService.getSellerById(id).get() != null) {
			model.addAttribute("seller_id", id);
		}
		return "fruit_add";
	}

//	create fruit
	@PostMapping("/fruits/add/{seller_id}")
	public String addFruit(@PathVariable("seller_id") int id,
			@ModelAttribute("fruit") Fruit fruit_req, Model model) throws IOException {

		Seller seller = sellerService.getSellerById(id).get();

		Fruit fruit = new Fruit();

//		fruit.setImage(Base64.getEncoder().encodeToString(photo.getBytes()));
		fruit.setName(fruit_req.getName());
		fruit.setDescription(fruit_req.getDescription());
		fruit.setOrder(null);
		fruit.setSeller(seller);
		fruit.setPrice(fruit_req.getPrice());
		fruit.setQuantity(fruit_req.getQuantity());
		fruit.setPurchasequantity(0);
		
		List<Fruit> fruits = seller.getFruits();

		fruits.add(fruit);

//		System.out.println(seller.getFruits());
		fruitService.createFruit(fruit);

		sellerService.updateSeller(seller);

		model.addAttribute("sellerfruits", seller.getFruits());
		return "redirect:/seller/fruits/get";
	}

	@GetMapping("/fruits/update/fruit/{fruit_id}")
	public String updateFruitForm(@PathVariable("fruit_id") int id, Model model) {
		if (fruitService.getFruitById(id).get() != null) {
			model.addAttribute("fruit_id", id);
			model.addAttribute("fruit", fruitService.getFruitById(id).get());
			model.addAttribute("sellerfruits", fruitService.getAllFruits());
		}
		return "fruit_update";
	}

	@PostMapping("/fruits/update/fruit/{fruit_id}")
	public String updateForm(@PathVariable("fruit_id") int id, @ModelAttribute("fruit") Fruit fruit_req) throws IOException {
		if (fruitService.getFruitById(id).get() != null) {
			Fruit fruit = fruitService.getFruitById(id).get();
//			fruit.setImage(Base64.getEncoder().encodeToString(photo.getBytes()));
			fruit.setName(fruit_req.getName());
			fruit.setDescription(fruit_req.getDescription());
			fruit.setOrder(null);
			fruit.setPrice(fruit_req.getPrice());
			fruit.setQuantity(fruit_req.getQuantity());
			fruitService.updateFruit(fruit);
		}

		return "redirect:/seller/sellerPage";
	}

	@GetMapping("/fruits/delete/fruit/{fruit_id}")
	public String deleteFruit(@PathVariable("fruit_id") int id) {
		if (fruitService.getFruitById(id).get() != null) {
			fruitService.deleteFruit(fruitService.getFruitById(id).get());
		}

		return "redirect:/seller/sellerPage";
	}
	
	@GetMapping("/order/complete")
	public String completeOrder(@RequestParam("id") int order_id) {
		Order order = orderService.getOrderById(order_id).get();
		
		order.setDelivered(true);
		Seller seller = order.getSeller();
		seller.setTotalRevenue(seller.getTotalRevenue() + order.getTotalPrice());
		orderService.updateOrder(order);
		
		return "redirect:/seller/orders?id=" + order_id;
	}
	@GetMapping("/order/delete")
	public String deleteOrder(@RequestParam("id") int order_id, Model model) {
		Order order = orderService.getOrderById(order_id).get();
		
		order.setDelivered(false);
		Seller seller = order.getSeller();
		seller.setTotalRevenue(seller.getTotalRevenue() + order.getTotalPrice());
		orderService.deleteOrder(order);
		sellerService.updateSeller(seller);
		
		model.addAttribute("seller", seller);
		return "redirect:/seller/orders?id=" + order_id;
	}

}
