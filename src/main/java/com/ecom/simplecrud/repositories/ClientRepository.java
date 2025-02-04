package com.ecom.simplecrud.repositories;

import com.ecom.simplecrud.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer>{
	
	public Client findByEmail(String email);

}
