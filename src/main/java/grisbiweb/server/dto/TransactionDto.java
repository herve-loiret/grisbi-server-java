package grisbiweb.server.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDto {

	private Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate date;
	private String party;
	private Double debit;
	private Double credit;
	private Double solde;
	private String category;
	private String currencyId;
	private String pr;
	private List<TransactionDto> subTransactions = new ArrayList<>();

}
