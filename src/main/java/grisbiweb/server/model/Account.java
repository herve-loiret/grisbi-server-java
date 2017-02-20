package grisbiweb.server.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Account {
    
    public enum AccountType {
        BANK, ASSET, LIABILITY, CASH;
    }

    private String id;
    private AccountType accountType;
    private String currencyId;
    private BigDecimal initialBalance;
    private String kind;
    private String name;
}
