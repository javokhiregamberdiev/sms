package uz.student.sms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
public class StudentManagementSystemApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(StudentManagementSystemApplication.class, args);
        Environment env = applicationContext.getEnvironment();
        logApplicationStartup(env);
    }

    private static void logApplicationStartup(Environment env) {
        log.info("""
                        \n----------------------------------------------------------
                        \tApplication name :{}
                        \tSwagger URL: {}
                        \tJava version: {}
                        \tTimezone: {}
                        \tProfile(s): \t{}
                        ----------------------------------------------------------""",
                env.getProperty("spring.application.name"),
                env.getProperty("application.serverUrl") + "/swagger-ui/index.html",
                env.getProperty("java.version"),
                env.getProperty("user.timezone"),
                env.getActiveProfiles());
    }

}