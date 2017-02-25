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

import grisbiweb.server.model.TransactionOld;
import grisbiweb.server.rest.mapper.TransactionMapper;
import grisbiweb.server.rest.model.request.TransactionRequest;
import grisbiweb.server.rest.model.response.ListTransactionResponse;
import grisbiweb.server.rest.model.response.TransactionResponse;
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
    public void createTransaction(TransactionRequest transactionRequest) {
        TransactionOld transactionOld = transactionMapper.mapTransactionRequest(transactionRequest);
        transactionService.createTransaction(transactionOld);
    }

    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "get all transaction from an account", response = ListTransactionResponse.class)
    public ListTransactionResponse getTransactionsByAccountNumber(
            @ApiParam(value = "account id") @PathVariable("accountId") String accountId) {
        List<TransactionOld> transactionOlds = transactionService.getTransactionsOrderedByAccountId(accountId);
        List<TransactionResponse> transactionUis = transactionMapper.mapTransactions(transactionOlds);
        return new ListTransactionResponse(transactionUis);
    }

    /**
     * 
     * @param accountNumber
     * @param page
     * @param perpage
     * @return @
     */
    @RequestMapping(value = "/{accountNumber}/page/{page}/perpage/{perpage}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "get all transaction from an account, with pagination", response = ListTransactionResponse.class)
    public ListTransactionResponse getTransactionsPaginateByAccountNumber(
            @ApiParam(value = "account id") @PathVariable("accountNumber") String accountNumber, //
            @ApiParam(value = "page number") @PathVariable("page") Integer page, //
            @ApiParam(value = "item per page") @PathVariable("perpage") Integer perpage) {
        List<TransactionOld> transactionOlds = transactionService.getTransactionsOrderedByAccountId(accountNumber, page,
                perpage);

        List<TransactionResponse> transactionsUI = transactionMapper.mapTransactions(transactionOlds);
        return new ListTransactionResponse(transactionsUI);
    }
}
