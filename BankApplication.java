package com.bank.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BankApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(BankApplication.class, args);
        // System.out.println(ctx);
    }
}