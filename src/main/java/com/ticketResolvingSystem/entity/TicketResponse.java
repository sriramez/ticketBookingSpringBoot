package com.ticketResolvingSystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TicketResponse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	long ticketid;
	
	long agentid;
	
	String response;
	
	public TicketResponse(long ticketid,long agentid,String response) {

		this.ticketid = ticketid;
		this.agentid = agentid;
		this.response = response;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTicketid() {
		return ticketid;
	}

	public void setTicketid(long ticketid) {
		this.ticketid = ticketid;
	}

	public long getAgentid() {
		return agentid;
	}

	public void setAgentid(long agentid) {
		this.agentid = agentid;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
	
}
