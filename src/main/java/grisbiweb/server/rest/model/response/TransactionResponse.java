package grisbiweb.server.rest.model.response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ApiModel
@Getter
@Setter
@ToString
public class TransactionResponse {

    private Long id;
    private Date date;
    private String party;
    private Double debit;
    private Double credit;
    private Double solde;
    private String category;
    private String currencyId;
    private String pr;
    private List<TransactionResponse> subTransactions = new ArrayList<>();

    public String getCreditUI() {
        return credit == null ? "" : credit + " €";
    }

    public String getDateUI() {

        if (date == null) {
            return null;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return simpleDateFormat.format(date);
    }

    public String getSoldeUI() {
        return solde + " €";
    }

}
