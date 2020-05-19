curl -X POST http://localhost:8080/actuator/loggers/com.bolly.springboot.controller \
-H "Content-Type: application/vnd.spring-boot.actuator.v2+json;charset=UTF-8" \
--data '{"configuredLevel":"debug"}'