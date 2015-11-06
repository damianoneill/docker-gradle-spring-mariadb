package com.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Load db bean.
 */
@Configuration
public class LoadDbBean {
    @Autowired
    private CustomerRepository repository;

    private static Logger log = LoggerFactory.getLogger(LoadDbBean.class);

    /**
     * Start command line runner.
     *
     * @return the command line runner
     */
    @Bean
    public CommandLineRunner start() {
        return (args) -> {
            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("Michelle", "Dessler"));

            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
        };
    }
}
