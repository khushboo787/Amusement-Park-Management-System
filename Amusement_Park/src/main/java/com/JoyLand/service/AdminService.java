package com.JoyLand.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.JoyLand.model.Activity;
import com.JoyLand.model.Admin;

public interface AdminService {
	
	   String insertAdmin(Admin admin);

	    String updateAdmin(Admin admin, int id);

	    List<Activity> getAllActivities();

	    Admin deleteAdminById(int id);

	    List<Activity> getAllActivitiesByCustomerId(int customerId);

	    Map<String, List<Activity>> getActivitiesCustomerWise();

	    List<Activity> getActivitiesDateWise();

	    List<Activity> getActivitiesForDays(int customerId, LocalDate fromDate, LocalDate toDate);
}
