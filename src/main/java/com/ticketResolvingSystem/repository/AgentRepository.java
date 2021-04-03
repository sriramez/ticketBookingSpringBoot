package com.ticketResolvingSystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ticketResolvingSystem.entity.Agent;

@Repository
public interface AgentRepository extends CrudRepository<Agent, Long>{

	Agent findByAgentName(String agentname);
	
}
