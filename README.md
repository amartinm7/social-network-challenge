![social-networks](./_media/social-networks.png)


#

Some notes for the interviewers, what I would expect from them:
- First for all, appreciate to the candidate for the effort to make/finish the challenge. Say thank you.
- Stay positive, give a constructive feedback, always in a respectful way.
- There are a lot solutions, this challenge is an approach of many others. Be positive.
- You are looking for a partner, a person who can join your team and help to be better. Don't be an ass.
- Don't put the candidate under pressure. Relax and take it easy.
- Be smart and treat to the candidate as you wish to be treated.
- Spread your enthusiasm, explain the open position and the work to do in a enthusiastic way.
- laugh a lot, is for free! 
     
# Considerations, staying/being clear
- Taking into account the spending time to achieve the challenge, the requirements and so on, this is one approach and fits into the requirements. 
- It doesn't mean that it's the real truth. You can implement another approaches and it will be good too.
- Everything can be improved of course, but this is an stating point for a tech talk, so be positive and get the best of it.
- Think what we can achieve in the future from here instead of criticizing the work. This is not a competition to know who is better.

     
# social-network-challenge

This service have users that can ask for friendship other users and offers an HTTP API to do so.

## Deployed app in the cloud
Swagger:
* https://social-network-challenge.herokuapp.com/swagger-ui.html

Single Page Application (status=under construction)
* https://social-network-challenge.herokuapp.com/index.html 

## ![springboot](./_media/icons/springboot.png) run springboot app
To start, install the gradle wrapper is you didn't do it previously and boot the app: 
```bash
gradle wrapper
./gradlew build
./gradlew bootRun
```

or run the application using the java command line, using the fat jar:
```bash
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
```bash
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
```

to clean the docker images from the system:
```bash
docker images
docker rmi PID
``` 

Once you have the dockerized app is really easy bring it to the cloud. You look for a cloud provider to deploy it and host it.

## ![swagger](./_media/icons/swagger.png) Swagger
You can see the swagger documentation in the following url:
- http://localhost:8080/swagger-ui.html


## Single Page Application (status=under construction)
Once the app is runnning you can visit the next url to manage the REST API.
* http://localhost:8080/index.html

You can run the curl files to test the app with users. 
Once the app is running you can run from the command line of the root project:
```bash
sh ./src/test/resources/all_cases_curl.sh
```

## Test code covarage using Jacoco
To see the report with the code coverage for the testing, simply open the report: 
- build/reports/jacoco/test/html/index.html

## Code Quality coverage using checkstyle and findbugs
To see the reports simply open the report: 
- build/reports/checkstyle/main.html
- build/reports/checkstyle/test.html

You can execute the report on demand:
```bash
./gradlew check
```

## actuator for checking the availability of the system
Under the next urls you can see how is the system running:
* http://localhost:8080/actuator/health
* http://localhost:8080/actuator/info

## Improvements

### New URIs with path params for the rest api (status=implemented)
Improving the rest api signature sending usernames as path parameters (currently implemented) :
```
http://localhost:8080/friendship/{usernameFrom}/request/{usernameTo}
http://localhost:8080/friendship/{usernameFrom}/accept/{usernameTo}
http://localhost:8080/friendship/{usernameFrom}/decline/{usernameTo}
http://localhost:8080/friendship/{username}/listFriends
http://localhost:8080/friendship/{username}/listPending
```

### implement cache in the front-controller (status=idea)
We can implement a cache module to cache the request. This is the URI and the headers. 
If the request it's cached then we can get the data from there. We can try to a Memcache or REDIS service as cache.
The idea behind is to use a cache service as CloudFront is in the AWS ecosystem. 
 
### implement versioning (status=implemented)
We can add a versioning in the api to handle the different versions of the api while this API is evolving.
For instance:
```
http://localhost:8080/v1/friendship/{usernameFrom}/request/{usernameTo}
http://localhost:8080/v2/friendship/{usernameFrom}/request/{usernameTo}
http://localhost:8080/v1/swagger-ui.html
```
For achieving this we can change the context-path property in the application.yml file. 
At this moment you can specify the profile when you run springboot for testing this behaviour.

### Deployed app in heroku with gradle (status=implemented)
At this moment Heroku provides a functionality to deploy the projects under git. In this case use the build.gradle 
as pipeline to deploy the application in the cloud. You can see the application running in the next url:
 
* https://social-network-challenge.herokuapp.com/swagger-ui.html

### Deploy app in heroku with docker (status=idea)
At this moment Heroku provides a functionality to deploy dockerized projects under git. There are a heroku.yml pipeline to do it. 

### More room to improve (status=idea)
* implement Circuit Breaker pattern to avoid the degradation of the system, in the case this REST API was consuming another REST API.
* enable CSRF and XSS features from Spring to avoid this kind of attacks.
* implement JWT feature to authorization things.
* implement a concurrency mechanism to avoid updating the store at the same time for the same user.
* implement a mechanism to log the system: kibana + elastic search + logstash. More info https://www.elastic.co/

## DDD: Domain-Driven Design
The application follows some rules of the DDD.
The application is divided in four modules/layers:
* interface/presentation layer: show the information to the user and capture the user events.
* application layer: define the use cases. Coordinates the domain objects to resolve problems.
* domain layer: resolve the specific problems for this domain.
* infrastructure layer: isolate the technology agreements for the system. 
Contains the implementations for email services, persistence services, transaction services and so on. 

![ddd-building-blocks](./_media/ddd4.png)

https://devexperto.com/domain-driven-design-2/



![ddd-building-blocks](./_media/ddd-building-blocks.png)

## Some useful Tips

## Closing ports
Closing ports, useful when the service is running and you want to kill it.
```
sudo lsof -i :8080
sudo kill -9 PID
```

## Using an local httpServer to develop static content
Install and run the local server to see the changes refreshed in the browser automatically.
```bash
npm install -g live-server
live-server
```
More info https://www.npmjs.com/package/live-server


#
 
