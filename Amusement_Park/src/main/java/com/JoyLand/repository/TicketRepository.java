package com.JoyLand.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.JoyLand.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer>
{
    @Query("SELECT t FROM Ticket t WHERE t.customer.id = :customerId AND t.dateTime BETWEEN :fromdate AND :todate")
    List<Ticket> findTicketsByCustomerIdAndDateRange(@Param("customerId") int customerId, @Param("fromdate") LocalDate fromdate, @Param("todate") LocalDate todate);

	List<Ticket> findAllByOrderByDateTimeAsc();
	
	@Query("select c.tickets from Customer c where c.customerId = ?1")
	public List<Ticket> getAllTicketsByCustomerId(Integer customer_id);
}
