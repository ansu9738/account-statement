package com.test.accountstatement.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DateRangeResDto {

	private Long id;

	private Long account_id;

	private LocalDate datefield;

	private String amount;
	
	private String account_number;
}
