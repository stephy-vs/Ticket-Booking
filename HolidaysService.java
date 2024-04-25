package com.TicketBooking.holidays;

import com.TicketBooking.DateTimeSlot.showTime.ShowTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HolidaysService {

    @Autowired
    private HolidayesRepo holidayesRepo;
    public HoliDays addHolidays(HoliDays holiDays) {
        try {
            HoliDays holiDays1 = holidayesRepo.save(holiDays);
            return holiDays1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ResponseEntity<List<HoliDays>> getAllHoliDays() {
        try {
            return new ResponseEntity<>(holidayesRepo.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }


    public HoliDays updateHolidaysById(Integer id, HoliDays updateDays) {
        HoliDays existingData = holidayesRepo.findById(id).orElse(null);
        if (existingData== null){
            return null;
        }else {
            existingData.setName(updateDays.getName());
            existingData.setDate(updateDays.getDate());
            return holidayesRepo.save(existingData);
        }
    }
}
