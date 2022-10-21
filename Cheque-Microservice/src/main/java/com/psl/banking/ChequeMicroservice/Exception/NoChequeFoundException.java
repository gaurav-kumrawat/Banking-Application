package com.psl.banking.ChequeMicroservice.Exception;

public class NoChequeFoundException extends RuntimeException{

    public NoChequeFoundException(String msg){
        super(msg);
    }
}
