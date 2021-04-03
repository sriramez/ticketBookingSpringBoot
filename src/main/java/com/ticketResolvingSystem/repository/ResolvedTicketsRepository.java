package com.ticketResolvingSystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ticketResolvingSystem.entity.ResolvedTickets;

@Repository
public interface ResolvedTicketsRepository extends CrudRepository<ResolvedTickets, Long>{

	List<ResolvedTickets> findByToBeClosedDate(String date);
}
