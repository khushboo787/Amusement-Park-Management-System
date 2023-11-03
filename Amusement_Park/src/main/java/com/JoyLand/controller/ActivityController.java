package com.JoyLand.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.JoyLand.exception.ActivityException;
import com.JoyLand.exception.CustomerException;
import com.JoyLand.model.Activity;
import com.JoyLand.service.ActivityService;

import jakarta.validation.Valid;

@RestController
public class ActivityController {
	
	@Autowired
	ActivityService activityService;
	
	
	
	public ActivityController(ActivityService activityService) {
		this.activityService = activityService;
	}

	@PostMapping("/insertActivity")
	public ResponseEntity<Activity> createTicketHandler(@Valid @RequestBody Activity act){	
		return new ResponseEntity<>(activityService.insertActivity(act), HttpStatus.CREATED);
	}

	@PutMapping("/updateActivity/{activityid}")
	public ResponseEntity<Activity> updateActivity(@PathVariable ("activityid") Integer activityid,@Valid @RequestBody Activity activity){

		Activity act = activityService.updateActivity(activityid, activity);

		return new ResponseEntity<Activity>(act, HttpStatus.ACCEPTED);

	}
	
	@DeleteMapping("/deleteActivity/{activityid}")
	public ResponseEntity<String> deleteActivity(@PathVariable ("activityid") Integer activityid){

		return new ResponseEntity<>(activityService.deleteActivity(activityid), HttpStatus.OK);

	}
}
