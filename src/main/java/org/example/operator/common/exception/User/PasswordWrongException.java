package org.example.operator.common.exception.User;

import org.example.operator.common.exception.BaseException;

public class PasswordWrongException extends BaseException {
    public PasswordWrongException(){

    }
    public PasswordWrongException(String msg){
        super(msg);
    }
}
