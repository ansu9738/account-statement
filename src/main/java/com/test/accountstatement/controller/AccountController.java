package com.test.accountstatement.controller;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.accountstatement.exception.AccountStatementException;
import com.test.accountstatement.models.AccountReqDto;
import com.test.accountstatement.models.AccountResponseDto;
import com.test.accountstatement.service.AccountsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AccountController {

	@Autowired
	AccountsService accountsService;

	@PostMapping("/account")
	public void addAccount(@RequestBody AccountReqDto accountReqDto) throws AccountStatementException {
		log.info("AccountController .. addAccount().. method execution started");
		Optional<AccountReqDto> accountReq = Optional.ofNullable(accountReqDto);
		if (accountReq.isPresent()) {
			accountsService.addAccount(accountReq.get());
		}
	}

	@GetMapping("/account/{accountType}")
	public List<AccountResponseDto> fetchAccountsByAccountTyp(@PathVariable("accountType") String accountType)
			throws AccountStatementException {
		log.info("AccountController .. fetchAccountsByAccountTyp().. method execution started");
		List<AccountResponseDto> accntResList = null;
		if (!StringUtils.isEmpty(accountType)) {
			accntResList = accountsService.getAccountsByAccntType(accountType);
		}
		log.info("AccountController .. fetchAccountsByAccountTyp().. method execution ends");
		return accntResList;
	}

	@GetMapping("/account/{accountNumber}")
	public List<AccountResponseDto> fetchAccountsByAccountNum(@PathVariable("accountNumber") Long accountNumber)
			throws AccountStatementException {
		log.info("AccountController .. fetchAccountsByAccountNum().. method execution started");
		List<AccountResponseDto> accntResList = null;
		if (accountNumber != null) {
			accntResList = accountsService.getAccountsByAccountNumber(accountNumber);
		}
		log.info("AccountController .. fetchAccountsByAccountNum().. method execution ends");
		return accntResList;
	}

	@GetMapping("/account")
	public List<AccountResponseDto> fetchAccountsDetails() throws AccountStatementException {
		log.info("AccountController .. fetchAccountsDetails().. method execution started");
		List<AccountResponseDto> accntResList = accountsService.getAccounts();
		log.info("AccountController .. fetchAccountsDetails().. method execution ends");
		return accntResList;
	}
}
