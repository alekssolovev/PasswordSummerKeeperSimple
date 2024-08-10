package org.springproject.passwordsummerkeepersimple;

import org.springframework.boot.SpringApplication;

public class TestPasswordSummerKeeperSimpleApplication {

    public static void main(String[] args) {
        SpringApplication.from(PasswordSummerKeeperSimpleApplication::main)/*.with(TestcontainersConfiguration.class)*/.run(args);
    }

}
