package org.oa.mindbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MindbookApplication {

    public static void main(String[] args) {
        SpringApplication.run(MindbookApplication.class, args);
    }

}
