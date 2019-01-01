# sh bash

# create users
curl -X POST "http://localhost:8080/signup?username=username" -H  "accept: */*" -H  "X-Password: password"
echo
curl -X POST "http://localhost:8080/signup?username=username1" -H  "accept: */*" -H  "X-Password: password"
echo
curl -X POST "http://localhost:8080/signup?username=username2" -H  "accept: */*" -H  "X-Password: password"
echo
curl -X POST "http://localhost:8080/signup?username=username3" -H  "accept: */*" -H  "X-Password: password"
echo