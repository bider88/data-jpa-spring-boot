package com.springboot.springboot.app.models.dao;

import java.util.List;

import com.springboot.springboot.app.models.entity.Client;

public interface IClientDAO {
	
	public List<Client> findAll();
}
