package grisbiweb.server.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import grisbiweb.server.dto.AccountDto;
import grisbiweb.server.exception.NotImplementedException;
import grisbiweb.server.mapper.AccountMapper;
import grisbiweb.server.model.Account.AccountType;
import grisbiweb.server.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/accounts")
@Api(value = "/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMapper accountMapper;

    /**
     * delete one account
     *
     * @return @
     */
    @RequestMapping(value = "/{accountId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AccountDto> deleteAccount(@PathVariable("accountId") String accountId) {
        throw new NotImplementedException();
    }

    /**
     * get one account
     * 
     * @return @
     */
    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "get account by id", response = AccountDto.class)
    @ApiResponses(value = { @ApiResponse(code = 404, message = "AccountOld not found") })
    public AccountDto getAccount(
            @ApiParam(value = "id of the account", required = true) @PathVariable("accountId") String accountId) {
        return accountMapper.accountToAccountDto(accountService.getAccountById(accountId));
    }

    /**
     * return all accounts
     * 
     * @return @
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "get all accounts", response = AccountDto.class, responseContainer = "List")
    public List<AccountDto> getAccounts() {
        return accountMapper.accountToAccountDto(accountService.getOpenedAccounts());
    }

    /**
     * get balance by account id
     * 
     * @return @
     */
    @RequestMapping(value = "/{accountId}/balance", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = { @ApiResponse(code = 404, message = "AccountOld not found") })
    @ApiOperation(value = "calculate the balance of this account", response = BigDecimal.class)
    public BigDecimal getBalanceByAccountId(
            @ApiParam(value = "id of the account", required = true) @PathVariable("accountId") String accountId) {
        return accountService.getBalance(accountId, false);
    }

    /**
     * get reconciled balance by account id
     * 
     * @return @
     */
    @RequestMapping(value = "/{accountId}/balance/reconciled", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = { @ApiResponse(code = 404, message = "AccountOld not found") })
    @ApiOperation(value = "calculate the reconciled balance of this account", response = BigDecimal.class)
    public BigDecimal getBalanceReconciledByAccountId(
            @ApiParam(value = "id of the account", required = true) @PathVariable("accountId") String accountId) {
        return accountService.getBalance(accountId, true);
    }

    /**
     * get balance total by account type
     * 
     * @return @
     */
    @RequestMapping(value = "/balance/{typeAccountStr}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = { @ApiResponse(code = 404, message = "Type account not found") })
    @ApiOperation(value = "calculate total balance of this type account", response = BigDecimal.class)
    public BigDecimal getBalanceTotalByAccountType(
            @ApiParam(value = "type of the account", allowableValues = "BANK,ASSET,LIABILITY,CASH") @PathVariable("typeAccountStr") String typeAccountStr) {
        AccountType typeAccount = AccountMapper.getAccountTypeFromStringName(typeAccountStr);
        return accountService.getBalanceTotalByAccountType(typeAccount, false);
    }

    /**
     * get balance total by account type
     * 
     * @return @
     */
    @RequestMapping(value = "/balance/reconciled/{typeAccountStr}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = { @ApiResponse(code = 404, message = "Type account not found") })
    @ApiOperation(value = "calculate total reconciled balance of this type account", response = BigDecimal.class)
    public BigDecimal getBalanceTotalReconciledByAccountType(
            @ApiParam(value = "type of the account", allowableValues = "BANK,ASSET,LIABILITY,CASH") @PathVariable("typeAccountStr") String typeAccountStr) {
        AccountType typeAccount = AccountMapper.getAccountTypeFromStringName(typeAccountStr);
        return accountService.getBalanceTotalByAccountType(typeAccount, true);
    }

    /**
     * create one account
     *
     * @return @
     */
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AccountDto> postAccount() {
        throw new NotImplementedException();
    }

    /**
     * update one account
     *
     * @return @
     */
    @RequestMapping(value = "/{accountId}", method = RequestMethod.PUT)
    public List<AccountDto> putAccount(@PathVariable("accountId") String accountId) {
        throw new NotImplementedException();
    }
}
