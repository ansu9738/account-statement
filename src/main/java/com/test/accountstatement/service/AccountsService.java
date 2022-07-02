package com.test.accountstatement.service;

import java.util.List;

import com.test.accountstatement.exception.AccountStatementException;
import com.test.accountstatement.models.AccountReqDto;
import com.test.accountstatement.models.AccountResponseDto;

public interface AccountsService {
	
	List<AccountResponseDto> getAccountsByAccntType(String accountType) throws AccountStatementException;
	
	List<AccountResponseDto> getAccounts() throws AccountStatementException;
	
	void addAccount(AccountReqDto accountReqDto) throws AccountStatementException;
	
	List<AccountResponseDto> getAccountsByAccountNumber(Long accountNumber) throws AccountStatementException;

}
