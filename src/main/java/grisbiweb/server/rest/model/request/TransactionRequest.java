package grisbiweb.server.rest.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel
@Getter
@Setter
public class TransactionRequest {

    @ApiModelProperty(value = "id of the account", required = true)
    private String accountId;

    @ApiModelProperty(value = "date of the transaction", required = true)
    private String date;

    @ApiModelProperty(value = "category id")
    private String categoryId;

    @ApiModelProperty(value = "subcategory id")
    private String subCategoryId;

    @ApiModelProperty(value = "party id")
    private String partyId;

    @ApiModelProperty(value = "credit - either credit or debit are mandatory")
    private String debit;

    @ApiModelProperty(value = "debit - either credit or debit are mandatory")
    private String credit;

}
