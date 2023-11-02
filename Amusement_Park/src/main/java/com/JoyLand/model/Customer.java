package com.JoyLand.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor  
@Getter
@Setter
@Entity
public class Customer extends AbstractUser{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL )
	private List<Ticket> tickets = new ArrayList<>();
	
	@OneToMany(mappedBy="customer", cascade = CascadeType.ALL)
	private List<Activity> activities=new ArrayList<>();
}
