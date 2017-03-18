package grisbiweb.server.dto;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel
@Data
@NoArgsConstructor
public class ListTransactionDto {

    private List<TransactionDto> transactionsResponse = new ArrayList<>();

    @ApiModelProperty(value = "total item (for pagination purpose", required = true)
    private long totalItem;

    public ListTransactionDto(List<TransactionDto> transactionsResponse) {
        this.transactionsResponse = transactionsResponse;
        totalItem = transactionsResponse.size();
    }

}
