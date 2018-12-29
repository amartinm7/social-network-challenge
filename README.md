![social-networks](./_media/social-networks.png)

# social-network-challenge

This service have users that can ask for friendship other users and offers an HTTP API to do so.

## ![springboot](./_media/icons/springboot.png) run springboot app
```
gradle wrapper
./gradlew build
./gradlew bootRun
```

or run the application using the java command line, using the fat jar:
```
java -jar -Dspring.profiles.active=dev build/libs/friends-rest-service-0.1.0.jar
```
you can specify the profile using the -Dspring.profiles.active parameter. If you don't specify any, by default is 'dev' in our case.


## ![swagger](./_media/icons/docker.png) Dockerize the app
From the project root folder exec the commands to create a docker image and run it:

for macosx start the docker daemon
```bash
killall Docker && open /Applications/Docker.app
```

then execute the next commands in order to create the docker image and run it:
```bash
docker build -f docker/Dockerfile . -t friends
docker run -p 8080:8080 friends
```

to stop the application first we have to stop the docker process and then kill the docker process:
```
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
```

to clean the docker images from the system:
```
docker images
docker rmi PID
``` 

Once you have the dockerized app is really easy bring it to the cloud. You look for a cloud provider to deploy it and host it.


## ![swagger](./_media/icons/swagger.png) Swagger
You can see the swagger documentation in the following url:
- http://localhost:8080/swagger-ui.html

## Test code covarage using Jacoco
To see the report with the code coverage for the testing, simply open the report: 
- build/reports/jacoco/test/html/index.html

## Code Quality coverage using checkstyle and findbugs
To see the reports simply open the report: 
- build/reports/checkstyle/main.html
- build/reports/checkstyle/test.html

You can execute the report on demand:
```
./gradlew check
```

### Some useful Tips

closing ports
```
sudo lsof -i :8080
sudo kill -9 PID
```

## improvements

send usernames as path paremeters and between the relations of them: 

localhost:8080/friendship/johndoe/requestFriendshipTo/robert
localhost:8080/friendship/robert/acceptFriendshipTo/johndoe
localhost:8080/friendship/robert/declineFriendshipTo/johndoe
localhost:8080/friendship/johndoe/listFriends

https://social-network-challenge.herokuapp.com/swagger-ui.html

![ddd-building-blocks](./_media/ddd-building-blocks.png)