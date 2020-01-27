package uk.financial.reporting.exception;

public class ExceptionResponse {

	private String response;

	public ExceptionResponse(String response) {
		super();
		this.response = response;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
