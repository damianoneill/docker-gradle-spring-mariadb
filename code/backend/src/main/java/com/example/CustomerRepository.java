package com.example;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * The interface Customer repository.
 */
@RepositoryRestResource
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    /**
     * Find by last name list.  For e.g.
     * <p>
     * http://192.168.99.100:8080/customers/search/findByLastName?lastName=Palmer
     *
     * @param lastName the last name
     * @return the list
     */
    List<Customer> findByLastName(@Param("lastName") String lastName);
}
