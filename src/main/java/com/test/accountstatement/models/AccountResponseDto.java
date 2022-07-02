package com.test.accountstatement.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountResponseDto {

	private Long id;

	private String account_type;

	private Long account_number;
}
