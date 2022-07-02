package com.test.accountstatement.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.test.accountstatement.exception.AccountStatementException;
import com.test.accountstatement.models.AccountReqDto;
import com.test.accountstatement.models.AccountResponseDto;
import com.test.accountstatement.service.AccountsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountsServiceImpl implements AccountsService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<AccountResponseDto> getAccountsByAccntType(String accountType) throws AccountStatementException {
		log.info("AccountsServiceImpl .. getAccountsByAccntType().. method execution started");
		String sql = "Select * from account where account_type = ?";

		List<AccountResponseDto> accountsList = null;
		try {

			accountsList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(AccountResponseDto.class),
					new Object[] { accountType });
		} catch (Exception e) {
			log.info("Error occurred while retreiving the account details using accountType");
			throw new AccountStatementException("Error occurred while retreiving the account details using accountType", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.info("AccountsServiceImpl .. getAccounts().. method execution ends");
		return accountsList;
	}

	@Override
	public List<AccountResponseDto> getAccounts() throws AccountStatementException {
		log.info("AccountsServiceImpl .. getAccounts().. method execution started");
		String sql = "Select * from account";

		List<AccountResponseDto> accountsList = null;

		try {

			accountsList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(AccountResponseDto.class));
		} catch (Exception e) {
			log.error("Error occurred while retreiving the account details");
			throw new AccountStatementException("Error occurred while retreiving the account details", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.info("AccountsServiceImpl .. getAccounts().. method execution started");
		return accountsList;
	}

	@Override
	public void addAccount(AccountReqDto accountReqDto) throws AccountStatementException {
		log.info("AccountsServiceImpl .. addAccount().. method execution started");
		String sql = "Insert into account(id, account_type, account_number) VALUES(?,?,?)";
		try {
			
			jdbcTemplate.update(sql, new Object[] { accountReqDto.getId(), accountReqDto.getAccountType(),
					accountReqDto.getAccountNumber() });
		} catch (Exception e) {
			log.error("Error occurred while adding data's to account");
			throw new AccountStatementException("Error occurred while adding data's to account", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

	}

	@Override
	public List<AccountResponseDto> getAccountsByAccountNumber(Long accountNumber) throws AccountStatementException {
		log.info("AccountsServiceImpl .. getAccountsByAccountNumber().. method execution started");
		String sql = "Select * from account where account_number = ?";
		List<AccountResponseDto> accountsList = null;
        try {
        	accountsList = jdbcTemplate.query(sql,
    				new BeanPropertyRowMapper(AccountResponseDto.class), new Object[] { accountNumber });
		} catch (Exception e) {
			log.error("Error occurred while retreiving the details using accountNumber");
			throw new AccountStatementException("Error occurred while retreiving the details using accountNumber", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

		return accountsList;
	}

}
