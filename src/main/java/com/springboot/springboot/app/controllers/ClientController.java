package com.springboot.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.springboot.springboot.app.models.dao.ClientDAO;
import com.springboot.springboot.app.models.entity.Client;

@Controller
@SessionAttributes("client")
public class ClientController {
	
	@Autowired
	private ClientDAO clientDAO;
	
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
	public String save(@Valid Client client, BindingResult result, Model  model, SessionStatus status) {
		model.addAttribute("title", "Formulario");
		if (result.hasErrors()) {
			return "form";
		}
		clientDAO.save(client);
		
		status.setComplete();
		
		return "redirect:list";
	}
	
	@RequestMapping(value="/form/{id}")
	public String edit(@PathVariable(value="id") Long id, Map<String, Object> model) {
		
		Client client = null;
		
		if (id > 0) {
			client = clientDAO.findOne(id);
		} else {
			return "redirect:list";
		}
		model.put("client", client);
		model.put("title", "Editar");
		
		return "form";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Long id) {
		if (id > 0) {
			clientDAO.delete(id);
		}
		return "redirect:/list";
	}
}
