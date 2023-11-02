package com.JoyLand.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TripBooking {
	private Customer customer;

	private List<Ticket> tickets;

	private Integer totalAmount;

}
