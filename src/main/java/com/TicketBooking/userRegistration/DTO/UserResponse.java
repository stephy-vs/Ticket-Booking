package com.TicketBooking.userRegistration.DTO;

import java.time.LocalDate;

public class UserResponse {
    private Integer id;

    private Integer total;

    private String name;
    private String phoneNumber;

    private Integer adult;
    private Integer child;
    private LocalDate bookDate;

    public UserResponse(Integer id, Integer total, String name, String phoneNumber, Integer adult, Integer child, LocalDate bookDate) {
        this.id = id;
        this.total = total;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.adult = adult;
        this.child = child;
        this.bookDate = bookDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAdult() {
        return adult;
    }

    public void setAdult(Integer adult) {
        this.adult = adult;
    }

    public Integer getChild() {
        return child;
    }

    public void setChild(Integer child) {
        this.child = child;
    }

    public LocalDate getBookDate() {
        return bookDate;
    }

    public void setBookDate(LocalDate bookDate) {
        this.bookDate = bookDate;
    }
}
