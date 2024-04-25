package com.TicketBooking.admin.WeekDay;

import com.TicketBooking.constant.TicketConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping(path = "dayWeek")
@CrossOrigin(origins= TicketConstants.serverName)
public class DayWeekController {
    @Autowired
    private DayWeekService dayWeekService;
    @GetMapping(path = "count")
    public Map<LocalDate,Integer>getUserCount(@RequestParam LocalDate startDate){
        return dayWeekService.getUserCount(startDate);
    }
}
    