package com.projects.socialmedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SocialmediaApplication {

  public static void main(String[] args) {
    SpringApplication.run(SocialmediaApplication.class, args);
  }

}
