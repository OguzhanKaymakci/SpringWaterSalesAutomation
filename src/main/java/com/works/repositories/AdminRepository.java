package com.works.repositories;

import com.works.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;


public interface AdminRepository extends JpaRepository<Admin,Long> {


    Optional<Admin> findByEmailEqualsIgnoreCase(String email);





}
