package grisbiweb.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import grisbiweb.server.dto.ListTransactionDto;
import grisbiweb.server.dto.TransactionCreationDto;
import grisbiweb.server.dto.TransactionDto;
import grisbiweb.server.model.Transaction;
import grisbiweb.server.rest.mapper.TransactionMapper;
import grisbiweb.server.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/transactions")
@Api(value = "/transactions", description = "Operations about transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionMapper transactionMapper;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiResponses(value = { @ApiResponse(code = 400, message = "the transaction parameter is not valid"),
            @ApiResponse(code = 404, message = "AccountOld not found") })
    @ApiOperation(value = "Create a new transaction in the grisbi data file")
    public void createTransaction(TransactionCreationDto transactionCreationDto) {
        Transaction transaction = transactionMapper.mapTransactionRequest(transactionCreationDto);
        transactionService.createTransaction(transaction);
    }

    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "get all transaction from an account", response = ListTransactionDto.class)
    public ListTransactionDto getTransactionsByAccountId(
            @ApiParam(value = "account id") @PathVariable("accountId") String accountId) {
        List<Transaction> transactions = transactionService.getTransactionsOrderedByAccountId(accountId);
        List<TransactionDto> transactionUis = transactionMapper.mapTransactions(transactions);
        return new ListTransactionDto(transactionUis);
    }

    /**
     * 
     * @param accountNumber
     * @param page
     * @param perpage
     * @return @
     */
    @RequestMapping(value = "/{accountNumber}/page/{page}/perpage/{perpage}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "get all transaction from an account, with pagination", response = ListTransactionDto.class)
    public ListTransactionDto getTransactionsPaginateByAccountNumber(
            @ApiParam(value = "account id") @PathVariable("accountNumber") String accountNumber, //
            @ApiParam(value = "page number") @PathVariable("page") Integer page, //
            @ApiParam(value = "item per page") @PathVariable("perpage") Integer perpage) {
        List<Transaction> transactions = transactionService.getTransactionsOrderedByAccountId(accountNumber, page,
                perpage);

        List<TransactionDto> transactionsUI = transactionMapper.mapTransactions(transactions);
        return new ListTransactionDto(transactionsUI);
    }
}
