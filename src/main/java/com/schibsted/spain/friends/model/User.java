package com.schibsted.spain.friends.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final String name;
    private final String password;

    private User(String name, String password){
        this.name = name;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("name='").append(name).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static class Builder{

        private String name;
        private String password;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        /**
         * Username must be unique, from 5 to 10 alphanumeric characters.
         * Password from 8 to 12 alphanumeric characters.
         */
        private void validate(){
            final List<String> errorMessages = new ArrayList<>();
            if(name == null){
                errorMessages.add("The 'username' parameter is null.");
            }
            if(name.length() < 5 || name.length() > 10){
                errorMessages.add(String.format("The length of the'username' parameter has to be between 5 and 10: current %s.",name.length()));
            }
            if (!name.matches("[a-zA-Z0-9]*")){
                errorMessages.add(String.format("The 'username' parameter contains invalid characters. Only alphanumeric are valid characters: current %s.",name.length()));
            }
            if(password == null){
                errorMessages.add("The 'password' parameter is null.");
            }
            if(password.length() < 8 || password.length() > 12){
                errorMessages.add(String.format("The length of the 'password' parameter has to be between 8 and 12: current %s.",password.length()));
            }
            if (!password.matches("[a-zA-Z0-9]*")){
                errorMessages.add(String.format("The 'password' parameter contains invalid characters. Only alphanumeric are valid characters: current %s.",password.length()));
            }
            if (errorMessages.size() > 0){
                throw new IllegalArgumentException(String.join("|| ", errorMessages));
            }
        }

        public User build(){
            validate();
            return new User(name,password);
        }
    }
}
