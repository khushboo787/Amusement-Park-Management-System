package com.JoyLand.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JoyLand.model.Ticket;
import com.JoyLand.model.TripBooking;
import com.JoyLand.repository.ActivityRepository;
import com.JoyLand.repository.CustomerRepository;
import com.JoyLand.repository.TicketRepository;
import com.JoyLand.exception.ActivityException;
import com.JoyLand.exception.CustomerException;
import com.JoyLand.model.Activity;
import com.JoyLand.model.Customer;

@Service
public class TicketBookingServiceImpl implements TicketBookingService {
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	ActivityRepository activityRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	
	
	
	
	@Override
	public Ticket createTicket(Integer customerId, Integer activityId, Ticket ticket){
		Optional<Activity> act = activityRepository.findById(activityId);
		Optional<Customer> cus = customerRepository.findById(customerId);
		
		if (act.isPresent()) {

			ticket.setCustomer(cus.get());
			ticket.setActivity(act.get());

			Ticket tick = ticketRepository.save(ticket);

			return tick;

		} else {

			throw new ActivityException("Please Enter Correct Activity ID : ");

		}
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
