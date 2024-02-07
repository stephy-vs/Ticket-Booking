package com.TicketBooking.InstitutionRegistration.InstitutionDTO;

import java.time.LocalDate;

public class InstitutionDTO {
    private Integer id;
    private String name;
    private String phoneNumber;
    private Integer teachers;
    private Integer student;
    private LocalDate bookDate;
    private Integer total;


    public InstitutionDTO(Integer id, String name, String phoneNumber, Integer teachers, Integer student, LocalDate bookDate, Integer total) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.teachers = teachers;
        this.student = student;
        this.bookDate = bookDate;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getTeachers() {
        return teachers;
    }

    public void setTeachers(Integer teachers) {
        this.teachers = teachers;
    }

    public Integer getStudent() {
        return student;
    }

    public void setStudent(Integer student) {
        this.student = student;
    }

    public LocalDate getBookDate() {
        return bookDate;
    }

    public void setBookDate(LocalDate bookDate) {
        this.bookDate = bookDate;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
