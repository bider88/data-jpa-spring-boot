package com.springboot.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.springboot.app.models.entity.Client;
import com.springboot.springboot.app.models.service.ClientService;
import com.springboot.springboot.app.util.paginator.PageRender;

@Controller
@SessionAttributes("client")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(@RequestParam(name="page", defaultValue = "0") int page, Model model) {
		
		// New versions > 2.0 of spring is: Pageable pageRequest = PageRequest.of(page, 4);
		Pageable pageRequest = new PageRequest(page, 4);
		
		Page<Client> clients = clientService.findAll(pageRequest);
		
		PageRender<Client> pageRender = new PageRender<>("/list", clients);
		model.addAttribute("title", "List of clients");
		model.addAttribute("clients", clients);
		model.addAttribute("page", pageRender);
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
	public String save(@Valid Client client, BindingResult result, Model  model, RedirectAttributes flash, SessionStatus status) {
		model.addAttribute("title", "Formulario");
		if (result.hasErrors()) {
			return "form";
		}
		String messageFlash = (client.getId() != null ) ? "Successfully client updated" : "Successfully client added";
		
		clientService.save(client);
		
		status.setComplete();
		flash.addFlashAttribute("success", messageFlash);
		return "redirect:list";
	}
	
	@RequestMapping(value="/form/{id}")
	public String edit(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Client client = null;
		
		if (id > 0) {
			client = clientService.findOne(id);
			if (client == null) {
				flash.addFlashAttribute("error", "Client ID " + id + " does not exist in DB");
				return "redirect:/list";
			}
		} else {
			flash.addFlashAttribute("error", "Client ID does can not be zero");
			return "redirect:/list";
		}
		model.put("client", client);
		model.put("title", "Editar");
		
		return "form";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			clientService.delete(id);
			flash.addFlashAttribute("success", "Successfully client deleted");
		}
		return "redirect:/list";
	}
}
