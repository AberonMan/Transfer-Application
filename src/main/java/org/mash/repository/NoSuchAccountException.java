package org.mash.repository;


/**
 * Exception that is thrown if there is no account in repository
 * @author Maksim Shestakov
 */
public class NoSuchAccountException extends RuntimeException {

    private final long absentAccountId;

    public NoSuchAccountException(long absentAccountId) {
        this.absentAccountId = absentAccountId;
    }

    @Override
    public String getMessage() {
        return "There is no account with id: " + absentAccountId;
    }
}
