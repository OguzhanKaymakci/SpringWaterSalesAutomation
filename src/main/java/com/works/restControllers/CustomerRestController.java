package com.works.restControllers;

import com.works.entities.Customers;
import com.works.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/water")
public class CustomerRestController {

    final CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/cussave")

    public ResponseEntity save(@RequestBody @Valid Customers customer){
        return customerService.insert(customer);
    }

    @GetMapping("/cuslist")

    public ResponseEntity list(){
        return customerService.list();
    }

}
