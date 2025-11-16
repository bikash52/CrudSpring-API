package com.decfourapi.service;

import com.decfourapi.entity.Registration;
import com.decfourapi.payload.RegistrationDto;
import com.decfourapi.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public RegistrationDto saveRegistration(RegistrationDto registrationDto){
        //Convert to Entity from Dto
        Registration registration=new Registration();
        registration.setName(registrationDto.getName());
        registration.setEmailId(registrationDto.getEmailId());
        registration.setMobile(registrationDto.getMobile());
        Registration savedReg=registrationRepository.save(registration);

        //Convert entity to dto
        RegistrationDto savedRegDto=new RegistrationDto();
        savedRegDto.setName(savedReg.getName());
        savedRegDto.setEmailId(savedReg.getEmailId());
        savedRegDto.setMobile(savedReg.getMobile());
        return savedRegDto;
    }

    public void deleteRegistration(long id) {
        registrationRepository.deleteById(id);
    }

    public void updateRegistration(long id, Registration registration) {
        Optional<Registration> opReg = registrationRepository.findById(id);
        Registration reg=opReg.get();
        reg.setName(registration.getName());
        reg.setEmailId(registration.getEmailId());
        reg.setMobile(registration.getMobile());
        registrationRepository.save(reg);
    }

    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    public Registration getRegistrationById(long id) {
        Optional<Registration> opReg = registrationRepository.findById(id);
        Registration reg=opReg.get();
        return reg;
    }
}
