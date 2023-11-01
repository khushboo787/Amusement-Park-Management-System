package com.JoyLand.model;

import java.time.LocalDate;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ticketId;
	
	@ManyToOne
	@JoinColumn(name = "customerId")
	@JsonIgnore
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "activityId")
	@JsonIgnore
	private Activity activity;

	@FutureOrPresent(message = "ticketBooking date must be of today or future")
	private LocalDate dateTime;

	
}
