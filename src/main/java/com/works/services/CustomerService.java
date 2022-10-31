package com.works.services;

import com.works.entities.Customers;
import com.works.repositories.CustomerRepository;
import com.works.utils.Enums;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public ResponseEntity<Map<Enums,Object>> list(){
        Map<Enums,Object> hm= new LinkedHashMap<>();
        List<Customers> customerList = customerRepository.findAll();
        hm.put(Enums.result,customerList);
        hm.put(Enums.status, HttpStatus.OK);

        return new ResponseEntity<>(hm, HttpStatus.OK);
    }


    public ResponseEntity<Map<Enums,Object>> insert(Customers customer){
        Map<Enums,Object>hm= new LinkedHashMap<>();
        Customers p= customerRepository.save(customer);
        hm.put(Enums.status,true);
        hm.put(Enums.result,customer);
        return new ResponseEntity<>(hm, HttpStatus.OK);
    }
}
