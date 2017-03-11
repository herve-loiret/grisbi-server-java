package grisbiweb.server.dto;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@ApiModel
@EqualsAndHashCode
public class ListTransactionDto {

    @Getter
    private List<TransactionDto> transactionsResponse = new ArrayList<>();

    @Getter
    @Setter
    @ApiModelProperty(value = "total item (for pagination purpose", required = true)
    private int totalItem;

    public ListTransactionDto(List<TransactionDto> transactionsResponse) {
        this.transactionsResponse = transactionsResponse;
        totalItem = transactionsResponse.size();
    }

}
