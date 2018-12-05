package com.springboot.springboot.app.models.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.springboot.springboot.app.models.dao.ClientDAO;
import com.springboot.springboot.app.models.entity.Client;

@Repository
public class ClientDAOImpl implements ClientDAO {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Client> findAll() {
		return em.createQuery("from Client").getResultList();
	}

	@Override
	public Client findOne(Long id) {
		return em.find(Client.class, id);
	}
	
	@Override
	public void save(Client client) {
		if (client.getId() != null && client.getId() > 0) {
			em.merge(client);
		} else {
			em.persist(client);
		}
	}

	@Override
	public void delete(Long id) {
		Client client = findOne(id);
		em.remove(client);
	}

}
