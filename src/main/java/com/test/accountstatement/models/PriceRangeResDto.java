package com.test.accountstatement.models;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceRangeResDto {
	  
	private Long id;
	
	private Long account_id;
	
	private String datefield;
	
	private BigDecimal amount;
	
	private String account_number;

}
