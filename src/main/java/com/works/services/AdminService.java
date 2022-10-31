package com.works.services;

import com.works.config.JwtUtil;
import com.works.entities.Admin;
import com.works.entities.Login;
import com.works.repositories.AdminRepository;
import com.works.utils.Enums;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class AdminService implements UserDetailsService {
    final AdminRepository adminRepository;
    final AuthenticationManager authenticationManager;
    final JwtUtil jwtUtil;
    final HttpSession httpSession;

    public AdminService(AdminRepository adminRepository, AuthenticationManager authenticationManager, JwtUtil jwtUtil, HttpSession httpSession) {
        this.adminRepository = adminRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.httpSession = httpSession;
    }


    public ResponseEntity<Map<Enums,Object>> register(Admin admin){
    Map<Enums,Object> hm = new LinkedHashMap<>();
    Optional<Admin> optionalAdmin=adminRepository.findByEmailEqualsIgnoreCase(admin.getEmail());


        Admin ad= adminRepository.save(admin);
    if (!optionalAdmin.isPresent()){
        admin.setPassword(encoder().encode(admin.getPassword()));
        Admin admin1= adminRepository.save(admin);
        hm.put(Enums.result,admin);
        hm.put(Enums.status,true);
        return new ResponseEntity<>(hm, HttpStatus.OK);
    }else {
        hm.put(Enums.status,false);
        hm.put(Enums.message,"user has already registered");
        return new ResponseEntity<>(hm,HttpStatus.ALREADY_REPORTED);
    }
    }


    public ResponseEntity<Map<Enums,Object>> login(Login login){
        Map<Enums,Object> hm= new LinkedHashMap<>();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    login.getUserName(),login.getPassword()));
            UserDetails userDetails=loadUserByUsername(login.getUserName());
            String jwt= jwtUtil.generateToken(userDetails);
            Admin admin= (Admin) httpSession.getAttribute("admin");

            hm.put(Enums.status,true);
            hm.put(Enums.message,"login successful");
            hm.put(Enums.result,admin);
            hm.put(Enums.jwt,jwt);
            return new ResponseEntity<>(hm,HttpStatus.OK);

    }catch (Exception ex){
        hm.put(Enums.status,false);
        hm.put(Enums.error,ex.getMessage());
        return new ResponseEntity<>(hm,HttpStatus.OK);
        }
    }

    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> optionalAdmin = adminRepository.findByEmailEqualsIgnoreCase(username);

        if (optionalAdmin.isPresent()){
            Admin ad= optionalAdmin.get();
            UserDetails userDetails = new User(
                    ad.getEmail(),
                    ad.getPassword(),
                    ad.isEnabled(),
                    ad.isTokenExpired(),
                    true,
                    true,
                    admin(ad.getRoles());



            )
        }
    }
}
