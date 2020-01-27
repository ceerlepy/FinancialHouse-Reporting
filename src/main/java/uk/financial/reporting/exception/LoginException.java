package uk.financial.reporting.exception;

public class LoginException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2117598824432970212L;

	public LoginException(String message){
        super(message);
    }

    public LoginException(Throwable e){
        super(e);
    }

}
