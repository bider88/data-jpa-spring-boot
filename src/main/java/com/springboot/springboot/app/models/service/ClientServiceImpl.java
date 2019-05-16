package com.springboot.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.springboot.app.models.dao.ClientDAO;
import com.springboot.springboot.app.models.entity.Client;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientDAO clientDAO;

	@Override
	@Transactional(readOnly=true)
	public List<Client> findAll() {
		return (List<Client>) clientDAO.findAll();
	}

	@Override
	@Transactional
	public void save(Client client) {
		clientDAO.save(client);
	}

	@Override
	@Transactional(readOnly=true)
	public Client findOne(Long id) {
		return clientDAO.findOne(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clientDAO.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Client> findAll(Pageable pageable) {
		return clientDAO.findAll(pageable);
	}

}
