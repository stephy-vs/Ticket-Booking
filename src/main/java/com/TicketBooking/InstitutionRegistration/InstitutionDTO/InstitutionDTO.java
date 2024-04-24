package com.TicketBooking.InstitutionRegistration.InstitutionDTO;

import java.time.LocalDate;

public class InstitutionDTO {
<<<<<<< Updated upstream
    private int id;
    private String name;
    private String phNum;
=======
    private Integer id;
    private String name;
    private String phoneNumber;
>>>>>>> Stashed changes
    private Integer teachers;
    private Integer student;
    private LocalDate bookDate;
    private Integer total;

<<<<<<< Updated upstream
    public InstitutionDTO(int id, String name, String phNum, Integer teachers, Integer student, LocalDate bookDate, Integer total) {
        this.id = id;
        this.name = name;
        this.phNum = phNum;
=======

    public InstitutionDTO(Integer id, String name, String phoneNumber, Integer teachers, Integer student, LocalDate bookDate, Integer total) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
>>>>>>> Stashed changes
        this.teachers = teachers;
        this.student = student;
        this.bookDate = bookDate;
        this.total = total;
    }

<<<<<<< Updated upstream


    public int getId() {
        return id;
    }

    public void setId(int id) {
=======
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
>>>>>>> Stashed changes
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

<<<<<<< Updated upstream
    public String getPhNum() {
        return phNum;
    }

    public void setPhNum(String phNum) {
        this.phNum = phNum;
=======
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
>>>>>>> Stashed changes
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
