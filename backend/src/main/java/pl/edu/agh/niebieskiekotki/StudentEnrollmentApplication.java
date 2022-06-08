package pl.edu.agh.niebieskiekotki;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@RequestMapping("app")
public class StudentEnrollmentApplication {


    @Value("${app.serverPath}")
    static private String serverPath;
    public static void main(String[] args) {
        System.out.println(serverPath);
        SpringApplication.run(StudentEnrollmentApplication.class, args);
    }
}
