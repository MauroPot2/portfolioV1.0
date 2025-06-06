package com.mauropot.portfolio;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PortfolioBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioBackendApplication.class, args);
	}
@Bean
public CommandLineRunner testDB(DataSource dataSource) {
    return args -> {
        try (Connection conn = dataSource.getConnection()) {
            System.out.println("✅ Connessione al DB riuscita!");
            conn.createStatement().execute("SELECT 1");
        }
    };
}
}

