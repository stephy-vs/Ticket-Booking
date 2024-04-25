package com.TicketBooking.holidays;

import com.TicketBooking.DateTimeSlot.showTime.ShowTime;
import com.TicketBooking.DateTimeSlot.showTime.ShowTimeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "holidays")
public class HolidaysController {

    @Autowired
    private HolidaysService holidaysService;

    @PostMapping(path = "addDayData")
    public ResponseEntity<?> addHoliDays(@RequestBody HoliDays holiDays){
        HoliDays holiDays1 = holidaysService.addHolidays(holiDays);
        return ResponseEntity.ok(holiDays1);
    }

    @GetMapping(path = "/getDayList")
    public ResponseEntity<List<HoliDays>> getAllHoliDays(){

        return holidaysService.getAllHoliDays();
    }

    @PutMapping(path = "/updateDate/{id}")
    public ResponseEntity<?> updateHolidaysById(@PathVariable("id") Integer id,
                                                @RequestBody HoliDays updateDays){
        HoliDays existingDays = holidaysService.updateHolidaysById(id,updateDays);
        if (existingDays!=null){
            return ResponseEntity.ok(existingDays);
        }else {
            return ResponseEntity.notFound().build();
        }

    }

}
