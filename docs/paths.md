## Paths
### get all accounts
```
GET /accounts
```

#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|successful operation|AccountResponse array|


#### Produces

* application/json

#### Tags

* accounts

### POST /accounts
#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|successful operation|AccountResponse array|


#### Tags

* accounts

### calculate total reconciled balance of this type account
```
GET /accounts/balance/reconciled/{typeAccount}
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|typeAccountStr|type of the account|true|enum (BANK, ASSET, LIABILITY, CASH)||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|successful operation|number|
|404|Type account not found|No Content|


#### Produces

* application/json

#### Tags

* accounts

### calculate total balance of this type account
```
GET /accounts/balance/{typeAccountStr}
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|typeAccountStr|type of the account|true|enum (BANK, ASSET, LIABILITY, CASH)||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|successful operation|number|
|404|Type account not found|No Content|


#### Produces

* application/json

#### Tags

* accounts

### get account by id
```
GET /accounts/{accountId}
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|accountId|id of the account|true|string||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|successful operation|AccountResponse|
|404|Account not found|No Content|


#### Produces

* application/json

#### Tags

* accounts

### PUT /accounts/{accountId}
#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|accountId||true|string||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|successful operation|AccountResponse array|


#### Tags

* accounts

### calculate the balance of this account
```
GET /accounts/{accountId}/balance
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|accountId|id of the account|true|string||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|successful operation|number|
|404|Account not found|No Content|


#### Produces

* application/json

#### Tags

* accounts

### calculate the reconciled balance of this account
```
GET /accounts/{accountId}/balance/reconciled
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|accountId|id of the account|true|string||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|successful operation|number|
|404|Account not found|No Content|


#### Produces

* application/json

#### Tags

* accounts

### DELETE /accounts/{accountNumber}
#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|accountId||true|string||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|successful operation|AccountResponse array|


#### Tags

* accounts

### get all categories
```
GET /categories
```

#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|successful operation|CategoryResponse array|


#### Produces

* application/json

#### Tags

* categories

### get all currencies
```
GET /currencies
```

#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|successful operation|CurrencyResponse array|


#### Produces

* application/json

#### Tags

* currencies

### get all parties
```
GET /parties
```

#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|successful operation|PartyResponse array|


#### Produces

* application/json

#### Tags

* parties

### Create a new transaction in the grisbi data file
```
POST /transactions
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|BodyParameter|body||false|TransactionRequest||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|400|the transaction parameter is not valid|No Content|
|404|Account not found|No Content|


#### Consumes

* application/json

#### Produces

* application/json

#### Tags

* transactions

### get all transaction from an account
```
GET /transactions/{accountId}
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|accountId|account id|true|string||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|successful operation|ListTransactionResponse|


#### Produces

* application/json

#### Tags

* transactions

### get all transaction from an account, with pagination
```
GET /transactions/{accountNumber}/page/{page}/perpage/{perpage}
```

#### Parameters
|Type|Name|Description|Required|Schema|Default|
|----|----|----|----|----|----|
|PathParameter|accountNumber|account id|true|string||
|PathParameter|page|page number|true|integer (int32)||
|PathParameter|perpage|item per page|true|integer (int32)||


#### Responses
|HTTP Code|Description|Schema|
|----|----|----|
|200|successful operation|ListTransactionResponse|


#### Produces

* application/json

#### Tags

* transactions

