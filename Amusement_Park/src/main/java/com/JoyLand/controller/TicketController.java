package com.JoyLand.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.JoyLand.exception.ActivityException;
import com.JoyLand.exception.CustomerException;
import com.JoyLand.model.Ticket;
import com.JoyLand.service.TicketBookingService;

import jakarta.validation.Valid;

@RestController
public class TicketController {
	@Autowired
	TicketBookingService ticketBookingService;
	
	@PostMapping("/tickets/{customerId}/{activityId}")
	public ResponseEntity<Ticket> createTicketHandler(@PathVariable Integer customerId, @PathVariable Integer activityId,@Valid @RequestBody Ticket ticket) throws ActivityException, CustomerException {
		
		Ticket tick = ticketBookingService.createTicket(customerId, activityId, ticket);
		
		return new ResponseEntity<>(tick, HttpStatus.CREATED);
	}
}
