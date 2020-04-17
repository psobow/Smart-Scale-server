package com.sobow.smartscale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartscaleApplication
{

  public static void main(String[] args)
  {
    SpringApplication.run(SmartscaleApplication.class, args);
    // h2 database available under URL: http://localhost:8080/h2-console/
    // config: JDBC URL = jdbc:h2:mem:testdb
    // Swagger: http://localhost:8080/swagger-ui.html
  }

}
