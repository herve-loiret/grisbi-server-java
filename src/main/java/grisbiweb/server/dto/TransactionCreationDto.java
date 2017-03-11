package grisbiweb.server.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class TransactionCreationDto {

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
