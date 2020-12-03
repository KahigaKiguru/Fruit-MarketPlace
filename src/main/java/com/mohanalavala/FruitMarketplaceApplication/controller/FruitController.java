package com.mohanalavala.FruitMarketplaceApplication.controller;

import java.io.IOException;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mohanalavala.FruitMarketplaceApplication.model.Fruit;
import com.mohanalavala.FruitMarketplaceApplication.service.FruitService;
import com.mohanalavala.FruitMarketplaceApplication.service.SellerService;

@Controller
@RequestMapping("/fruit")
public class FruitController {

	@Autowired
	private FruitService fruitService;
	
	@Autowired
	private SellerService sellerService;

//	show create fruits page
	@GetMapping("/createFruitPage")
	public String createFruitPage(Model model) {
		Fruit fruit = new Fruit();
		model.addAttribute("fruit", fruit);
		return "fruit_create";
	}




}
