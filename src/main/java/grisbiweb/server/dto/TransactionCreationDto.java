package grisbiweb.server.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import grisbiweb.server.utils.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class TransactionCreationDto {

    @ApiModelProperty(value = "id of the account", required = true)
    private String accountId;

    @ApiModelProperty(value = "date of the transaction", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.FRENCH_DATE_PATTERN)
    private LocalDate date;

    @ApiModelProperty(value = "category id")
    private String categoryId;

    @ApiModelProperty(value = "subcategory id")
    private String subCategoryId;

    @ApiModelProperty(value = "party id")
    private String partyId;

    @ApiModelProperty(value = "amount of the transaction")
    private BigDecimal amount;

}
