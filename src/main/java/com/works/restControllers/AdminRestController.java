package com.works.restControllers;

import com.works.entities.Admin;
import com.works.entities.Login;
import com.works.services.AdminService;
import com.works.utils.Enums;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminRestController {


    final AdminService adminService;

    public AdminRestController(AdminService adminService) {
        this.adminService = adminService;
    }
    @PostMapping("/register")
    public ResponseEntity<Map<Enums,Object>> register(@RequestBody @Valid Admin admin){
        return adminService.register(admin);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<Enums,Object>> login(@RequestBody @Valid Login login){
        return adminService.login(login);
    }
}
