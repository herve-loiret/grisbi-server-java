## Definitions
### AccountResponse
|Name|Description|Required|Schema|Default|
|----|----|----|----|----|
|id|id of the account|false|integer (int64)||
|name|name of the account|false|string||
|typeAccount|id of the currency used in this account|false|enum (BANK, ASSET, LIABILITY, CASH)||
|currencyId|id of the currency used in this account|false|string||


### CategoryResponse
|Name|Description|Required|Schema|Default|
|----|----|----|----|----|
|idCategory||false|integer (int64)||
|idSubCategory||false|integer (int64)||
|nameCategory||false|string||
|nameSubCategory||false|string||
|completeId||false|string||
|completeName||false|string||


### CurrencyResponse
|Name|Description|Required|Schema|Default|
|----|----|----|----|----|
|id||false|integer (int64)||
|name||false|string||
|sign||false|string||


### ListTransactionResponse
|Name|Description|Required|Schema|Default|
|----|----|----|----|----|
|transactionsResponse||false|TransactionResponse array||
|totalItem|total item (for pagination purpose|true|integer (int32)||


### PartyResponse
|Name|Description|Required|Schema|Default|
|----|----|----|----|----|
|id|id of the party|true|integer (int64)||
|name|name of the party|true|string||


### TransactionRequest
|Name|Description|Required|Schema|Default|
|----|----|----|----|----|
|accountId|id of the account|true|string||
|date|date of the transaction|true|string||
|categoryId|category id|false|string||
|subCategoryId|subcategory id|false|string||
|partyId|party id|false|string||
|debit|credit - either credit or debit are mandatory|false|string||
|credit|debit - either credit or debit are mandatory|false|string||


### TransactionResponse
|Name|Description|Required|Schema|Default|
|----|----|----|----|----|
|id||false|integer (int64)||
|date||false|string (date-time)||
|party||false|string||
|debit||false|number (double)||
|credit||false|number (double)||
|solde||false|number (double)||
|category||false|string||
|currencyId||false|string||
|pr||false|string||
|subTransactions||false|TransactionResponse array||
|creditUI||false|string||
|dateUI||false|string||
|soldeUI||false|string||


