package com.JoyLand.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.JoyLand.model.Ticket;
import com.JoyLand.model.TripBooking;

@Service
public class TicketServiceImpl implements TicketBookingService {

	@Override
	public Ticket insertTicket(Ticket ticket, Integer activity_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ticket updateTicket(Integer ticket_id, Integer activity_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ticket deleteTicket(Integer ticketId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ticket> viewAllTickets(Integer customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TripBooking calculateBill(Integer customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
