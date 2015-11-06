package com.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The type Customer.
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"firstName", "lastName"})
@ToString(exclude = "id")
@SuppressWarnings("PMD")
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;

    /**
     * Instantiates a new Customer.
     *
     * @param firstName the first name
     * @param lastName  the last name
     */
    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

