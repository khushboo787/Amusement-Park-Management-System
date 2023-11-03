package com.JoyLand.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.MediaSize.ISO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JoyLand.model.Activity;
import com.JoyLand.model.Admin;
import com.JoyLand.service.AdminServiceImpl;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
public class AdminController
{
	@Autowired
	AdminServiceImpl adminserv;
	
  @PostMapping("admin/admin")
  public ResponseEntity<String>insertAdmin(@Valid @RequestBody Admin admin)
  {
	  String str=adminserv.insertAdmin(admin);
	  return new ResponseEntity(str,HttpStatus.ACCEPTED);
  }
  
  @PutMapping("admin/update/{id}")
  public ResponseEntity<String>UpdateAdmin(@RequestBody Admin admin,@PathVariable int id)
  {
	  String str=adminserv.updateAdmin(admin,id);
	  return new ResponseEntity(str,HttpStatus.ACCEPTED);
  }
  
  @DeleteMapping("admin/Delete/{id}")
   public ResponseEntity<Admin>deleteAdminById(@PathVariable int id)
   {
	  Admin admin=adminserv.deleteAdminById(id);
	  return new ResponseEntity(admin,HttpStatus.OK);

   }

  @GetMapping("admin/activites/{id}")
  public ResponseEntity<List<Activity>>getAllActivityByCustomerId(@PathVariable int id)
  {
	  List<Activity> activities =adminserv.getAllActivitiesByCustomerId(id);
	  return new ResponseEntity(activities,HttpStatus.OK);
  }
  @GetMapping("admin/call")
  public String calladmin()
  {
	  return"I am from admin what is work ksnnasdjnvjmlkmv";
  }
  
  @GetMapping("admin/activites")
  public ResponseEntity<List<Activity>>getAllActivityByAdmin()
  {
	  List<Activity> activities =adminserv.getAllActivities();
	  return new ResponseEntity(activities,HttpStatus.OK);
  }
  
  @GetMapping("admin/activities/wise")
  public ResponseEntity<Map<String, List<Activity>>> getActivitesCustomerWise()
  {
	  Map<String,List<Activity>>  activities = adminserv.getActivitiesCustomerWise();
	  return new ResponseEntity(activities,HttpStatus.OK);  
  }
  
  @GetMapping("admin/Activities/dateWise")
  public ResponseEntity<List<Activity>> getActivitesDateWise()
  {
	  List<Activity> activities = adminserv.getActivitiesDateWise();
	  return new ResponseEntity(activities,HttpStatus.OK);  
  }
  
  @GetMapping("admin/activitiea/forday/{customerid}")
  public ResponseEntity<List<Activity>> getActivitesFordays(@PathVariable int customerid,
		    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromdate,
		    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate todate)
  {
	  List<Activity> activities = adminserv.getActivitiesForDays(customerid,fromdate,todate);
	  return new ResponseEntity(activities,HttpStatus.OK);  
  }
  
  
  
}
