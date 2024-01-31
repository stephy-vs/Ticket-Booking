package com.TicketBooking.InstitutionRegistration.InstitutionDTO;

import java.time.LocalDate;

public class InstitutionDTO {
    private int id;
    private String name;
    private String phNum;
    private Integer teachers;
    private Integer student;
    private LocalDate bookDate;
    private Integer total;

    public InstitutionDTO(int id, String name, String phNum, Integer teachers, Integer student, LocalDate bookDate, Integer total) {
        this.id = id;
        this.name = name;
        this.phNum = phNum;
        this.teachers = teachers;
        this.student = student;
        this.bookDate = bookDate;
        this.total = total;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhNum() {
        return phNum;
    }

    public void setPhNum(String phNum) {
        this.phNum = phNum;
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
