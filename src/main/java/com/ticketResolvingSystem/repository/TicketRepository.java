package com.ticketResolvingSystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ticketResolvingSystem.entity.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long>{

	List<Ticket> findByAssignedTo(String agentName);
	
	List<Ticket> findByCustomer(String customerName);
	
	List<Ticket> findByStatus(String status);
	
}
