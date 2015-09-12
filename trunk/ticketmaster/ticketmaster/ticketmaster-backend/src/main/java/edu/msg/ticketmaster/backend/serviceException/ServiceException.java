package edu.msg.ticketmaster.backend.serviceException;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -2167613584805462710L;

	public ServiceException() {
		super();
	}

	public ServiceException(final String message) {
		super(message);
	}

	public ServiceException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
