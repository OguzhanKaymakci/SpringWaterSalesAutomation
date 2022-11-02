package com.works.repositories;

import com.works.entities.Admin;
import com.works.entities.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customers,Long> {
    Optional<Customers> findByEmailEqualsIgnoreCase(String email);
}
