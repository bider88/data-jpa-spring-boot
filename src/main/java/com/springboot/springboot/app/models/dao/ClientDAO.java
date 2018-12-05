package com.springboot.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.springboot.app.models.entity.Client;

public interface ClientDAO extends CrudRepository<Client, Long> {
	
}
