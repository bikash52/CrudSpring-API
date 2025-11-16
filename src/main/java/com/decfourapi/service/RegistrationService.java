package com.decfourapi.service;

import com.decfourapi.entity.Registration;
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

    public void saveRegistration(Registration registration){
        registrationRepository.save(registration);
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
