package com.springboot.springboot.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.springboot.springboot.app.models.entity.Client;

public interface ClientDAO extends PagingAndSortingRepository<Client, Long> {
	
}
