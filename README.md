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

## improvements

- send username in the body instead as parameter
- the controller has to return a something
- review the signature of the methods
- review the name of the test methods
- review the messages of the test methods

## profiles
specify
```aidl
java -jar myapp.jar --spring.profiles.active=dev
```
