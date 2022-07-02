package com.test.accountstatement.models;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatementReqDto {
 
	private Long accountNumber;
	
	private String dateField;
	
	private BigDecimal amount;
}
