package grisbiweb.server.rest.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel
public class ListTransactionResponse {

	private List<TransactionResponse> transactionsResponse = new ArrayList<>();

	@ApiModelProperty(value = "total item (for pagination purpose", required = true)
	private int totalItem;

	public ListTransactionResponse(List<TransactionResponse> transactionsResponse) {
		this.transactionsResponse = transactionsResponse;
		totalItem = transactionsResponse.size();
	}

	public int getTotalItem() {
		return totalItem;
	}

	public List<TransactionResponse> getTransactionsResponse() {
		return transactionsResponse;
	}

}
