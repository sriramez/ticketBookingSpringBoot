package com.ticketResolvingSystem.configuration;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ticketResolvingSystem.entity.ResolvedTickets;
import com.ticketResolvingSystem.entity.Ticket;
import com.ticketResolvingSystem.repository.ResolvedTicketsRepository;
import com.ticketResolvingSystem.repository.TicketResponseRepository;
import com.ticketResolvingSystem.services.TicketService;

@Component
public class TicketCloser {

	@Autowired
	TicketResponseRepository ticketRepo;
	
	@Autowired
	ResolvedTicketsRepository resolvedTicks;
	
	@Autowired
	TicketService service;
	
	@Scheduled(cron="0 1 * * * ?")
	public void closeTasks()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		java.util.Date date = new java.util.Date();  
		String resolvedDate = formatter.format(date); 
		List<ResolvedTickets> resolvedTicketsToBeClosed = resolvedTicks.findByToBeClosedDate(resolvedDate);
		
		for(ResolvedTickets ticket : resolvedTicketsToBeClosed)
		{
			long ticketid = ticket.getTicketid();
			service.updateStatus(ticketid, "Closed");
		}
	}
}
