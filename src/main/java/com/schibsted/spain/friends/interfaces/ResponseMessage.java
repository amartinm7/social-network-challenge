package com.schibsted.spain.friends.interfaces;

public class ResponseMessage<T>{
    private T message;
    private int value;

    public ResponseMessage() {super();}

    public ResponseMessage( T object, int value) {
        this.message = object;
        this.value = value;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "message=" + message +
                ", value=" + value +
                '}';
    }
}
