package com.schibsted.spain.friends.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class User {

    private static final Logger logger = LoggerFactory.getLogger(User.class);

    private final String name;
    private final String password;
    private final Set<User> friends;
    private final Set<User> pendingFriends;

    private User(String name, String password){
        this.name = name;
        this.password = password;
        this.friends = new LinkedHashSet<>();
        this.pendingFriends = new LinkedHashSet<>();
    }

    public String getName(){
        return this.name;
    }
    public String getPassword(){
        return this.password;
    }

    @JsonIgnore
    public Collection<User> getFriendList() {
        return friends;
    }

    @JsonIgnore
    public Collection<User> getPendingFriendsList() {
        return pendingFriends;
    }

    public Collection<String> getFriends() {
        return friends.stream().map(user -> user.getName()).collect(Collectors.toList());
    }

    public Collection<String> getPendingFriends() {
        return pendingFriends.stream().map(user -> user.getName()).collect(Collectors.toList());
    }

    public Optional<User> requestFriendShip(User userTo) {
        if (this.friends.contains(userTo)){
            throw new IllegalArgumentException(String.format("The %s is already a friend.", userTo.getName()));
        }
        if (userTo.pendingFriends.contains(this)){
            throw new IllegalArgumentException(String.format("The %s has already a pending request for this friend %s.", userTo.getName(), this.getName()));
        }
        if (this.equals(userTo)){
            throw new IllegalArgumentException(String.format("You can't ask for friendship to yourself"));
        }
        boolean addedRequest = userTo.pendingFriends.add(this);
        logger.info("added request friendship from {} to {}? {}", this.getName(), userTo.getName(), addedRequest);
        return (addedRequest)? Optional.of(this) : Optional.empty();
    }
    private void validateRelationshipLists(User userTo){
        if (!this.pendingFriends.contains(userTo)) {
            throw new IllegalArgumentException(String.format("The %s is not in the list of pending friends.", userTo.getName()));
        }
        if (this.friends.contains(userTo)) {
            throw new IllegalArgumentException(String.format("The %s is already in the list of friends.", userTo.getName()));
        }
    }
    public Optional<User> acceptFriendShip(User userTo) {
        validateRelationshipLists(userTo);
        this.pendingFriends.remove(userTo);
        userTo.pendingFriends.remove(this);
        boolean addingTo = this.friends.add(userTo);
        boolean addingFrom = userTo.friends.add(this);
        logger.info("accepted friendship from {} to {}? {}", this.getName(), userTo.getName(), (addingTo && addingFrom));
        return (addingTo && addingFrom) ? Optional.of(this) : Optional.empty();

    }

    public Optional<User> declineFriendShip(User userTo) {
        validateRelationshipLists(userTo);
        boolean removed = this.pendingFriends.remove(userTo);
        logger.info("declined friendship from {} to {}? {}", this.getName(), userTo.getName(), removed);
        return (removed)? Optional.of(this) : Optional.empty();
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
        sb.append(", friends=").append(friends.stream().map(user -> user.getName()).collect( Collectors.joining( "," ) ));
        sb.append(", pendingFriends=").append(pendingFriends.stream().map(user -> user.getName()).collect( Collectors.joining( "," ) ));
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
            final Collection<String> errorMessages = new ArrayList<>();
            if(name == null){
                errorMessages.add("The 'username' parameter is null.");
            }
            if(name != null && (name.length() < 5 || name.length() > 10)){
                errorMessages.add(String.format("The length of the'username' parameter has to be between 5 and 10: current %s.",name.length()));
            }
            if (name != null && !name.matches("[a-zA-Z0-9]*")){
                errorMessages.add(String.format("The 'username' parameter contains invalid characters. Only alphanumeric are valid characters: current %s.",name.length()));
            }
            if(password == null){
                errorMessages.add("The 'password' parameter is null.");
            }
            if(password != null && (password.length() < 8 || password.length() > 12) ){
                errorMessages.add(String.format("The length of the 'password' parameter has to be between 8 and 12: current %s.",password.length()));
            }
            if (password != null && !password.matches("[a-zA-Z0-9]*")){
                errorMessages.add(String.format("The 'password' parameter contains invalid characters. Only alphanumeric are valid characters: current %s.",password.length()));
            }
            if (errorMessages.size() > 0){
                throw new IllegalArgumentException(String.join("; ", errorMessages));
            }
        }

        public User build(){
            validate();
            return new User(name,password);
        }
    }
}
