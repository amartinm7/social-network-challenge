package com.schibsted.spain.friends;

import com.schibsted.spain.friends.service.PersistenceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FriendsApplication {
  private static final Logger logger = LoggerFactory.getLogger(FriendsApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(FriendsApplication.class, args);
  }
}
