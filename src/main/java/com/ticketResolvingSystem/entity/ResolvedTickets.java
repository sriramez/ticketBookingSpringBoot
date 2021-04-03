package com.ticketResolvingSystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ResolvedTickets {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	long ticketid;
	
	String resolvedDate;
	
	String toBeClosedDate;

	public ResolvedTickets(long ticketid, String resolvedDate, String toBeClosedDate) {
		super();
		this.ticketid = ticketid;
		this.resolvedDate = resolvedDate;
		this.toBeClosedDate = toBeClosedDate;
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

	public String getResolvedDate() {
		return resolvedDate;
	}

	public void setResolvedDate(String resolvedDate) {
		this.resolvedDate = resolvedDate;
	}

	public String getToBeClosedDate() {
		return toBeClosedDate;
	}

	public void setToBeClosedDate(String toBeClosedDate) {
		this.toBeClosedDate = toBeClosedDate;
	}
	
	
}
