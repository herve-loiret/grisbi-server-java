# grisbi-server-java

Grisbi-server-java is a java web application exposing the account manager file of [Grisbi](http://www.grisbi.org) via rest services.

This application is intented to be used with [grisbi-client-web](https://github.com/herve-loiret/grisbi-server-java)

**This project is in  early development !**

## Features
- Generate the synthesis page
- Retrieve list of accounts
- Retrieve list of transactions
- Create basic transaction

## Roadmap
For 1.0 version : 
- manage multiple currency (currently only account in euro)
- allow pagination

## REST methods
For now, methods implemented are : 

- accounts : Operations about accounts
  - get /accounts/{accountId}
    - get account by id
  - get /accounts
    - get all accounts
  - get /accounts/{accountId}/balance
    - calculate the balance of this account
  - get /accounts/{accountId}/balance/reconciled
    - calculate the reconciled balance of this account
  - get /accounts/balance/{typeAccountStr}
    - calculate total balance of this type account
  - get /accounts/balance/reconciled/{typeAccount}
    - calculate total reconciled balance of this type account
- categories : Operations about categories
  - get /categories
    - get all categories
- currencies : Operations about currencies
  - get /currencies
    - get all currencies
- parties : Operations about parties
  - get /parties
    - get all parties
- transactions : Operations about transactions
  - post /transactions
    - Create a new transaction in the grisbi data file
  - get /transactions/{accountId}
    - get all transaction from an account
  - get /transactions/{accountNumber}/page/{page}/perpage/{perpage}
    - get all transaction from an account, with pagination
    
## Complete documentation

- [Definitions](docs/definitions.md)
- [Paths](docs/paths.md)

## Swagger url
- when you run the spring boot application, you can access swagger with theses urls :
  - http://localhost:8080/grisbiweb/swagger/index.html
  - http://localhost:8080/grisbiweb/v2/api-docs