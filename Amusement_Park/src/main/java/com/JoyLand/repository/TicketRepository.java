package com.JoyLand.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JoyLand.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{

}
