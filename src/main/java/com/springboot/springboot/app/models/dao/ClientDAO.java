package com.springboot.springboot.app.models.dao;

import java.util.List;

import com.springboot.springboot.app.models.entity.Client;

public interface ClientDAO {
	
	public List<Client> findAll();
	
	public void save(Client client);
	
	public Client findOne(Long id);
	
	public void delete(Long id);
}
