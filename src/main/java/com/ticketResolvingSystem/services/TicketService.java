package com.ticketResolvingSystem.services;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.ticketResolvingSystem.entity.Agent;
import com.ticketResolvingSystem.entity.ResolvedTickets;
import com.ticketResolvingSystem.entity.Ticket;
import com.ticketResolvingSystem.entity.TicketResponse;
import com.ticketResolvingSystem.repository.AgentRepository;
import com.ticketResolvingSystem.repository.ResolvedTicketsRepository;
import com.ticketResolvingSystem.repository.TicketRepository;
import com.ticketResolvingSystem.repository.TicketResponseRepository;

import org.springframework.stereotype.Service;

@Service
public class TicketService {

	
	@Autowired
	TicketRepository repository;
	
	@Autowired
	AgentService agentService;
	
	@Autowired
	TicketResponseRepository ticketRepository;
	
	@Autowired
	AgentRepository repo;
	
	@Autowired
	SendGrid sendGrid;
	
	@Autowired
	ResolvedTicketsRepository resolvedTicketsRepo;
	
	public Ticket createTicket(Ticket ticket)
	{
		Ticket savedTicket = repository.save(ticket);
		agentService.addTicketToAgent(savedTicket.getId());
		
		return savedTicket;
	}
	
	public Iterable<Ticket> getAllTickets()
	{
		return repository.findAll();
	}
	
	public Iterable<Ticket> findByAgentName(String agentName)
	{
		return repository.findByAssignedTo(agentName);
	}
	
	public Iterable<Ticket> findByCustomerName(String customerName)
	{
		return repository.findByCustomer(customerName);
	}
	
	public Iterable<Ticket> findByStatus(String status)
	{
		return repository.findByStatus(status);
	}
	
	public Ticket findById(long id)
	{
		Optional<Ticket> findById = repository.findById(id);
		return findById.get();
	}
	
	public Ticket updateTicket(Ticket ticket)
	{
		return repository.save(ticket);
	}
	
	public Ticket updateStatus(long id,String status)
	{
		Ticket ticket = findById(id);
		ticket.setStatus(status);
		if(status.equals("Resolved"))
		{
			Calendar c= Calendar.getInstance();
			c.add(Calendar.DATE, 30);
			java.util.Date time = c.getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
			java.util.Date date = new java.util.Date();  
			String resolvedDate = formatter.format(date); 
			String toBeClosedDate = formatter.format(time);
			resolvedTicketsRepo.save(new ResolvedTickets(id, resolvedDate, toBeClosedDate));
		}
		return repository.save(ticket);
	}
	
	public TicketResponse addResponse(TicketResponse response)
	{
		TicketResponse ticketResponse = ticketRepository.save(response);
		
		Email from = new Email("yogesh@sinecycle.com");
		Email to = new Email("velayutham.sriram@gmail.com");
		Content content = new Content("text/html", response.getResponse());
		Mail mail = new Mail(from, "Ticket NO:"+response.getTicketid(), to, content);
		
		 Request request = new Request();
	     Response sendGridResponse = null;
	     
	     request.setMethod(Method.POST);
         request.setEndpoint("mail/send");
         try {
         request.setBody(mail.build());

         sendGridResponse = sendGrid.api(request);
         }
         catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	        
		return ticketRepository.save(ticketResponse);
	}
	
	public String deleteTickets(long id)
	{
		Optional<Ticket> ticket = repository.findById(id);
		String agent = ticket.get().getAssignedTo();
		Agent agentEntity = repo.findByAgentName(agent);
		int idTodelete=0;
		for(int i=0;i<agentEntity.getTickets().size();i++)
		{
			if(agentEntity.getTickets().get(i).getId()==id)
			{
				idTodelete = i;
			}
		}
		agentEntity.getTickets().remove(idTodelete);
		repo.save(agentEntity);
		repository.deleteById(id);
		return "ticket Deleted Successfully";
	}
	
	
}
