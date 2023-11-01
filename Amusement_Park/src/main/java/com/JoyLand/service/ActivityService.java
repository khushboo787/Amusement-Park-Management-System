package com.JoyLand.service;

import java.util.List;

import com.JoyLand.model.Activity;

public interface ActivityService {

	public Activity insertActivity(Activity activity);
	public Activity updateActivity(Activity activity);
	public Activity deleteActivity(int activityid);
	public List<Activity> viewActivitiesOfCharges(float charges);
	public int countActivitiesOfCharges(float charges);
}
