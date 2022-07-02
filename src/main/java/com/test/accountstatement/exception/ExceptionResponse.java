package com.test.accountstatement.exception;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {

	private String message;
	
	private LocalDateTime dateTime;
}
