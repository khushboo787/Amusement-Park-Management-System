package com.JoyLand.controller;

import java.util.List;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.JoyLand.exception.ActivityException;
import com.JoyLand.exception.CustomerException;
import com.JoyLand.model.Activity;
import com.JoyLand.model.Customer;
import com.JoyLand.model.Ticket;
import com.JoyLand.model.TripBooking;
import com.JoyLand.service.CustomerService;
import com.JoyLand.service.TicketBookingService;

import jakarta.validation.Valid;

@RestController
public class TicketController {
	@Autowired
	TicketBookingService ticketBookingService;
	@Autowired
	CustomerService customerService;
	
	@PostMapping("/tickets/{customerId}/{activityId}")
	public ResponseEntity<Ticket> createTicketHandler(@PathVariable Integer customerId, @PathVariable Integer activityId,@Valid @RequestBody Ticket ticket) throws ActivityException, CustomerException {
		
		Ticket tick = ticketBookingService.createTicket(customerId, activityId, ticket);
		
		return new ResponseEntity<>(tick, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteTicket/{ticketid}")
	public ResponseEntity<Ticket> deleteTicket(@PathVariable Integer ticketid){

		Ticket ticket = ticketBookingService.deleteTicket(ticketid);

		return new ResponseEntity<>(ticket, HttpStatus.OK);
	}

	@GetMapping("/getAllTickets/{customerId}")
	public ResponseEntity<List<Ticket>> getTickets(@PathVariable Integer customerId){

		
		return new ResponseEntity<>( ticketBookingService.viewAllTickets(customerId), HttpStatus.OK);
	}
	

	@GetMapping("/calculateBill/{customerId}")
	public ResponseEntity<TripBooking> getBillHandler(@PathVariable Integer customerId)throws LoginException {

		
		TripBooking trip = ticketBookingService.calculateBill(customerId);

		return new ResponseEntity<>(trip, HttpStatus.OK);

	}

}
