package com.springboot.springboot.app.models.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.springboot.app.models.dao.IClientDAO;
import com.springboot.springboot.app.models.entity.Client;

@Repository
public class ClientDAOImpl implements IClientDAO {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Client> findAll() {
		return em.createQuery("from Client").getResultList();
	}

	@Override
	@Transactional
	public void save(Client client) {
		em.persist(client);
		
	}

}
