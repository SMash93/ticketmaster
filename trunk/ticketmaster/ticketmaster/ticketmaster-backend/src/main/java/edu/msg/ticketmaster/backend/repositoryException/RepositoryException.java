package edu.msg.ticketmaster.backend.repositoryException;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class RepositoryException extends RuntimeException {

	private static final long serialVersionUID = -2167613584805462710L;

	public RepositoryException() {
		super();
	}

	public RepositoryException(final String message) {
		super(message);
	}

	public RepositoryException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
