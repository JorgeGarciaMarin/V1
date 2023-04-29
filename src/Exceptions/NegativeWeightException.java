package Exceptions;

import javax.swing.*;

public class NegativeWeightException extends Throwable {
    public NegativeWeightException(String message){
        super(message);
    }

}
