package com.test.accountstatement.controller;

import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.accountstatement.exception.AccountStatementException;
import com.test.accountstatement.models.DateRangeResDto;
import com.test.accountstatement.models.PriceRangeResDto;
import com.test.accountstatement.models.StatementResponseDto;
import com.test.accountstatement.service.AccountsStatementService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class StatementController {

	@Autowired
	AccountsStatementService accountsSatementService;

	@GetMapping("/statements")
	public List<StatementResponseDto> getStatements(@RequestParam Long accountId) throws AccountStatementException {
		log.info("StatementController .. getStatements() .. method execution started");
		return accountsSatementService.getStatements(accountId);

	}

	@GetMapping("/statements/daterange")
	public List<DateRangeResDto> getDateRangeStatements(@RequestParam String fromDate, @RequestParam String toDate)
			throws ParseException, AccountStatementException {
		log.info("StatementController .. getDateRangeStatements() .. method execution started");
		return accountsSatementService.getDateRangeStatements(fromDate, toDate);
	}

	@GetMapping("/statements/pricerange")
	public List<PriceRangeResDto> getPriceRangeStatements(@RequestParam String startPrice,
			@RequestParam String endPrice) throws AccountStatementException {
		log.info("StatementController .. getPriceRangeStatements() .. method execution started");
		return accountsSatementService.getPriceRangeStatements(startPrice, endPrice);
	}

	@GetMapping("/statement/threemonths")
	public List<StatementResponseDto> getThreeMonthsStatements() throws AccountStatementException {
		log.info("StatementController .. getThreeMonthsStatements() .. method execution started");
		return accountsSatementService.getThreeMonthsStatements();
	}
}
