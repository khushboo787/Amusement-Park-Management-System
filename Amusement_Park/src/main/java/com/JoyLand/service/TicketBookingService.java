package com.JoyLand.service;

import java.util.List;

import com.JoyLand.model.Ticket;
import com.JoyLand.model.TripBooking;

public interface TicketBookingService {
	
	public Ticket insertTicket(Ticket ticket, Integer activity_id);

	public Ticket updateTicket(Integer ticket_id,Integer activity_id);

	public Ticket deleteTicket(Integer ticketId);

	public List<Ticket> viewAllTickets(Integer customerId);

	public TripBooking calculateBill(Integer customerId);
}
