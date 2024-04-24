package com.TicketBooking.DateTimeSlot.showTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/stime")
@CrossOrigin(origins = "http://localhost:8081")
public class ShowTimeController {
    @Autowired
    private ShowTimeService showTimeService;
    @PostMapping(path = "/addstime")
    public ResponseEntity<?> addShowTime(@RequestBody ShowTimeDTO showTimeDTO){
        ShowTime showTime = showTimeService.addShowTime(showTimeDTO);
        return ResponseEntity.ok(showTime);
    }

    @PutMapping(path = "/updateShow/{id}")
    public ResponseEntity<ShowTime> updateShowTime(@PathVariable("id")Integer id,@RequestBody ShowTimeDTO updateShowTimeDTO){
        ShowTime updatedShowTime = showTimeService.updateShowTime(id,updateShowTimeDTO);

        if (updatedShowTime!=null){
            return ResponseEntity.ok(updatedShowTime);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
