package com.schibsted.spain.friends;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FriendsApplication {

  private static final Logger logger = LoggerFactory.getLogger(FriendsApplication.class);

  public static void main(String[] args) {
    logger.info("Welcome to the FriendsApplication...");
    SpringApplication.run(FriendsApplication.class, args);
  }

}
