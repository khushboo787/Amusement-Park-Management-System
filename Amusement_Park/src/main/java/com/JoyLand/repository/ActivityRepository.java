package com.JoyLand.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JoyLand.model.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Integer>{

	List<Activity> findByCharge(float charges);

}
