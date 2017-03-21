package grisbiweb.server.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class TransactionCreationDto {

	@ApiModelProperty(value = "id of the account", required = true)
	private String accountId;

	@ApiModelProperty(value = "date of the transaction, format ISO DATE", required = true)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
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
