![social-networks](./_media/social-networks.png)

# social-network-challenge

We need your help migrating a legacy social network service.

This service have users that can friend other users and offers an HTTP API to do so.

## run springboot app
```
gradle wrapper
./gradlew build
./gradlew build bootRun
```

or run fat jar
```
java -jar build/libs/friends-rest-service-0.1.0.jar
```

## Dockerize the app: create a docker image and run the project from command line
From the project root folder exec the commands to create a docker image and run it:

for macosx start the docker daemon
```bash
killall Docker && open /Applications/Docker.app
```

then
```bash
docker build -f docker/Dockerfile . -t friends
docker run -p 8080:8080 friends
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
```

to clean docker images
```
docker images
docker rmi PID
``` 

### Tips

closing ports
```
sudo lsof -i :8888
sudo kill -9 PID
```

## improvements

- send username in the body instead as parameter,
- send usernames as path paremeters and between the relations of them: 
localhost:8080/friendship/johndoe/requestFriendshipTo/robert
localhost:8080/friendship/robert/acceptFriendshipTo/johndoe
localhost:8080/friendship/robert/declineFriendshipTo/johndoe
localhost:8080/friendship/johndoe/listFriends
- the controller has to return a something - done
- the once the user has login you can use the oath token to login
- review the signature of the methods
- review the name of the test methods
- review the messages of the test methods
- isolate interfaces for query and for command (save) - done
- don't use the user object in the front layer and others. USer VO as argument in the methods
- review the tests ignore
- create swagger doc

## profiles

specify
```
java -jar myapp.jar --spring.profiles.active=dev
```

![ddd-building-blocks](./_media/ddd-building-blocks.png)