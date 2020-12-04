package com.mohanalavala.FruitMarketplaceApplication.controller;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.mohanalavala.FruitMarketplaceApplication.model.Buyer;
import com.mohanalavala.FruitMarketplaceApplication.model.BuyerDTO;
import com.mohanalavala.FruitMarketplaceApplication.model.Cart;
import com.mohanalavala.FruitMarketplaceApplication.model.Fruit;
import com.mohanalavala.FruitMarketplaceApplication.model.Order;
import com.mohanalavala.FruitMarketplaceApplication.service.BuyerService;
import com.mohanalavala.FruitMarketplaceApplication.service.CartService;
import com.mohanalavala.FruitMarketplaceApplication.service.FruitService;
import com.mohanalavala.FruitMarketplaceApplication.service.OrderService;

@Controller
@RequestMapping("/buyer")
public class BuyerController {

	@Autowired
	private BuyerService buyerService;
	
	@Autowired
	private FruitService fruitService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderService orderService;
	
	@ModelAttribute("buyer")
	public Buyer buyerRegister() {
		return new Buyer();
	}
	@ModelAttribute("cart")
	public Cart getCart() {
		return new Cart();
	}
	@ModelAttribute("fruit")
	public Fruit getFruit() {
		return new Fruit();
	}
//	show create buyer form
	@GetMapping("/registerBuyerForm")
	public String showAddBuyerForm(Model model) {
		Buyer buyer = new Buyer();
		model.addAttribute("buyer", buyer);
		return "buyer_register";
	}
//	create a buyer 
	@PostMapping("/register")
	public String addBuyer(@ModelAttribute("buyer") Buyer buyer) {
		buyerService.createBuyer(buyer);
		return "redirect:/buyer/registerBuyerForm?success";
	}
//	show login page
	@GetMapping("/loginPage")
	public String buyerLogInForm() {
		return "buyer_login";
	}
	@GetMapping("/buyerPage")
	public String buyerPage(@AuthenticationPrincipal() BuyerDTO buyerDTO, Model model) {
		String email = buyerDTO.getUsername();
		Buyer buyer = buyerService.getBuyerByEmail(email).get();
		
		Cart cart = buyer.getCart();
		
		System.out.println(buyer.toString());
		Iterable<Fruit> fruits = fruitService.getAllFruits();
		System.out.println(fruits);
		model.addAttribute("allfruits", fruits);
		model.addAttribute("buyer", buyer);
		model.addAttribute("cart", cart);
		return "buyer_page";
	}
//	show update buyer form
	@GetMapping("/show_update_buyer_form/{id}")
	public String updateBuyerForm(@PathVariable("id") int id, Model model) {
		Optional<Buyer> buyer = buyerService.getBuyerById(id);
		model.addAttribute("buyer", buyer);
		return "update_buyer";
	}
//	update a buyer
	@PostMapping("/update")
	public void updateBuyer(@RequestBody Buyer buyer) {
		buyerService.updateBuyer(buyer);
	}
//	delete a buyer
	@GetMapping("/delete/{id}")
	public void deleteBuyer(@PathVariable("id") int id) {
		buyerService.deleteBuyer(id);
	}
//	get a buyer by id
	@GetMapping("/get/{id}")
	public Buyer getBuyerById(@PathVariable("id") int id) {
		Optional<Buyer> buyer = buyerService.getBuyerById(id);
		return buyer.get();
	}
//	get a buyer by name
	@GetMapping("/get/{name}")
	public Buyer getBuyerByName(@PathVariable("name") String name) {
		return buyerService.getBuyerByName(name);
	}
//	get all buyers
	@GetMapping("/get/all")
	public Iterable<Buyer> getAllBuyers() {
		return buyerService.getAllBuyers();
	}
	
//	carts
//	add to cart confirm
	@GetMapping("/cart/confirmAdd")
	public String showConfrimAdd(@RequestParam("buyer_id") int buyer_id, @RequestParam("fruit_id") int fruit_id, Model model) {
		Fruit fruit = fruitService.getFruitById(fruit_id).get();
//		fruit.setPurchasequantity(quantity);
		fruitService.updateFruit(fruit);
		model.addAttribute("fruit", fruit);
		model.addAttribute("buyer", buyerService.getBuyerById(buyer_id).get());
		return "add_to_cart";
	}
//	cart page
	@GetMapping("/cart/cartPage/{cart_id}")
	public String showCartPage(@PathVariable("cart_id") int cart_id, Model model ) {
		model.addAttribute("cartfruits", cartService.getFruitsInCart(cart_id));
		return "cart_page";
	}
//	add fruit to cart
	@PostMapping("/cart/add")
	public String addFruitToCart(
			@RequestParam("buyer_id") int buyer_id , 
			@RequestParam("fruit_id") int fruit_id, 
			@ModelAttribute("fruit") Fruit fruit_req,
			Model model) {
		
		Buyer buyer = buyerService.getBuyerById(buyer_id).get();
		
		Fruit fruit = fruitService.getFruitById(fruit_id).get();
		
		Cart cart = buyer.getCart();
		
		fruit.setCart(cart);
		fruit.setPurchasequantity(fruit_req.getPurchasequantity() );
		cart.getFruits().add(fruit);
		
		fruitService.updateFruit(fruit);
		buyerService.updateBuyer(buyer);
		cartService.updateCart(cart);
		
		double totalprice = 0;
		
		model.addAttribute("cartfruits", cart.getFruits());
		model.addAttribute("cart", cart);
		model.addAttribute("buyer", buyer);
		
		model.addAttribute("totalprice", totalprice);
		return "cart_page";
	}
	
	@GetMapping("/cart/delete/fruit/{fruit_id}")
	public String removeFruitFromCart( @PathVariable("fruit_id") int fruit_id) {
		cartService.removeFruitFromCart(fruitService.getFruitById(fruit_id).get());
		return "redirect:/buyer/cart/cartPage";
	}
	
//	checkout
	@GetMapping("/cart/checkout")
	public String checkout(@RequestParam("buyer_id") int buyer_id, Model model) {
		Buyer buyer = buyerService.getBuyerById(buyer_id).get();
		
		Cart cart = buyer.getCart();

		List<Fruit> fruits = cart.getFruits();
		
		List<Order> orders = new ArrayList<Order>();
		for (Fruit fruit : fruits) {
			
			Order order = new Order();
			
			order.setSeller(fruit.getSeller());
			order.setFruits(Arrays.asList(fruit));
			order.setDelivered(false);
			order.setTotalPrice(fruit.getPurchasequantity() * fruit.getPrice());
			
			fruit.setQuantity(fruit.getQuantity() - fruit.getPurchasequantity());
			fruit.setOrder(order);
			orderService.createOrder(order);
			
			fruitService.updateFruit(fruit);
			
			orders.add(order);
		}

		model.addAttribute("orders", orders);
		cart.getFruits().clear();
		cartService.updateCart(cart);
		return "redirect:/buyer/buyerPage";
	}
}
