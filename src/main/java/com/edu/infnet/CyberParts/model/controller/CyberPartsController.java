package com.edu.infnet.CyberParts.model.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CyberPartsController {

	@GetMapping("/")
	public String init() {
		return "Bem vindo a CyberParts";
	}
}
