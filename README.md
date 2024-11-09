# Implementing Spring Actuator in SB 
## 1. Import Actuator dependency in pom.xml file
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

## 2. Add configuration in application.yml
```yml
management:
  endpoints:
    web:
      exposure:
        include: *
  endpoint:
    health:
      show-details: always
```

## 3. Common Actuator endpoints
- ***/actuator/health***: Displays the health status of the application.
- ***/actuator/info***: Displays arbitrary application information.
- ***/actuator/metrics***: Shows ‘metrics’ information for the current application.
- ***/actuator/prometheus***: Provides metrics in a format that Prometheus can scrape.
- ***/actuator/loggers***: Shows and modifies the logging level of your application at runtime.
- ***/actuator/env***: Displays properties from the `Environment`.
- ***/actuator/beans***: Displays a complete list of all Spring beans in your application.
- ***/actuator/threaddump***: Provides a thread dump of the application.
- ***/actuator/httptrace***: Displays HTTP request-response exchanges (limited to the last 100 requests by default).
- ***/actuator/shutdown***: Allows shutting down the application gracefully.

## 4. Optional: Adding custom health indicators

## 5. Optional: Customizing Actuator endpoints
Create custom endpoints by implementing the `Endpoint` interface

## 6. Securing Actuator endpoints
### Import Spring Security dependency
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
