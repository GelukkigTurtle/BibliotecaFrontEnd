package com.uae.biblioteca.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.RestTemplate;

import com.uae.biblioteca.entidades.Autor;

@Controller
public class IndexController {
	
	
	@GetMapping("/")
	public String init() {
		return "index.html";
	}
	
	@GetMapping("/autores")
	public String autores(Model model) {
		final String endpoint = "http://localhost:8080/autor/get";
		RestTemplate restWs = new RestTemplate();
		ArrayList<Autor> autores =  new ArrayList<>();
		try {
			autores = restWs.getForObject(endpoint, ArrayList.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("listaAutores", autores);
		
		return "index.html";
	}
	
	@GetMapping("/eliminar")
	public String eliminar(@ModelAttribute int id) {
		
		final String endpoint = "http://localhost:8080/autor/eliminar?id="+id;
		RestTemplate restWs = new RestTemplate();
		try {
		restWs.getForObject(endpoint, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "index.html";
	}

}
