package com.schibsted.spain.friends.model;

import java.util.HashSet;
import java.util.Set;

public class FriendShip {
    private final Status status;
    private final User userFrom;
    private final User userTo;
    private final Set<User> users;

    private FriendShip(User userFrom, User userTo, Status status){
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.status = status;
        this.users = new HashSet<User>();
        this.users.add(userFrom);
        this.users.add(userTo);
    }

    public boolean isAlreadyFriend(){
        return Status.ACCEPTED.equals(this.getStatus());
    }

    public enum Status{
        ACCEPTED, PENDING;
    }

    public static class Builder {
        private Status status;
        private User userFrom;
        private User userTo;

        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder setUserFrom(User userFrom) {
            this.userFrom = userFrom;
            return this;
        }

        public Builder setUserTo(User userTo) {
            this.userTo = userTo;
            return this;
        }

        public FriendShip build(){
            return new FriendShip(userFrom, userTo, status);
        }
    }

    public Status getStatus() {
        return status;
    }

    public User getFriend(User user) {
        final Set<User> friends = new HashSet<>(this.users);
        friends.remove(user);
        return friends.iterator().next();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendShip that = (FriendShip) o;

        if (status != that.status) return false;
        return users.equals(that.users);
    }

    @Override
    public int hashCode() {
        int result = status.hashCode();
        result = 31 * result + users.hashCode();
        return result;
    }

    public static FriendShip newInstanceRequest(User userFrom, User userTo){
        return new FriendShip.Builder()
                .setUserFrom(userFrom)
                .setUserTo(userTo)
                .setStatus(Status.PENDING)
                .build();
    }

    public static FriendShip newInstanceAccept(User userFrom, User userTo){
        return new FriendShip.Builder()
                .setUserFrom(userFrom)
                .setUserTo(userTo)
                .setStatus(Status.ACCEPTED)
                .build();
    }
}
