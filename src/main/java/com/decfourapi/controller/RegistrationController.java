package com.decfourapi.controller;

import com.decfourapi.entity.Registration;
import com.decfourapi.payload.RegistrationDto;
import com.decfourapi.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("api/v1/registration")
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    //http://localhost:8080/api/v1/registration
   @PostMapping
   public  ResponseEntity<RegistrationDto> addRegistration(@RequestBody RegistrationDto registrationDto){
        RegistrationDto r=registrationService.saveRegistration(registrationDto);
        return new ResponseEntity<>(r,HttpStatus.CREATED);
   }

    //Using query parameter
    //http://localhost:8080/api/v1/registration?id=3
//    @DeleteMapping
//    public String deleteRegistration(@RequestParam long id){
//        registrationService.deleteRegistration(id);
//        return "deleted";
//    }

    //Using path variable
    //http://localhost:8080/api/v1/registration/3
    @DeleteMapping("{id}")
    public String deleteRegistration(@PathVariable long id){
        registrationService.deleteRegistration(id);
        return "deleted";
    }


    //http://localhost:8080/api/v1/registration/3
    @PutMapping("{id}")
    public String updateRegistration(@PathVariable long id,@RequestBody Registration registration){
        registrationService.updateRegistration(id,registration);
        return "updated";
    }

    //http://localhost:8080/api/v1/registration
    @GetMapping
    public List<Registration> getAllRegistrations(){
        return registrationService.getAllRegistrations();
    }

    @GetMapping("/id/{id}")
    public Registration getRegistrationById(@PathVariable long id){
        return registrationService.getRegistrationById(id);
    }
}
