package com.springboot.springboot.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.springboot.app.models.dao.IClientDAO;

@Controller
public class ClientController {
	
	@Autowired
	private IClientDAO clientDAO;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("title", "List of clients");
		model.addAttribute("clients", clientDAO.findAll());
		return "list";
	}
}
