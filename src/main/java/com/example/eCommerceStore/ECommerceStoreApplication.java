package com.example.eCommerceStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.transaction.Transactional;

@SpringBootApplication
@Transactional
public class ECommerceStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceStoreApplication.class, args);
	}

}
