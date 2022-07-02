package com.test.accountstatement.controller;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.test.accountstatement.exception.AccountStatementException;
import com.test.accountstatement.models.AccountReqDto;
import com.test.accountstatement.models.AccountResponseDto;
import com.test.accountstatement.service.AccountsService;

@ExtendWith(SpringExtension.class)
public class AccountControllerTest {
	
	@InjectMocks
	AccountController accountController;
	
	@Mock
	AccountsService accountsService;
	
	@Test
	public void addAccount() throws AccountStatementException {
		AccountReqDto reqDto = new AccountReqDto();
		reqDto.setAccountNumber(723238823l);
		reqDto.setAccountType("Savings");
		reqDto.setId(2l);
		Mockito.doNothing().when(accountsService).addAccount(reqDto);
		AccountController mockCntrl = Mockito.mock(AccountController.class);
		mockCntrl.addAccount(reqDto);
		Mockito.verify(mockCntrl, Mockito.times(1)).addAccount(reqDto);
	}
	
	@Test
	public void fetchAccountsByAccountTypTest() throws AccountStatementException {
		List<AccountResponseDto> resList = new ArrayList<AccountResponseDto>();
		AccountResponseDto res = new AccountResponseDto();
		res.setAccount_number(0012250016002l);
		res.setAccount_type("savings");
		res.setId(2l);
		resList.add(res);
		Mockito.when(accountsService.getAccountsByAccntType("savings")).thenReturn(resList);
		assertNotNull(accountController.fetchAccountsByAccountTyp("savings"));
	}
	
	@Test
	public void fetchAccountsByAccountNum() throws AccountStatementException {
		List<AccountResponseDto> resList = new ArrayList<AccountResponseDto>();
		AccountResponseDto res = new AccountResponseDto();
		res.setAccount_number(0012250016002l);
		res.setAccount_type("savings");
		res.setId(2l);
		resList.add(res);
		Long accountNumber = 0012250016002l;
		Mockito.when(accountsService.getAccountsByAccountNumber(accountNumber)).thenReturn(resList);
		assertNotNull(accountController.fetchAccountsByAccountNum(accountNumber));
	}
	
	@Test
	public void fetchAccountsDetailsTest() throws AccountStatementException {
		List<AccountResponseDto> resList = new ArrayList<AccountResponseDto>();
		AccountResponseDto res = new AccountResponseDto();
		res.setAccount_number(0012250016002l);
		res.setAccount_type("savings");
		res.setId(2l);
		resList.add(res);
		
		Mockito.when(accountsService.getAccounts()).thenReturn(resList);
		
		assertNotNull(accountController.fetchAccountsDetails());
	}

}
