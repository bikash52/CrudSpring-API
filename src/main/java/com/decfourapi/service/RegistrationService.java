package com.decfourapi.service;

import com.decfourapi.entity.Registration;
import com.decfourapi.exception.ResourceNotFound;
import com.decfourapi.payload.RegistrationDto;
import com.decfourapi.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        //Convert Dto to Entity
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

    public List<Registration> getAllRegistrations(int pageNo, int pageSize, String sortBy, String sortDir) {
        //use ternary operator for creating sort object
        Sort sort=sortDir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable page=PageRequest.of(pageNo, pageSize, sort);
        Page<Registration> records=registrationRepository.findAll(page);
        List<Registration> registrations=records.getContent();
        return registrations;
    }

    public Registration getRegistrationById(long id) {
      Registration registration = registrationRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFound("Registration not found with id: " + id));
      return registration;
    }

}