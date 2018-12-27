package com.schibsted.spain.friends.model;

public class FriendShip {
    private final Status status;
    private final User userFrom;
    private final User userTo;

    private FriendShip(User userFrom, User userTo, Status status){
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.status = status;
    }

    public boolean isFriend(){
        return Status.ACCEPTED.equals(this.getStatus());
    }

    public static enum Status{
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

    public User getUserFrom() {
        return userFrom;
    }

    public User getUserTo() {
        return userTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendShip that = (FriendShip) o;

        if (status != that.status) return false;
        if (!userFrom.equals(that.userFrom)) return false;
        return userTo.equals(that.userTo);
    }

    @Override
    public int hashCode() {
        int result = status.hashCode();
        result = 31 * result + userFrom.hashCode();
        result = 31 * result + userTo.hashCode();
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
