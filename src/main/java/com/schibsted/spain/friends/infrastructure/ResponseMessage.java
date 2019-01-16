package com.schibsted.spain.friends.infrastructure;

public class ResponseMessage<T>{
    private final T message;
    private final int value;

    public ResponseMessage( T object, int value) {
        this.message = object;
        this.value = value;
    }

    public T getMessage() {
        return message;
    }

    public int getValue() {
        return value;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ResponseMessage{");
        sb.append("message=").append(message);
        sb.append(", value=").append(value);
        sb.append('}');
        return sb.toString();
    }

}
