package grisbiweb.server.exception;

public class TransactionRequestNotValidException extends RuntimeException{

	private static final long serialVersionUID = -3371848526650970099L;

	public TransactionRequestNotValidException(String field) {
//		super(Response.status(Responses.CLIENT_ERROR)
//				.entity("transaction field " + field + " is not valid") //
//				.type("text/plain") //
//				.build());
	}
}
