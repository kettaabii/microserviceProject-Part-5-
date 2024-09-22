package org.construction.userservice.controller;

import org.construction.userservice.dto.LoginRequestDto;
import org.construction.userservice.enums.Erole;
import org.construction.userservice.model.Customer;
import org.construction.userservice.model.Person;
import org.construction.userservice.security.JwtAuth;
import org.construction.userservice.service.AdminService;
import org.construction.userservice.service.CustomerService;
import org.construction.userservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {


    @Autowired
    private PersonService personService;
    @Autowired
    private AuthenticationManager authenticationManager;




    @PostMapping("/signup")
    public Person register(@RequestBody Person person ) {
     return personService.addPerson(person);
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );
        Person person1 = personService.findPersonByUsername(loginRequestDto.getUsername());
        Erole role= person1.getRole();
        String token = JwtAuth.generateToken(loginRequestDto.getUsername(),role);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

}
