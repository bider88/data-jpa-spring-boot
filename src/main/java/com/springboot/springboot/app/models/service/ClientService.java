package com.springboot.springboot.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.springboot.app.models.entity.Client;

public interface ClientService {
	public List<Client> findAll();
	
	public Page<Client> findAll(Pageable pageable);
	
	public void save(Client client);
	
	public Client findOne(Long id);
	
	public void delete(Long id);
}
