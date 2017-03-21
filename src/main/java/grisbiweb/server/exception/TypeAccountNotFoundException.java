package grisbiweb.server.exception;

public class TypeAccountNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1622896321495454368L;

	public TypeAccountNotFoundException(String typeAccount, Throwable cause) {
		super("type account " + typeAccount + " can not be found. (possible value = BANK, ASSET, LIABILITY, CASH",
				cause);
	}

}
