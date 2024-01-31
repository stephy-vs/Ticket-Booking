package com.TicketBooking.userRegistration;

import com.TicketBooking.userRegistration.DTO.UserResponse;
import com.TicketBooking.userRegistration.User;
import com.TicketBooking.userRegistration.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "user")
public class UserController {
    @Autowired
    UserService userService;
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/userData")
    public ResponseEntity<UserResponse> userDetails(@RequestBody User user){
        UserResponse response = userService.userDetail(user);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


}
