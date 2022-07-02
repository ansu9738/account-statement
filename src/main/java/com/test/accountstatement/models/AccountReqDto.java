package com.test.accountstatement.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountReqDto {
	
	private Long id;

	private String accountType;

	private Long accountNumber;

}
