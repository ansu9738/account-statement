package com.test.accountstatement.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatementResponseDto {
  
	private Long id;
	
	private Long account_id;
	
	private String datefield;
	
	private String amount;
	
	private String account_number;
}
