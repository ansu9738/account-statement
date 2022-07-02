package com.test.accountstatement.service;

import java.text.ParseException;
import java.util.List;

import com.test.accountstatement.exception.AccountStatementException;
import com.test.accountstatement.models.DateRangeResDto;
import com.test.accountstatement.models.PriceRangeResDto;
import com.test.accountstatement.models.StatementResponseDto;

public interface AccountsStatementService {
	
	List<StatementResponseDto> getStatements(Long accountId) throws AccountStatementException;
	
	List<DateRangeResDto> getDateRangeStatements(String fromDate,  String toDate) throws ParseException, AccountStatementException;
	
	List<PriceRangeResDto> getPriceRangeStatements(String startPrice, String endPrice) throws AccountStatementException;
	
	List<StatementResponseDto> getThreeMonthsStatements() throws AccountStatementException;

}
