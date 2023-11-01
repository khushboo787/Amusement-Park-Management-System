package com.JoyLand.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.JoyLand.model.Activity;
import com.JoyLand.model.Admin;

@Service
public class AdminServiceImpl implements AdminService {

	@Override
	public Admin insertAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin deleteAdmin(Integer adminId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Activity> getAllActivitiesByCustomerID(Integer cutomerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Activity> getAllActivities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Activity> getActivitiesCustomerwise(LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Activity> getActivitiesDatewise(LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Activity> getActivitiesForDays(Integer customerId, LocalDate fromDate, LocalDate toDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
