package com.ecom.simplecrud.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ecom.simplecrud.models.Client;
import com.ecom.simplecrud.models.ClientDto;
import com.ecom.simplecrud.repositories.ClientRepository;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.*;

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
	
	@GetMapping("create")
	public String createClient(Model model) {
		ClientDto clientDto = new ClientDto();
		model.addAttribute("clientDto", clientDto);
		
		return "clients/create";
	}
	
	@PostMapping("/create")
	public String createClient(
			@Valid @ModelAttribute ClientDto clientDto, 
			BindingResult result)
	{
		if(clientRepo.findByEmail(clientDto.getEmail()) != null) {
			result.addError(new FieldError("clientDto", "email", clientDto.getEmail(), false, null, null, "Email address is already used"));
		}
		
		if(result.hasErrors()) {
			return "clients/create";	
		}
		
		Client client  = new Client();
		client.setFirstName(clientDto.getFirstName());
		client.setLastName(clientDto.getLastName());
		client.setEmail(clientDto.getEmail());
		client.setPhone(clientDto.getPhone());
		client.setAddress(clientDto.getAddress());
		client.setStatus(clientDto.getStatus());
		client.setCreatedAt(new Date());
		
		clientRepo.save(client);
		
		return "redirect:/clients";	
		
	}

}
