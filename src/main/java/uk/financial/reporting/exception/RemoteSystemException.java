package uk.financial.reporting.exception;

public class RemoteSystemException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3551159325977679742L;

	public RemoteSystemException(String message, Exception ex){
        super(message, ex);
    }

}
