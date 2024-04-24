package com.TicketBooking.DateTimeSlot.showTime;

import com.TicketBooking.DateTimeSlot.showTime.ShowTime;
import com.TicketBooking.DateTimeSlot.showTime.ShowTimeDTO;
import com.TicketBooking.DateTimeSlot.showTime.ShowTimeRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowTimeService {
    @Autowired
    private ShowTimeRepo showTimeRepo;
    public ShowTime addShowTime(ShowTimeDTO showTimeDTO) {
        ShowTime showTime = new ShowTime();
        showTime.setId(showTimeDTO.getId());
        showTime.setStartTime(showTimeDTO.getStartTime());
        showTime.setEndTime(showTimeDTO.getEndTime());
        showTime.setCapacity(showTimeDTO.getCapacity());
        return showTimeRepo.save(showTime);
    }

    public ShowTime updateShowTime(Integer id, ShowTimeDTO updateShowTimeDTO) {
        ShowTime existingShowTime = showTimeRepo.findById(id).orElse(null);
        if (existingShowTime==null){
            return null;
        }
        existingShowTime.setStartTime(updateShowTimeDTO.getStartTime());
        existingShowTime.setEndTime(updateShowTimeDTO.getEndTime());
        existingShowTime.setCapacity(updateShowTimeDTO.getCapacity());
        return showTimeRepo.save(existingShowTime);
    }

    public ShowTime getShowTimeById(int i) {
        return showTimeRepo.findById(i).orElseThrow(() -> new EntityNotFoundException("Show time not found for id"+i));
    }
}
