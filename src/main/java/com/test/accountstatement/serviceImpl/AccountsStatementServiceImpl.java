package com.test.accountstatement.serviceImpl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.test.accountstatement.exception.AccountStatementException;
import com.test.accountstatement.models.DateRangeResDto;
import com.test.accountstatement.models.PriceRangeResDto;
import com.test.accountstatement.models.StatementResponseDto;
import com.test.accountstatement.service.AccountsStatementService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountsStatementServiceImpl implements AccountsStatementService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<StatementResponseDto> getStatements(Long accountId) throws AccountStatementException {
		log.info("AccountsStatementServiceImpl .. getStatements().. method execution started");
		String sql = "SELECT id, account_number, datefield, amount FROM STATEMENT INNER JOIN ACCOUNT ON STATEMENT.account_id=ACCOUNT.ID WHERE account_id=?";
		List<StatementResponseDto> responseList = null;
        try {
        	responseList = this.jdbcTemplate.query(sql,
    				new BeanPropertyRowMapper(StatementResponseDto.class), new Object[] { accountId });
		} catch (Exception e) {
			log.error("Error occurred while getting the statements using account id");
			throw new AccountStatementException("Error occurred while getting the statements using account id", HttpStatus.INTERNAL_SERVER_ERROR);
		}
        log.info("AccountsStatementServiceImpl .. getStatements().. method execution ends");
		return responseList;
	}

	@Override
	public List<DateRangeResDto> getDateRangeStatements(String fromDate, String toDate) throws ParseException, AccountStatementException {
		log.info("AccountsStatementServiceImpl .. getDateRangeStatements().. method execution started");
		String sql = "SELECT id,account_id, account_number, datefield, amount FROM STATEMENT INNER JOIN ACCOUNT ON STATEMENT.account_id=ACCOUNT.ID";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		LocalDate startDate = LocalDate.parse(fromDate, formatter);
		LocalDate endDate = LocalDate.parse(toDate, formatter);
		List<DateRangeResDto> dateRangeResList = null;
		List<DateRangeResDto> dateRangeResponseList = null;
		try {
			dateRangeResList = this.jdbcTemplate.query(sql,
					(rs, rownum) -> new DateRangeResDto(rs.getLong("id"), rs.getLong("account_id"),
							LocalDate.parse(rs.getString("datefield"), formatter), rs.getString("amount"),
							rs.getString("account_number")));
			dateRangeResponseList = dateRangeResList.stream()
					.filter(data -> data.getDatefield().isAfter(startDate.minusDays(1))
							&& data.getDatefield().isBefore(endDate.plusDays(1)))
					.collect(Collectors.toList());

			for (DateRangeResDto dateRangeResDto : dateRangeResponseList) {
				Integer hashedAccountNumber = dateRangeResDto.getAccount_number().hashCode();
				String hashedAccntNum = String.valueOf(hashedAccountNumber);
				dateRangeResDto.setAccount_number(hashedAccntNum);
			}
		} catch (Exception e) {
			log.error("Error occurred while retreiving the statements based on date range");
			throw new AccountStatementException("Error occurred while retreiving the statements based on date range", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.info("AccountsStatementServiceImpl .. getDateRangeStatements().. method execution ends");
		return dateRangeResponseList;
	}

	@Override
	public List<PriceRangeResDto> getPriceRangeStatements(String startPrice, String endPrice) throws AccountStatementException {
		log.info("AccountsStatementServiceImpl .. getPriceRangeStatements().. method execution started");
		String sql = "SELECT id,account_id, account_number, datefield, amount FROM STATEMENT INNER JOIN ACCOUNT ON STATEMENT.account_id=ACCOUNT.ID";
		BigDecimal min = new BigDecimal(startPrice);
		BigDecimal max = new BigDecimal(endPrice);
		List<PriceRangeResDto> priceRangeList = null;
		List<PriceRangeResDto> priceRangeResList = null;
		try {
			priceRangeList = this.jdbcTemplate.query(sql,
					(rs, rownum) -> new PriceRangeResDto(rs.getLong("id"), rs.getLong("account_id"),
							rs.getString("datefield"), new BigDecimal(rs.getString("amount")),
							rs.getString("account_number")));

			priceRangeResList = priceRangeList.stream()
					.filter(data -> data.getAmount().compareTo(min) >= 0 && data.getAmount().compareTo(max) <= 0)
					.collect(Collectors.toList());
		} catch (Exception e) {
			log.error("Error occurred while retreiving the statements using price range");
			throw new AccountStatementException("Error occurred while retreiving the statements using price range", HttpStatus.INTERNAL_SERVER_ERROR);
		}
        
		log.info("AccountsStatementServiceImpl .. getPriceRangeStatements().. method execution ends");
		return priceRangeResList;
	}

	@Override
	public List<StatementResponseDto> getThreeMonthsStatements() throws AccountStatementException {
		log.info("AccountsStatementServiceImpl .. getThreeMonthsStatements().. method execution started");
		String sql = "Select top 3 id,account_id, account_number, datefield, amount FROM STATEMENT INNER JOIN ACCOUNT ON STATEMENT.account_id=ACCOUNT.ID";
		List<StatementResponseDto> statementResList = null;
		try {
			statementResList = this.jdbcTemplate.query(sql,
					new BeanPropertyRowMapper(StatementResponseDto.class));
		} catch (Exception e) {
			log.error("Error occurred while retreiving the three month statements");
			throw new AccountStatementException("Error occurred while retreiving the three month statements", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		log.info("AccountsStatementServiceImpl .. getThreeMonthsStatements().. method execution ends");
		return statementResList;
	}

}
