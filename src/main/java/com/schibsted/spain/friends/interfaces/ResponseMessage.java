package com.schibsted.spain.friends.interfaces;

public class ResponseMessage<T>{
    private final T message;
    private final int value;
    private final Action action;


    public ResponseMessage( T object, int value, Action action) {
        this.message = object;
        this.value = value;
        this.action = action;
    }

    public T getMessage() {
        return message;
    }

    public int getValue() {
        return value;
    }

    public Action getAction(){
        return action;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ResponseMessage{");
        sb.append("message=").append(message);
        sb.append(", value=").append(value);
        sb.append(", action=").append(action);
        sb.append('}');
        return sb.toString();
    }

    public enum Action {
        SIGNUP, REQUEST_FRIENDSHIP, ACCEPT_FRIENDSHIP, DECLINE_FRIENDSHIP, LIST_FRIENDS
    }

}
