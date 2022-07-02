package com.test.accountstatement.controller;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.test.accountstatement.exception.AccountStatementException;
import com.test.accountstatement.models.DateRangeResDto;
import com.test.accountstatement.models.PriceRangeResDto;
import com.test.accountstatement.models.StatementResponseDto;
import com.test.accountstatement.service.AccountsStatementService;

@ExtendWith(SpringExtension.class)
public class StatementControllerTest {

	@InjectMocks
	StatementController statementController;
	
	@Mock
	AccountsStatementService accountsSatementService;
	
	@Test
	public void getStatementsTest() throws AccountStatementException {
		Mockito.when(accountsSatementService.getStatements(1l)).thenReturn(Mockito.anyList());
		assertNotNull(statementController.getStatements(1l));
	}
	
	@Test
	public void getDateRangeStatementsTest() throws ParseException, AccountStatementException {
		List<DateRangeResDto> dateResList = new ArrayList<DateRangeResDto>();
		DateRangeResDto res = new DateRangeResDto();
		res.setAccount_id(2l);
		res.setAccount_number("0955353635");
		res.setAmount("987.890");
		res.setDatefield(LocalDate.now());
		res.setId(1l);
		dateResList.add(res);
		Mockito.when(accountsSatementService.getDateRangeStatements("03.10.2011", "03.10.2012")).thenReturn(dateResList);
		assertNotNull(statementController.getDateRangeStatements("03.10.2011", "03.10.2012"));
	}
	
	@Test
	public void getPriceRangeStatementsTest() throws AccountStatementException {
		List<PriceRangeResDto> priceResList = new ArrayList<PriceRangeResDto>();
		PriceRangeResDto res = new PriceRangeResDto();
		res.setAccount_id(2l);
		res.setAccount_number("096484647");
		res.setAmount(new BigDecimal("98467.09"));
		res.setDatefield("09.10.2020");
		res.setId(1l);
		
		priceResList.add(res);
		Mockito.when(accountsSatementService.getPriceRangeStatements("987.89", "1000.99")).thenReturn(priceResList);
		
		assertNotNull(statementController.getPriceRangeStatements("987.89", "1000.99"));
	}
	
	@Test
	public void getThreeMonthsStatements() throws AccountStatementException {
		List<StatementResponseDto> statementResDto = new ArrayList<StatementResponseDto>();
		StatementResponseDto res = new StatementResponseDto();
		res.setAccount_id(2l);
		res.setAmount("70");
		res.setDatefield("03.20.2022");
		res.setAccount_number("012222201");
		statementResDto.add(res);
		
		Mockito.when(accountsSatementService.getThreeMonthsStatements()).thenReturn(statementResDto);
		
		assertNotNull(statementController.getThreeMonthsStatements());
	}
}
