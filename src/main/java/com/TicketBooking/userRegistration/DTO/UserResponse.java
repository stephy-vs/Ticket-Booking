package com.TicketBooking.userRegistration.DTO;

import java.time.LocalDate;

public class UserResponse {
    private int id;

    private int total;

    private String name;
    private Long phoneNumber;

    private Integer adult;
    private Integer child;
    private LocalDate bookDate;


    public UserResponse(int id, int total, String name, Long phoneNumber, Integer adult, Integer child, LocalDate bookDate) {
        this.id = id;
        this.total = total;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.adult = adult;
        this.child = child;
        this.bookDate = bookDate;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
