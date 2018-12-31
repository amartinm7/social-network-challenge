# sh bash

# create users
curl -X POST "http://localhost:8080/signup?username=username" -H  "accept: */*" -H  "X-Password: password"
curl -X POST "http://localhost:8080/signup?username=username1" -H  "accept: */*" -H  "X-Password: password"
curl -X POST "http://localhost:8080/signup?username=username2" -H  "accept: */*" -H  "X-Password: password"
curl -X POST "http://localhost:8080/signup?username=username3" -H  "accept: */*" -H  "X-Password: password"

# send requests
curl -X POST "http://localhost:8080/friendship/request?usernameFrom=username&usernameTo=username1" -H  "accept: */*" -H  "X-Password: password"
curl -X POST "http://localhost:8080/friendship/request?usernameFrom=username&usernameTo=username2" -H  "accept: */*" -H  "X-Password: password"
curl -X POST "http://localhost:8080/friendship/request?usernameFrom=username&usernameTo=username3" -H  "accept: */*" -H  "X-Password: password"

# list requests
curl -X GET "http://localhost:8080/friendship/list?username=username" -H  "accept: */*" -H  "X-Password: password"
