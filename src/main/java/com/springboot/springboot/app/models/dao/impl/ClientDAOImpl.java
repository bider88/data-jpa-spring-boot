package com.springboot.springboot.app.models.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.springboot.app.models.dao.ClientDAO;
import com.springboot.springboot.app.models.entity.Client;

@Repository
public class ClientDAOImpl implements ClientDAO {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Client> findAll() {
		return em.createQuery("from Client").getResultList();
	}

	@Override
	@Transactional(readOnly=true)
	public Client findOne(Long id) {
		return em.find(Client.class, id);
	}
	
	@Override
	@Transactional
	public void save(Client client) {
		if (client.getId() != null && client.getId() > 0) {
			em.merge(client);
		} else {
			em.persist(client);
		}
	}

	@Override
	@Transactional
	public void delete(Long id) {
		Client client = findOne(id);
		em.remove(client);
	}

}
