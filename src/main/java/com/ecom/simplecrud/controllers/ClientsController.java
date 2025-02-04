package com.ecom.simplecrud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ecom.simplecrud.repositories.ClientRepository;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/clients")
public class ClientsController {
	
	@Autowired
	private ClientRepository clientRepo;
	
	@GetMapping({"", "/"})
	public String getClients(Model model) {
		var clients = clientRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
		model.addAttribute("clients", clients);
		
		return "clients/index";
	}

}
