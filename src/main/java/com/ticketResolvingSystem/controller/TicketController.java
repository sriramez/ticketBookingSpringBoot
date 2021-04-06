package com.ticketResolvingSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ticketResolvingSystem.entity.Ticket;
import com.ticketResolvingSystem.entity.TicketResponse;
import com.ticketResolvingSystem.services.TicketService;

@RestController
public class TicketController {

	
	@Autowired
	TicketService service;
	
	@PostMapping("/ticket")
	public Ticket createTicket(@RequestBody Ticket ticket)
	{
		return service.createTicket(ticket);
	}
	
	
	@GetMapping("/tickets")
	public Iterable<Ticket> getAllTickets()
	{
		return service.getAllTickets();
	}
	
	@GetMapping("/ticket/agent/{agentname}")
	public Iterable<Ticket> getTicketByAgent(@PathVariable("agentname") String agentName)
	{
		return service.findByAgentName(agentName);
	}
	
	@GetMapping("/ticket/customer/{customername}")
	public Iterable<Ticket> getTicketByCustomer(@PathVariable("customername") String customerName)
	{
		return service.findByCustomerName(customerName);
	}
	
	@GetMapping("/tickets/status/{status}")
	public Iterable<Ticket> getTicketStatus(@PathVariable("status") String status)
	{
		return service.findByStatus(status);
	}
	
	@GetMapping("/ticket/{ticketid}")
	public Ticket getTicketById(@PathVariable("ticketid") long ticketId)
	{
		return service.findById(ticketId);
	}
	
	
	@PutMapping("/update/ticket")
	public Ticket ticket(@RequestBody Ticket ticket)
	{
		return service.updateTicket(ticket);
	}
	
	@PutMapping("/update/status")
	public Ticket ticketStatus(@RequestParam String status,@RequestParam long id)
	{
		return service.updateStatus(id, status);
	}
	
	@PostMapping("/ticket/response")
	public TicketResponse ticketResponse(@RequestParam long ticketid,@RequestParam long agentid,@RequestParam String response)
	{
		TicketResponse ticketResponse = new TicketResponse(ticketid, agentid, response);
		return service.addResponse(ticketResponse);
	}
	
	@DeleteMapping("/ticket")
	public String deleteTicket(@RequestParam long ticketid)
	{
		return service.deleteTickets(ticketid);
	}
	
}
