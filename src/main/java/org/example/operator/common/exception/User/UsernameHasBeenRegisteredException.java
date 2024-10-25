package org.example.operator.common.exception.User;
import org.example.operator.common.exception.BaseException;

public class UsernameHasBeenRegisteredException extends BaseException {
    public UsernameHasBeenRegisteredException(){

    }
    public UsernameHasBeenRegisteredException(String msg){
        super(msg);
    }

}
