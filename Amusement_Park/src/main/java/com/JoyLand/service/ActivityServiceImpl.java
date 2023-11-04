package com.JoyLand.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.JoyLand.exception.ActivityException;
import com.JoyLand.model.Activity;
import com.JoyLand.repository.ActivityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@Slf4j
public class ActivityServiceImpl implements ActivityService{
	
	@Autowired
	ActivityRepository activityRepository;

	public ActivityServiceImpl(ActivityRepository activityRepository) {
		this.activityRepository = activityRepository;
	}
	


	@Override
	public Activity insertActivity(Activity activity) {
		log.info("activity creation here ");
		
		return activityRepository.save(activity);
	}

	
	@Override
	public Activity updateActivity(Integer activityid, Activity activity) {
		
		 java.util.Optional<Activity> opt = activityRepository.findById(activityid);


		if (opt.isPresent()) {
			Activity act = opt.get();

			act.setDescription(activity.getDescription());
			act.setCharge(activity.getCharge());
			return activityRepository.save(act);
		} else {

			throw new ActivityException("No activity with this Id");
		}
		
		

	}

	
	
	@Override
	public String deleteActivity(int activityid) {
		 java.util.Optional<Activity> a = activityRepository.findById(activityid);

		if (a.isPresent()) {
			Activity act = a.get();
			activityRepository.delete(act);			
		} else {
			throw new ActivityException("No activity with this id" + activityid);
		}
		return "Activity deleted successfully.";
	}
	
	

	@Override
	public List<Activity> viewActivitiesOfCharges(float charges)  {

		List<Activity> act = activityRepository.findByCharge(charges);

		if (act.isEmpty()) {

			throw new ActivityException("No any activity  in our activity table");
		} else {

			return act;
		}

	}


	
	@Override
	public int countActivitiesOfCharges(float charges){
		// TODO Auto-generated method stub
		List<Activity> act = activityRepository.findByCharge(charges);

		if (act.isEmpty()) {

			throw new ActivityException("No activity List Database");
		} else {

			return act.size();
		}
	}

}
