package com.test.accountstatement.serviceImpl;

import static org.junit.Assert.assertNotNull;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.test.accountstatement.exception.AccountStatementException;
import com.test.accountstatement.models.DateRangeResDto;
import com.test.accountstatement.models.PriceRangeResDto;
import com.test.accountstatement.models.StatementResponseDto;

@ExtendWith(SpringExtension.class)
public class AccountsStatementServiceImplTest {

	@InjectMocks
	AccountsStatementServiceImpl accountsStatementServiceImpl;

	@Mock
	JdbcTemplate jdbcTemplate;

	@Test
	public void getStatementsTest() throws AccountStatementException {
		Long accountId = 2l;
		String sql = "SELECT id, account_number, datefield, amount FROM STATEMENT INNER JOIN ACCOUNT ON STATEMENT.account_id=ACCOUNT.ID WHERE account_id=?";
		List<StatementResponseDto> statementResDto = new ArrayList<StatementResponseDto>();
		StatementResponseDto res = new StatementResponseDto();
		res.setAccount_id(2l);
		res.setAmount("70");
		res.setDatefield("03.20.2022");
		res.setAccount_number("012222201");
		statementResDto.add(res);

		Mockito.when(jdbcTemplate.query(sql, new BeanPropertyRowMapper(StatementResponseDto.class),
				new Object[] { accountId })).thenReturn(statementResDto);

		assertNotNull(accountsStatementServiceImpl.getStatements(2l).size());
	}

	@Test
	public void getDateRangeStatementsTest() throws ParseException, AccountStatementException {
		String sql = "SELECT id,account_id, account_number, datefield, amount FROM STATEMENT INNER JOIN ACCOUNT ON STATEMENT.account_id=ACCOUNT.ID";
		List<DateRangeResDto> dateResList = new ArrayList<DateRangeResDto>();
		DateRangeResDto res = new DateRangeResDto();
		res.setAccount_id(2l);
		res.setAccount_number("0955353635");
		res.setAmount("987.890");
		res.setDatefield(LocalDate.now());
		res.setId(1l);
		
		dateResList.add(res);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	
		Mockito.when(jdbcTemplate.query(sql, (rs, rownum) -> new DateRangeResDto(rs.getLong("id"), rs.getLong("account_id"),
				LocalDate.parse(rs.getString("datefield"), formatter), rs.getString("amount"),
				rs.getString("account_number")))).thenReturn(dateResList);
		
		assertNotNull(accountsStatementServiceImpl.getDateRangeStatements("06.01.2000", "06.01.2001"));
	}
	
	@Test
	public void getPriceRangeStatements() throws AccountStatementException {
		String sql = "SELECT id,account_id, account_number, datefield, amount FROM STATEMENT INNER JOIN ACCOUNT ON STATEMENT.account_id=ACCOUNT.ID";
		List<PriceRangeResDto> priceResList = new ArrayList<PriceRangeResDto>();
		PriceRangeResDto res = new PriceRangeResDto();
		res.setAccount_id(2l);
		res.setAccount_number("096484647");
		res.setAmount(new BigDecimal("98467.09"));
		res.setDatefield("09.10.2020");
		res.setId(1l);
		
		priceResList.add(res);
		
		Mockito.when(jdbcTemplate.query(sql,
				(rs, rownum) -> new PriceRangeResDto(rs.getLong("id"), rs.getLong("account_id"),
						rs.getString("datefield"), new BigDecimal(rs.getString("amount")),
						rs.getString("account_number")))).thenReturn(priceResList);
		
		assertNotNull(accountsStatementServiceImpl.getPriceRangeStatements("970.987", "1270.987"));
	}
	
	@Test
	public void getThreeMonthsStatementsTest() throws AccountStatementException {
		String sql = "Select top 3 id,account_id, account_number, datefield, amount FROM STATEMENT INNER JOIN ACCOUNT ON STATEMENT.account_id=ACCOUNT.ID";
		List<StatementResponseDto> statementResDto = new ArrayList<StatementResponseDto>();
		StatementResponseDto res = new StatementResponseDto();
		res.setAccount_id(2l);
		res.setAmount("70");
		res.setDatefield("03.20.2022");
		res.setAccount_number("012222201");
		statementResDto.add(res);
		
		Mockito.when(jdbcTemplate.query(sql,
					new BeanPropertyRowMapper(StatementResponseDto.class))).thenReturn(statementResDto);
		
		assertNotNull(accountsStatementServiceImpl.getThreeMonthsStatements());
		
	}
	

	
}
