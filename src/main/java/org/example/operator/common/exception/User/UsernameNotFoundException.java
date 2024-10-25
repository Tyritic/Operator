package org.example.operator.common.exception.User;

import org.example.operator.common.exception.BaseException;

public class UsernameNotFoundException extends BaseException {
    public UsernameNotFoundException(){

    }
    public UsernameNotFoundException(String msg){
        super(msg);
    }
}
