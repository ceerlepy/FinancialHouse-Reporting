package uk.financial.reporting.controller;


import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import uk.financial.reporting.exception.LoginException;
import uk.financial.reporting.exception.RemoteSystemException;
import uk.financial.reporting.service.TransactionReportService;
import uk.financial.reporting.transactionreport.bean.TransactionReportRequest;
import uk.financial.reporting.transactionreport.bean.TransactionReportResponse;
import uk.financial.reporting.transactionreport.bean.TransactionReportResponse.Response;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = TransactionReportController.class)
public class TransactionReportControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private TransactionReportService transactionReportService;

    @Test
    public void retrieveTransactionReport_shouldReturnHttp200WithContent_whenTransactionReportRetrievedSuccessfully() throws Exception {

        when(transactionReportService.retrieveReportList(Mockito.any(TransactionReportRequest.class))).thenReturn(TRANSACTION_REPORT);

        mvc.perform(post(TRANSACTION_REPORT_URL)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(INPUT)
                .header("token", "token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response[0].count").value(COUNT))
                .andExpect(jsonPath("$.response[0].total").value(TOTAL))
                .andExpect(jsonPath("$.response[0].currency").value(CURRENCY));
        
        verify(transactionReportService, Mockito.times(1)).retrieveReportList(Mockito.any(TransactionReportRequest.class));

    }

    @Test
    public void retrieveTransactionReport_shouldReturnHttp401_whenTokenIsExpired() throws Exception {

        doThrow(LoginException.class).when(transactionReportService).retrieveReportList(Mockito.any(TransactionReportRequest.class));

        mvc.perform(post(TRANSACTION_REPORT_URL)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(INPUT)
                .header("token", "token"))
                .andExpect(status().isUnauthorized());

    }

    @Test
    public void retrieveTransactionReport_shouldReturnHttp500_whenUnpexctedErrorOcuurs() throws Exception {

        doThrow(RemoteSystemException.class).when(transactionReportService).retrieveReportList(Mockito.any(TransactionReportRequest.class));

        mvc.perform(post(TRANSACTION_REPORT_URL)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(INPUT)
                .header("token", "token"))
                .andExpect(status().isInternalServerError());

    }

    @Test
    public void retrieveTransactionReport_shouldReturnResponseWithHttpStatus400_whenInvalidInputReceives() throws Exception {

        mvc.perform(post(TRANSACTION_REPORT_URL)
                .content(EMPTY_INPUT)
                .contentType(MediaType.APPLICATION_JSON)
                .header("token", "token"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fromDate", is("Start date is mandatory")))
                .andExpect(jsonPath("$.toDate", is("Finish date is mandatory")))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    private static final String TRANSACTION_REPORT_URL = "/api/transactions/report";
    private static final int COUNT = 12;
    private static final int TOTAL = 1200;
    private static final String CURRENCY = "currency";
    private static final String INPUT = "{\"fromDate\": \"fromDate\", \"toDate\" : \"toDate\"}";
    private static final String EMPTY_INPUT = "{\"startDate\": \"\", \"finishDate\" : \"\"}";
    

    private static final List<Response> TRANSACTION_REPORT_RESPONSE_LIST = new ArrayList<Response>(){
    	/**
		 * 
		 */
		private static final long serialVersionUID = 6769540488268211127L;

	{
        add(Response.builder()
                .count(COUNT)
                .total(TOTAL)
                .currency(CURRENCY)
                .build());
    }};
    
    private static final TransactionReportResponse TRANSACTION_REPORT = TransactionReportResponse.builder()
    		.responseList(TRANSACTION_REPORT_RESPONSE_LIST).build();

}