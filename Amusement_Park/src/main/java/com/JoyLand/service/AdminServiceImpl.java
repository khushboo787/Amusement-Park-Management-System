package com.JoyLand.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JoyLand.exception.AdminException;
import com.JoyLand.model.Activity;
import com.JoyLand.model.Admin;
import com.JoyLand.model.Customer;
import com.JoyLand.model.Ticket;
import com.JoyLand.repository.ActivityRepository;
import com.JoyLand.repository.AdminRepository;
import com.JoyLand.repository.CustomerRepository;
import com.JoyLand.repository.TicketRepository;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private ActivityRepository activityRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private TicketRepository ticketRepo;

    public String insertAdmin(Admin admin) {
        if (admin != null) {
            adminRepo.save(admin);
            return "Admin successfully inserted";
        } else {
            throw new AdminException("Admin data is not correct");
        }
    }

    public String updateAdmin(Admin admin, int id) {
        Optional<Admin> existingAdmin = adminRepo.findById(id);
        if (existingAdmin.isPresent()) {
            Admin adminData = existingAdmin.get();

            // Update the admin's fields
            adminData.setUsername(admin.getUsername());
            adminData.setPassword(admin.getPassword());
            adminData.setAddress(admin.getAddress());
            adminData.setMobileNumber(admin.getMobileNumber());
            adminData.setEmail(admin.getEmail());

            adminRepo.save(adminData);
            return "Admin successfully updated";
        } else {
            throw new AdminException("Admin not found with ID: " + id);
        }
    }

    public List<Activity> getAllActivities() {
        return activityRepo.findAll();
    }

    public Admin deleteAdminById(int id) {
        Optional<Admin> admin = adminRepo.findById(id);
        if (admin.isPresent()) {
            adminRepo.deleteById(id);
            return admin.get();
        } else {
            throw new AdminException("Admin not found with ID: " + id);
        }
    }

    public List<Activity> getAllActivitiesByCustomerId(int customerId) {
        Optional<Customer> customer = customerRepo.findById(customerId);
        if (customer.isPresent()) {
            Customer customerData = customer.get();
            return customerData.getActivities();
        } else {
            throw new AdminException("Customer not found with ID: " + customerId);
        }
    }

    public Map<String, List<Activity>> getActivitiesCustomerWise() {
        List<Ticket> tickets = ticketRepo.findAll();
        return tickets.stream()
                .collect(Collectors.groupingBy(
                        ticket -> ticket.getCustomer().getUsername(),
                        Collectors.mapping(Ticket::getActivity, Collectors.toList())
                ));
    }

    public List<Activity> getActivitiesDateWise() {
        List<Ticket> tickets = ticketRepo.findAllByOrderByDateTimeAsc();
        return tickets.stream()
                .map(Ticket::getActivity)
                .collect(Collectors.toList());
    }

    public List<Activity> getActivitiesForDays(int customerId, LocalDate fromDate, LocalDate toDate) {
        List<Ticket> tickets = ticketRepo.findTicketsByCustomerIdAndDateRange(customerId, fromDate, toDate);
        return tickets.stream()
                .map(Ticket::getActivity)
                .collect(Collectors.toList());
    }
    
}
