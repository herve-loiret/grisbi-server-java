package grisbiweb.server.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.Responses;

public class TransactionRequestNotValidException extends WebApplicationException {

	private static final long serialVersionUID = -3371848526650970099L;

	public TransactionRequestNotValidException(String field) {
		super(Response.status(Responses.CLIENT_ERROR)
				.entity("transaction field " + field + " is not valid") //
				.type("text/plain") //
				.build());
	}
}
