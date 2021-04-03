package com.ticketResolvingSystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ticketResolvingSystem.entity.TicketResponse;

@Repository
public interface TicketResponseRepository extends CrudRepository<TicketResponse, Long> {

	TicketResponse findByTicketid(long id);
}
