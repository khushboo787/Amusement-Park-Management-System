package com.JoyLand.service;

import java.util.List;
import java.util.Optional;

import com.JoyLand.model.Ticket;
import com.JoyLand.model.TripBooking;

public interface TicketBookingService {
	
	public Ticket createTicket(Integer customerId, Integer activityId, Ticket ticket);
	
	public Ticket updateTicket(Integer ticket_id,Integer activity_id);

	public Ticket deleteTicket(Integer ticketId);

	List<Ticket>  viewAllTickets(Integer customerId);

	TripBooking calculateBill(Integer customerId);

	

	
}
