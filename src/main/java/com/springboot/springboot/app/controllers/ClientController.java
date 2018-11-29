package com.springboot.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.springboot.app.models.dao.IClientDAO;
import com.springboot.springboot.app.models.entity.Client;

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
	
	@RequestMapping(value="/form")
	public String create(Map<String, Object> model) {
		Client client = new Client();
		model.put("client", client);
		model.put("title", "Formulario");
		return "form";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String save(@Valid Client client, BindingResult result, Model  model) {
		model.addAttribute("title", "Formulario");
		if (result.hasErrors()) {
			return "form";
		}
		clientDAO.save(client);
		return "redirect:list";
	}
}
