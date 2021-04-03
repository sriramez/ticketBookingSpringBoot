package com.ticketResolvingSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ticketResolvingSystem.entity.Agent;
import com.ticketResolvingSystem.services.AgentService;

@RestController
public class AgentController {

	@Autowired
	AgentService service;
	
	@PostMapping("/create/agent")
	public Agent createAgent(@RequestBody Agent agent)
	{
		return service.createAgent(agent);
	}
	
	@GetMapping("/find/allagents")
	public Iterable<Agent> findall()
	{
		return service.findAllAgents();
	}
}
