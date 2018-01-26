package com.inacap.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException (rollback = true)
public class TransactionException extends Exception {

    public TransactionException() {
        super();
    }
    
}
