package com.TicketBooking.admin.WeekDay;

import com.TicketBooking.InstitutionRegistration.InstitutionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class DayWeekService {

    @Autowired
    private InstitutionRepo institutionRepo;

    public Map<LocalDate, Integer> getUserCount(LocalDate startDate) {
        Map<LocalDate,Integer> institutionCounts = new HashMap<>();
        for (int i =0; i<7;i++){
            LocalDate currentDate = startDate.minusDays(i);
            int count = institutionRepo.getInstitutionCountByVisitDate(currentDate);
            institutionCounts.put(currentDate,count);
        }
        return institutionCounts;
    }

//    public List<UserCountDTO> getUserCount(LocalDate startDate) {
//        List<UserCountDTO> userCounts = new ArrayList<>();
//        for (int i = 0; i < 7; i++) {
//            LocalDate visitDate = startDate.plusDays(i);
//            DayOfWeek dayW = visitDate.getDayOfWeek();
//            String weekDay = dayW.toString();
//
//            Integer instituteCount = institutionRepo.countByVisitDateAndWeekDay(visitDate);
//
//            DayOfWeek day1 = visitDate.getDayOfWeek();
//            String dayOfWeek = day1.toString();
//
//            userCounts.add(new UserCountDTO(visitDate, instituteCount));
//        }
//        return userCounts;
////        return institutionRepo.findDetailsByDateRange(startDate);
////        LocalDate endDate = startDate.plusWeeks(1);
////        int institutionCount= institutionRepo.findIdCount(startDate,endDate);
//
//
////    public Map<String,String> getEventDetails(LocalDate startDate){
////        Map<String,String> eventDetailsMap = new HashMap<>();
////        for (int i = 0; i<7;i++){
////            LocalDate currentDate = startDate.plusDays(i);
////            LocalDate endDate = currentDate.plusDays(1);
////            int totalCount = institutionRepo.findIdCount(currentDate,endDate);
////            String weekDay = getWeekDay(currentDate);
////            eventDetailsMap.put("count : "+currentDate.toString() + ": ",String.valueOf(totalCount));
//////            eventDetailsMap.put("Day : "+currentDate.toString()+": ",weekDay);
//////            log.info("Day"+weekDay);
////
////        }
////        log.info("event : "+eventDetailsMap);
////
////        return eventDetailsMap;
////
////    }
////
////    private String getWeekDay(LocalDate startDate) {
////        DayOfWeek dayOfWeek = startDate.getDayOfWeek();
////        switch (dayOfWeek){
////            case MONDAY:
////                return "Monday";
////
////            case TUESDAY:
////                return "Tuesday";
////
////            case WEDNESDAY :
////                return "Wednesday";
////
////            case THURSDAY :
////                return "Thursday";
////
////            case FRIDAY :
////                return "Friday";
////
////            case SATURDAY :
////                return "Saturday";
////
////            case SUNDAY :
////                return "Sunday";
////
////            default :
////                return "";
////            }
////        }
////    }
//
//
//    }
}
