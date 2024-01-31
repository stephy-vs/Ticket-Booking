package com.TicketBooking.InstitutionRegistration;

import com.TicketBooking.InstitutionRegistration.InstitutionDTO.InstitutionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InstitutionService {

    @Autowired
    InstitutionRepo institutionRepo;
    public InstitutionDTO eduDetails(Institution institution) {
        try {
             Institution institution1= institutionRepo.save(institution);
            return new InstitutionDTO(institution.getId(), institution1.getName(),institution1.getName(), institution.getTeachers(), institution1.getStudent(),institution1.getBookDate(), institution1.getTotal());

        }catch (Exception e){
            e.printStackTrace();
        }
        return new InstitutionDTO(0,null,null,null,null,null,null);
    }

}
