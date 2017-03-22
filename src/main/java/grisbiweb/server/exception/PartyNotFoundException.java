package grisbiweb.server.exception;

public class PartyNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1801575558879254344L;

	public PartyNotFoundException(String partyId){
		super("party with id " +  partyId +" cannot be find in the grisbi file");
	}
}
