package com.cycle_saver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

//@Controller
@SpringBootApplication
//@ComponentScan("com.cycle_saver.repositories")
public class CycleSaver {
    public static void main(String[] args) {
        SpringApplication.run(CycleSaver.class, args);
    }
}
