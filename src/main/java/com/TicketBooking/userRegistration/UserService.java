package com.TicketBooking.userRegistration;

import com.TicketBooking.userRegistration.DTO.UserResponse;
import com.TicketBooking.InstitutionRegistration.InstitutionRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    InstitutionRepo institutionRepo;

    public UserResponse userDetail(User user) {
        try {
            User saveUser = userRepo.save(user);
            return new UserResponse(saveUser.getId(), saveUser.getTotal(), saveUser.getName(), saveUser.getPhoneNumber(),saveUser.getAdult(),saveUser.getChild(),saveUser.getBookDate());
        }catch (Exception e){
            e.printStackTrace();
        }
        return new UserResponse(0,0,null,null,null,null,null);
    }




}
