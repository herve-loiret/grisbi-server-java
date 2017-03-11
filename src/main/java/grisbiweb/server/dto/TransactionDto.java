package grisbiweb.server.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class TransactionDto {

    private Long id;
    private Date date;
    private String party;
    private Double debit;
    private Double credit;
    private Double solde;
    private String category;
    private String currencyId;
    private String pr;
    private List<TransactionDto> subTransactions = new ArrayList<>();

    public String getCreditUI() {
        return credit == null ? "" : credit + " €";
    }

    public String getDateUI() {

        if (date == null) {
            return null;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.FRENCH);
        return simpleDateFormat.format(date);
    }

    public String getSoldeUI() {
        return solde + " €";
    }

}
