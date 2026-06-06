package edu.ap.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.ap.spring.service.WebUserService;

@SpringBootApplication
public class BlockChainApplication implements CommandLineRunner {

    private final WebUserService userService;

    public BlockChainApplication(WebUserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BlockChainApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (!userService.usernameExists("admin")) {
            userService.save("admin", "admin", "ADMIN");
        }
    }
}