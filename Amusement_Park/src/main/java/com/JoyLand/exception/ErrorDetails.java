package com.JoyLand.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorDetails {
	
	private LocalDateTime timestamp;
	private String message;
	private String uri;
}