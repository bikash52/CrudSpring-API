package com.decfourapi.service;

import com.decfourapi.entity.Registration;
import com.decfourapi.payload.RegistrationDto;
import com.decfourapi.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;
    private ModelMapper modelMapper;
    public RegistrationService(RegistrationRepository registrationRepository, ModelMapper modelMapper) {
        this.registrationRepository = registrationRepository;
        this.modelMapper = modelMapper;
    }

    public RegistrationDto saveRegistration(RegistrationDto registrationDto){
        //Convert to Entity from Dto
        Registration registration=convertDtoToEntity(registrationDto);
        Registration savedReg=registrationRepository.save(registration);
        //Convert entity to dto
        RegistrationDto savedRegDto=convertEntityToDto(savedReg);
        return savedRegDto;
    }

    Registration convertDtoToEntity(RegistrationDto registrationDto){

        Registration registration=modelMapper.map(registrationDto,Registration.class);
        return registration;
    }

    RegistrationDto convertEntityToDto(Registration registration){
        RegistrationDto registrationDto=modelMapper.map(registration,RegistrationDto.class);
        return registrationDto;
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