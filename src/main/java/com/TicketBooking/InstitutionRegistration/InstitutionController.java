package com.TicketBooking.InstitutionRegistration;

import com.TicketBooking.InstitutionRegistration.InstitutionDTO.InstitutionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "institu")

public class InstitutionController {

    @Autowired
    InstitutionService institutionService;


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/eduData")
    public InstitutionDTO eduDetails(@RequestBody Institution institution){
        return institutionService.eduDetails(institution);
    }


}
