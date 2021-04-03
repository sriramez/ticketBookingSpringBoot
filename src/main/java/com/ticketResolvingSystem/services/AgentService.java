package com.ticketResolvingSystem.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketResolvingSystem.entity.Agent;
import com.ticketResolvingSystem.entity.Ticket;
import com.ticketResolvingSystem.repository.AgentRepository;
import com.ticketResolvingSystem.repository.TicketRepository;

@Service
public class AgentService {

	public static long lastAssignedTo = 1;
	
	@Autowired
	AgentRepository repo;
	
	@Autowired
	TicketRepository ticketRepo;
	
	public Agent createAgent(Agent agent)
	{
		return repo.save(agent);
	}
	
	public void addTicketToAgent(long id)
	{
		int agentCount =0;
		Iterable<Agent> agents = repo.findAll();
		for(Agent agent : agents)
		{
			agentCount=agentCount+1;
		}
		if(lastAssignedTo>agentCount)
		{
			lastAssignedTo=1;
		}
		Optional<Agent> agent = repo.findById(lastAssignedTo);
		Agent agentEntity = agent.get();
		int ticketCount = agentEntity.getTicketCount();
		ticketCount = ticketCount+1;
		agentEntity.setTicketCount(ticketCount);
		Optional<Ticket> ticket = ticketRepo.findById(id);
		Ticket ticketEntiy = ticket.get();
		agentEntity.getTickets().add(ticketEntiy);
		repo.save(agentEntity);
		ticketEntiy.setAssignedTo(agentEntity.getAgentName());
		ticketRepo.save(ticketEntiy);
		lastAssignedTo=lastAssignedTo+1;
	}
	
	public Iterable<Agent> findAllAgents()
	{
		return repo.findAll();
	}
}
