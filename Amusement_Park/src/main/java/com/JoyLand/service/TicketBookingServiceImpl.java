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
import com.JoyLand.exception.TicketException;
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
		Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);

		if (optionalTicket.isPresent()) {

			ticketRepository.delete(optionalTicket.get());
			
			return optionalTicket.get();

		} else {

			throw new TicketException("Please Enter Correct ticket Id :");
		}
	}

	@Override
	public List<Ticket> viewAllTickets(Integer customerId) {
		Customer cus = customerRepository.findById(customerId).get();
		if(cus!=null) {
			  List<Ticket> tcList = cus.getTickets();
			   return tcList;
		}
	   throw new  CustomerException("Invalid customer id");
	}

	@Override
	public TripBooking calculateBill(Integer customerId) {
		
		int totalAmount = 0;
		
		Customer cus = customerRepository.findById(customerId).get();
		TripBooking trip = new TripBooking();
		trip.setCustomer(cus);
		if(cus!=null) {
			  List<Ticket> tcList = cus.getTickets();
			  trip.setTickets(tcList);
			  for (Ticket t : tcList) {
					totalAmount += t.getActivity().getCharge();
				}

				trip.setTotalAmount(totalAmount);

		}
		
		return trip;
	}

	

}
