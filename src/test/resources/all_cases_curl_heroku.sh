#!/usr/bin/env bash

# create users
curl -X POST "https://social-network-challenge.herokuapp.com/signup?username=username" -H  "accept: */*" -H  "X-Password: password"
echo
curl -X POST "https://social-network-challenge.herokuapp.com/signup?username=username1" -H  "accept: */*" -H  "X-Password: password"
echo
curl -X POST "https://social-network-challenge.herokuapp.com/signup?username=username2" -H  "accept: */*" -H  "X-Password: password"
echo
curl -X POST "https://social-network-challenge.herokuapp.com/signup?username=username3" -H  "accept: */*" -H  "X-Password: password"
echo
curl -X POST "https://social-network-challenge.herokuapp.com/signup?username=username4" -H  "accept: */*" -H  "X-Password: password"
echo
curl -X POST "https://social-network-challenge.herokuapp.com/signup?username=username5" -H  "accept: */*" -H  "X-Password: password"
echo

# send requests
curl -X POST "https://social-network-challenge.herokuapp.com/friendship/request?usernameFrom=username&usernameTo=username1" -H  "accept: */*" -H  "X-Password: password"
echo
curl -X POST "https://social-network-challenge.herokuapp.com/friendship/request?usernameFrom=username&usernameTo=username2" -H  "accept: */*" -H  "X-Password: password"
echo
curl -X POST "https://social-network-challenge.herokuapp.com/friendship/request?usernameFrom=username&usernameTo=username3" -H  "accept: */*" -H  "X-Password: password"
echo

# send requests

curl -X POST "https://social-network-challenge.herokuapp.com/friendship/request?usernameFrom=username1&usernameTo=username" -H  "accept: */*" -H  "X-Password: password"
echo
curl -X POST "https://social-network-challenge.herokuapp.com/friendship/request?usernameFrom=username2&usernameTo=username" -H  "accept: */*" -H  "X-Password: password"
echo
curl -X POST "https://social-network-challenge.herokuapp.com/friendship/request?usernameFrom=username3&usernameTo=username" -H  "accept: */*" -H  "X-Password: password"
echo

# list requests
curl -X GET "https://social-network-challenge.herokuapp.com/friendship/list?username=username" -H  "accept: */*" -H  "X-Password: password"
echo

# accept requests
curl -X POST "https://social-network-challenge.herokuapp.com/friendship/accept?usernameFrom=username1&usernameTo=username" -H  "accept: */*" -H  "X-Password: password"
echo
curl -X POST "https://social-network-challenge.herokuapp.com/friendship/accept?usernameFrom=username2&usernameTo=username" -H  "accept: */*" -H  "X-Password: password"
echo
curl -X POST "https://social-network-challenge.herokuapp.com/friendship/accept?usernameFrom=username3&usernameTo=username" -H  "accept: */*" -H  "X-Password: password"
echo

# list requests
curl -X GET "https://social-network-challenge.herokuapp.com/friendship/list?username=username" -H  "accept: */*" -H  "X-Password: password"
echo
curl -X GET "https://social-network-challenge.herokuapp.com/friendship/list?username=username1" -H  "accept: */*" -H  "X-Password: password"
echo
curl -X GET "https://social-network-challenge.herokuapp.com/friendship/list?username=username2" -H  "accept: */*" -H  "X-Password: password"
echo
curl -X GET "https://social-network-challenge.herokuapp.com/friendship/list?username=username3" -H  "accept: */*" -H  "X-Password: password"
echo
