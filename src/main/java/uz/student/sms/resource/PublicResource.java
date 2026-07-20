package uz.student.sms.resource;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/public")
public class PublicResource {

    private final RestTemplate restTemplate;

    @GetMapping("/user-agent")
    public void userAgent() {
        while (true) {
            try {
//                Thread.sleep(1000);
                HttpHeaders headers = new HttpHeaders();
                headers.setBearerAuth("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5OTg5MDIyMjIyMjIiLCJhdXRoIjoiUk9MRV9PUEVSQVRPUiIsInVzZXJJZCI6Nzc0NTIsImNvbXBhbnlJZCI6MSwiYnJhbmNoSWQiOjEsImxhbmd1YWdlIjoicnUiLCJuYW1lIjoi0KLQtdGB0YIg0KLQtdGB0YIiLCJpYXQiOjE3NjQ4MzE1NDYsImV4cCI6MTc2NzQyMzU0Nn0.P9tFNwF5y6bxlJv7fNHcpeh5jAidFO3V9L9jaKz_PFsxKXeyLKCdD7WK9pdbZ1qwesKSIbq_sT5wJ5q01A2F1A");
                restTemplate.getForEntity("http://127.0.0.1:8080/api/public/v1/commons/user-agent", String.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
